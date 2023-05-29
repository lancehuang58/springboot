package com.delta.dms.i18n.interceptor;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.delta.dms.i18n.DbLogAutoConfiguration;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.regex.Matcher;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.type.TypeHandlerRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;


@Intercepts(value = {
    @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
    @Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})})
public class SqlStatementInterceptor implements Interceptor {

  private final static Logger log = LoggerFactory.getLogger(DbLogAutoConfiguration.class);

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    Object returnValue;
    long start = System.currentTimeMillis();
    // 执行 SQL语句
    returnValue = invocation.proceed();
    long end = System.currentTimeMillis();
    // 耗时
    long time = end - start;
    try {
      final Object[] args = invocation.getArgs();
      MappedStatement ms = (MappedStatement) args[0];
      Object parameter = null;
      // BoundSql就是封装 MyBatis最终产生的 sql类
      BoundSql boundSql = null;
      //获取参数，if语句成立，表示 sql语句有参数，参数格式是 map形式
      if (args.length > 1) {
        parameter = invocation.getArgs()[1];
        boundSql = ms.getBoundSql(parameter);
        if (args.length == 6) {
          boundSql = (BoundSql) args[5];
        }
      }
      // 获取到节点的 id,即 sql语句的 id
      String sqlId = ms.getId();
      // 获取节点的配置
      Configuration configuration = ms.getConfiguration();
      // 获取到最终的 sql语句
      printSql(returnValue, configuration, boundSql, sqlId, time);
    } catch (Exception e) {
      log.error("sql拦截异常", e);
    }
    return returnValue;
  }

  private void printSql(Object returnValue, Configuration configuration, BoundSql boundSql, String sqlId, long time) {
    String sql = showSql(configuration, boundSql);
    log.info("【SQL Monitor】sqlId = {}, count = {}, time = {} ms, sql = {}", sqlId, getCount(returnValue), time, sql);
    // SQL执行时间超过1000ms
    if(time >= 1000L) {
      log.info("sql_execute_terrible");
    }
  }

  private static String getCount(Object returnValue) {
    String count = "-1";
    if (returnValue == null) {
      log.warn("SQL执行结果对象为null！！！");
      return count;
    }

    log.debug("returnValue class name: {}", returnValue.getClass().getName());
    try {

      if (returnValue instanceof Number) {
        count = String.valueOf(returnValue);
      } else if (returnValue instanceof List) {
        count = String.valueOf(MethodUtils.invokeMethod(returnValue, "size"));
      } else if (returnValue instanceof IPage) {
        count = String.valueOf(MethodUtils.invokeMethod(returnValue, "getTotal"));
      } else {
        log.warn("SQL执行没有匹配到指定的类型: {}", returnValue.getClass());
        count = "1";
      }
    } catch (Exception e) {
      log.error("获取SQL执行结果记录数异常", e);
    }

    return count;
  }

  private static String getParameterValue(Object obj) {
    String value;
    if (obj instanceof String) {
      value = "'" + obj.toString() + "'";
    } else if (obj instanceof Date) {
      DateFormat formatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, Locale.TAIWAN);
      value = "'" + formatter.format(new Date()) + "'";
    } else {
      if (obj != null) {
        value = obj.toString();
      } else {
        value = "";
      }
    }
    return value;
  }

  private static String showSql(Configuration configuration, BoundSql boundSql) {
    // 获取参数
    Object parameterObject = boundSql.getParameterObject();
    List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
    // sql语句中多个空格都用一个空格代替
    String sql = boundSql.getSql().replaceAll("[\\s]+", " ");
    if (!CollectionUtils.isEmpty(parameterMappings) && parameterObject != null) {
      // 获取类型处理器注册器，类型处理器的功能是进行 java类型和数据库类型的转换　　　　　　
      // 如果根据 parameterObject.getClass(）可以找到对应的类型，则替换
      TypeHandlerRegistry typeHandlerRegistry = configuration.getTypeHandlerRegistry();
      if (typeHandlerRegistry.hasTypeHandler(parameterObject.getClass())) {
        sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(parameterObject)));
      } else {
        // MetaObject主要是封装了 originalObject对象，提供了 get和 set的方法用于获取和设置 originalObject的属性值
        // 主要支持对 JavaBean、Collection、Map三种类型对象的操作
        MetaObject metaObject = configuration.newMetaObject(parameterObject);
        for (ParameterMapping parameterMapping : parameterMappings) {
          String propertyName = parameterMapping.getProperty();
          if (metaObject.hasGetter(propertyName)) {
            Object obj = metaObject.getValue(propertyName);
            sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
          } else if (boundSql.hasAdditionalParameter(propertyName)) {
            Object obj = boundSql.getAdditionalParameter(propertyName);
            // 该分支是动态 sql
            sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(getParameterValue(obj)));
          } else {
            sql = sql.replaceFirst("\\?", "缺失");
          }
        }
      }
    }
    return sql;
  }

  @Override
  public Object plugin(Object arg0) {
    return Plugin.wrap(arg0, this);
  }

  @Override
  public void setProperties(Properties properties) {
    // do nothing
  }

}



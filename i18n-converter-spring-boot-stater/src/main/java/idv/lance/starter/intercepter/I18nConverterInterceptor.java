package idv.lance.starter.intercepter;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

@Intercepts({
  @Signature(
      type = ResultSetHandler.class,
      method = "handleResultSets",
      args = {Statement.class})
})
public class I18nConverterInterceptor implements Interceptor {

  private static final Logger log = LoggerFactory.getLogger(I18nConverterInterceptor.class);

  private Converter converter;

  public I18nConverterInterceptor(Converter converter) {
    this.converter = converter;
  }

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    Object result = invocation.proceed();
    if (result instanceof Collection) {
      return convert((Collection) result);
    } else {
      return convert(result);
    }
  }

  private Object convert(Object object) throws InvocationTargetException, IllegalAccessException {
    Set<String> i18nKeys = collectI18nKey(Arrays.asList(object));
    Map<String, String> i18nLabelMapping = converter.convert(i18nKeys);
    for (Field field : object.getClass().getDeclaredFields()) {
      I18n fieldAnnotatedI18n = field.getAnnotation(I18n.class);
      if (fieldAnnotatedI18n == null) {
        continue;
      }
      PropertyDescriptor ps = BeanUtils.getPropertyDescriptor(object.getClass(), field.getName());
      if (ps.getReadMethod() == null || ps.getWriteMethod() == null) {
        continue;
      }
      Object value = ps.getReadMethod().invoke(object);
      ps.getWriteMethod()
          .invoke(
              object, i18nLabelMapping.getOrDefault(String.valueOf(value), String.valueOf(value)));
    }
    return object;
  }

  private Collection<Object> convert(Collection<Object> objList)
      throws InvocationTargetException, IllegalAccessException {

    Set<String> i18nKeys = collectI18nKey(objList);

    final Map<String, String> i18nLabelMapping = converter.convert(i18nKeys);

    for (Object object : objList) {
      for (Field field : object.getClass().getDeclaredFields()) {
        I18n fieldAnnotatedI18n = field.getAnnotation(I18n.class);
        if (fieldAnnotatedI18n == null) {
          continue;
        }
        PropertyDescriptor ps = BeanUtils.getPropertyDescriptor(object.getClass(), field.getName());
        if (ps.getReadMethod() == null || ps.getWriteMethod() == null) {
          continue;
        }
        Object value = ps.getReadMethod().invoke(object);
        String i18nKey = String.valueOf(value);
        String i18nValue = i18nLabelMapping.getOrDefault(i18nKey, String.valueOf(value));
        log.info("i18n convert from [{}] to [{}]", i18nKey, i18nValue);
        ps.getWriteMethod().invoke(object, i18nValue);
      }
    }
    return objList;
  }

  private Set<String> collectI18nKey(Collection<Object> objList)
      throws IllegalAccessException, InvocationTargetException {
    Set<String> i18nKeys = new HashSet<>();

    for (Object object : objList) {
      for (Field field : object.getClass().getDeclaredFields()) {
        I18n fieldAnnotatedI18n = field.getAnnotation(I18n.class);
        if (fieldAnnotatedI18n == null) {
          continue;
        }
        PropertyDescriptor ps = BeanUtils.getPropertyDescriptor(object.getClass(), field.getName());
        if (ps.getReadMethod() == null || ps.getWriteMethod() == null) {
          continue;
        }
        Object value = ps.getReadMethod().invoke(object);
        i18nKeys.add(String.valueOf(value));
      }
    }
    return i18nKeys;
  }

  @Override
  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }
}

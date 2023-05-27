package idv.lance.starter.intercepter;

import java.sql.Statement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;

@Slf4j
@AllArgsConstructor
@Intercepts({
  @Signature(
      type = ResultSetHandler.class,
      method = "handleResultSets",
      args = {Statement.class})
})
public class I18nConverterInterceptor implements Interceptor {

  private I18nMappingHandler handler;

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    Object result = invocation.proceed();
    return handler.convert(result);
  }

  @Override
  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }
}

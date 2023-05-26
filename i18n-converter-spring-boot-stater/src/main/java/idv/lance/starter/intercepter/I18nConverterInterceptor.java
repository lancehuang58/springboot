package idv.lance.starter.intercepter;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.Statement;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import idv.lance.starter.service.I18nService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.beans.BeanUtils;

@Slf4j
@AllArgsConstructor
@Intercepts({
  @Signature(
      type = ResultSetHandler.class,
      method = "handleResultSets",
      args = {Statement.class})
})
public class I18nConverterInterceptor implements Interceptor {

  private I18nService service;

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    Object result = invocation.proceed();
    if (result instanceof Collection) {
      Collection<Object> collection = (Collection<Object>) result;
      return convertCollection(collection);
    } else {
      return convert(result);
    }
  }

  private Collection<Object> convertCollection(Collection<Object> objList) throws Exception {
    Set<String> i18nKeys = collectI18nKey(objList);
    if (i18nKeys.isEmpty()) {
      return objList;
    }

    Map<String, String> i18nLabelMapping = service.findByKeys(i18nKeys);
    ValueSetting valueSetting = new ValueSetting(i18nLabelMapping);
    objectListIterate(objList, valueSetting);
    return objList;
  }

  private Object convert(Object object) throws Exception {
    Set<String> i18nKeys = collectI18nKey(Collections.singletonList(object));

    if (i18nKeys.isEmpty()) {
      return object;
    }

    Map<String, String> i18nLabelMapping = service.findByKeys(i18nKeys);
    ValueSetting valueSetting = new ValueSetting(i18nLabelMapping);
    objectProcess(valueSetting, object);
    return object;
  }

  private Set<String> collectI18nKey(Collection<Object> objList) throws Exception {
    KeyCollector keyCollector = new KeyCollector();
    objectListIterate(objList, keyCollector);
    return keyCollector.getI18nKeys();
  }

  static void objectListIterate(Collection<Object> objects, FieldProcessor fieldProcessor)
      throws Exception {
    for (Object object : objects) {
      objectProcess(fieldProcessor, object);
    }
  }

  private static void objectProcess(FieldProcessor fieldProcessor, Object object) throws Exception {
    for (Field field : object.getClass().getDeclaredFields()) {
      I18nMapping fieldAnnotatedI18n = field.getAnnotation(I18nMapping.class);
      if (fieldAnnotatedI18n == null) {
        continue;
      }
      PropertyDescriptor ps = BeanUtils.getPropertyDescriptor(object.getClass(), field.getName());
      if (ps == null || ps.getReadMethod() == null || ps.getWriteMethod() == null) {
        continue;
      }
      fieldProcessor.process(ps, object);
    }
  }

  @Override
  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  interface FieldProcessor {
    void process(PropertyDescriptor ps, Object object) throws Exception;
  }

  static class ValueSetting implements FieldProcessor {
    Map<String, String> i18nLabelMapping;

    public ValueSetting(Map<String, String> i18nLabelMapping) {
      this.i18nLabelMapping = i18nLabelMapping;
    }

    @Override
    public void process(PropertyDescriptor ps, Object object) throws Exception {
      Object value = ps.getReadMethod().invoke(object);
      String i18nKey = String.valueOf(value);
      String i18nValue = i18nLabelMapping.getOrDefault(i18nKey, String.valueOf(value));
      log.info("i18n convert from [{}] to [{}]", i18nKey, i18nValue);
      ps.getWriteMethod().invoke(object, i18nValue);
    }
  }

  static class KeyCollector implements FieldProcessor {
    private final Set<String> i18nKeys = new HashSet<>();

    @Override
    public void process(PropertyDescriptor ps, Object object) throws Exception {
      Object value = ps.getReadMethod().invoke(object);
      i18nKeys.add(String.valueOf(value));
    }

    public Set<String> getI18nKeys() {
      return i18nKeys;
    }
  }
}

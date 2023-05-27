package idv.lance.starter.intercepter;

import idv.lance.starter.annotation.I18nMapping;
import idv.lance.starter.service.I18nService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;
import java.util.Set;

@AllArgsConstructor
@Service
public class I18nMappingHandlerImpl implements I18nMappingHandler {

  private I18nService service;

  @SuppressWarnings(value = {"unchecked"})
  @Override
  public Object convert(Object object) throws Exception {
    if (object instanceof Collection) {
      Collection<Object> objects = (Collection<Object>) object;
      return convertCollection(objects);
    }
    return object;
  }

  private Set<String> collectI18nKey(Collection<Object> objList) throws Exception {
    KeyCollector keyCollector = new KeyCollector();
    objectListIterate(objList, keyCollector);
    return keyCollector.getI18nKeys();
  }

  void objectListIterate(Collection<Object> objects, FieldProcessor fieldProcessor)
      throws Exception {
    for (Object object : objects) {
      objectProcess(fieldProcessor, object);
    }
  }

  private void objectProcess(FieldProcessor fieldProcessor, Object object) throws Exception {
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

  private Collection<Object> convertCollection(Collection<Object> objList) throws Exception {
    Set<String> i18nKeys = collectI18nKey(objList);
    if (i18nKeys.isEmpty()) {
      return objList;
    }

    Map<String, String> i18nKeyValueMapping = service.findByKeys(i18nKeys);
    ValueSetting processor = new ValueSetting(i18nKeyValueMapping);
    objectListIterate(objList, processor);
    return objList;
  }
}

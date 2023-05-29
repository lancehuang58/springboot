package com.delta.dms.i18n.intercepter;

import java.beans.PropertyDescriptor;
import java.util.Map;

class ValueSetting implements FieldProcessor {
  Map<String, String> i18nLabelMapping;

  public ValueSetting(Map<String, String> i18nLabelMapping) {
    this.i18nLabelMapping = i18nLabelMapping;
  }

  @Override
  public void process(PropertyDescriptor ps, Object object) throws Exception {
    Object value = ps.getReadMethod().invoke(object);
    String i18nKey = String.valueOf(value);
    String i18nValue = i18nLabelMapping.getOrDefault(i18nKey, String.valueOf(value));
    ps.getWriteMethod().invoke(object, i18nValue);
  }
}

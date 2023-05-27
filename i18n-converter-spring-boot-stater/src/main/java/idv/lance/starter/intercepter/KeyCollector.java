package idv.lance.starter.intercepter;

import java.beans.PropertyDescriptor;
import java.util.HashSet;
import java.util.Set;

class KeyCollector implements FieldProcessor {
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

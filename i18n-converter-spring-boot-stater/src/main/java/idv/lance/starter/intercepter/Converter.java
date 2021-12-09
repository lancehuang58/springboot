package idv.lance.starter.intercepter;

import java.util.Collection;
import java.util.Map;

public interface Converter {

  /**
   * convert i18n key to i18n value by locale
   * @param i18Key i18n key
   * @return i18n key/value map
   */
  Map<String,String> convert(Collection<String> i18Key);
}

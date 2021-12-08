package idv.lance.starter.intercepter;

import java.util.Collection;
import java.util.Map;

public interface Converter {
  Map<String,String> convert(Collection<String> i18Key);
}

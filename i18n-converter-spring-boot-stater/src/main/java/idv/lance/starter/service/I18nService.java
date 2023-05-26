package idv.lance.starter.service;

import java.util.Map;
import java.util.Set;

public interface I18nService {
    Map<String, String> findByKeys(Set<String> i18Key);
}

package com.delta.dms.i18n.service;

import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

public interface I18nService {
    Map<String, String> findByKeys(Set<String> i18Key);

    Map<String, String> findByKeys(HashSet<String> es, Locale locale);
}

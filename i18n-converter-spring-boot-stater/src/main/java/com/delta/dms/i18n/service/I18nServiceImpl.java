package com.delta.dms.i18n.service;

import static java.util.Objects.isNull;
import static java.util.stream.Collectors.*;

import com.delta.dms.i18n.entity.I18nEntity;
import com.delta.dms.i18n.repository.I18nRepository;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class I18nServiceImpl implements I18nService {

  private final I18nRepository repository;

  LoadingCache<String, I18nEntity> cache =
      CacheBuilder.newBuilder()
          .maximumSize(10000L)
          .build(
              new CacheLoader<String, I18nEntity>() {
                @Override
                public I18nEntity load(String key) throws Exception {
                  return repository.findByKey(key);
                }
              });

  @PostConstruct
  public void init() {
    cache.putAll(
        repository.findAll().stream()
            .collect(
                groupingBy(I18nEntity::getId, collectingAndThen(toList(), list -> list.get(0)))));
  }

  @Override
  public Map<String, String> findByKeys(Set<String> i18Keys) {

    return i18Keys.stream()
        .map(this::mappingValue)
        .map(this::mappingLocale)
        .collect(
            groupingBy(Pair::getLeft, collectingAndThen(toList(), list -> list.get(0).getRight())));
  }

  @Override
  public Map<String, String> findByKeys(HashSet<String> i18Keys, Locale locale) {
    return i18Keys.stream()
            .map(this::mappingValue)
            .map(i18nEntity -> mappingLocale(i18nEntity, locale))
            .collect(
                    groupingBy(Pair::getLeft, collectingAndThen(toList(), list -> list.get(0).getRight())));
  }

  private Pair<String, String> mappingLocale(I18nEntity entity, Locale defaultLocale) {

    Locale locale = isNull(defaultLocale)? LocaleContextHolder.getLocale(): defaultLocale;

    if (locale == Locale.TAIWAN) {
      return Pair.of(entity.getId(), entity.getZhTw());
    }

    if (locale == Locale.CHINA) {
      return Pair.of(entity.getId(), entity.getZhCn());
    }

    return Pair.of(entity.getId(), entity.getEnUs());
  }

  private Pair<String, String> mappingLocale(I18nEntity entity) {
    return mappingLocale(entity, null);
  }

  private I18nEntity mappingValue(String i18nKey) {
    try {
      return cache.get(i18nKey);
    } catch (Exception e) {
      log.warn("can't find any mapping key {}, use key as return value..., if you need to use i18n mapping service, please add i18n key/value to i18n table.", i18nKey);
      return new I18nEntity().setId(i18nKey).setZhTw(i18nKey).setEnUs(i18nKey).setZhCn(i18nKey);
    }
  }
}

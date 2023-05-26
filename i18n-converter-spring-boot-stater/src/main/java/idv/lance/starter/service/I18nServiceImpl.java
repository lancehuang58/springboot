package idv.lance.starter.service;

import static java.util.stream.Collectors.*;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import idv.lance.starter.entity.I18nEntity;
import idv.lance.starter.repository.I18nRepository;
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

  private Pair<String, String> mappingLocale(I18nEntity entity) {

    Locale locale = LocaleContextHolder.getLocale();

    if (locale == Locale.TAIWAN) {
      return Pair.of(entity.getId(), entity.getZhTw());
    }

    if (locale == Locale.CHINA) {
      return Pair.of(entity.getId(), entity.getZhCn());
    }

    return Pair.of(entity.getId(), entity.getEnUs());
  }

  private I18nEntity mappingValue(String i18nKey) {
    try {
      return cache.get(i18nKey);
    } catch (Exception e) {
      log.error(e.getMessage(), e);
      log.warn("can't find any mapping key {}", i18nKey);
      return new I18nEntity().setId(i18nKey).setZhTw(i18nKey).setEnUs(i18nKey).setZhCn(i18nKey);
    }
  }
}

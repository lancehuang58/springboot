package idv.lance.starter.repository;

import idv.lance.starter.entity.I18nEntity;

import java.util.List;

public interface I18nRepository {
  I18nEntity findByKey(String i18Key);

  List<I18nEntity> findAll();
}

package com.delta.dms.i18n.repository;

import com.delta.dms.i18n.entity.I18nEntity;

import java.util.List;

public interface I18nRepository {
  I18nEntity findByKey(String i18Key);

  List<I18nEntity> findAll();
}

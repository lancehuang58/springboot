package idv.lance.starter.repository;

import idv.lance.starter.config.I18nMappingConfig;
import idv.lance.starter.entity.I18nEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@RequiredArgsConstructor
@Service
public class I18nRepositoryImpl implements I18nRepository {
  private final JdbcTemplate jdbcTemplate;
  public static final String SQL_SELECT_ALL = "select * from %s.%s";
  public static final String SQL_SELECT_ONE = "select * from %s.%s where id = '%s'";

  @Autowired I18nMappingConfig config;

  @Override
  public I18nEntity findByKey(String i18Key) {
    String sql = String.format(SQL_SELECT_ONE, config.getSchema(), config.getTableName(), i18Key);
    return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(I18nEntity.class));
  }

  @Override
  public List<I18nEntity> findAll() {
    return jdbcTemplate.query(
        String.format(SQL_SELECT_ALL, config.getSchema(), config.getTableName()),
        new BeanPropertyRowMapper<>(I18nEntity.class));
  }
}

package idv.lance.starter.repository;


import idv.lance.starter.entity.I18nEntity;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
@Slf4j
@RequiredArgsConstructor
@Service
public class I18nRepositoryImpl implements I18nRepository {
  private final JdbcTemplate jdbcTemplate;
  public static final String SQL_SELECT_ALL = "select * from mydms.i18n";
  public static final String SQL_SELECT_ONE = "select * from mydms.i18n where id = '%s'";

  @Override
  public I18nEntity findByKey(String i18Key) {
    log.info("select by key {}", i18Key);
    String sql = String.format(SQL_SELECT_ONE, i18Key);
    return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(I18nEntity.class));
  }

  @Override
  public List<I18nEntity> findAll() {
    log.info("select all i18n");
    return jdbcTemplate.query(SQL_SELECT_ALL, new BeanPropertyRowMapper<>(I18nEntity.class));
  }
}

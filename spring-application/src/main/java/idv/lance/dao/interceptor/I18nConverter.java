package idv.lance.dao.interceptor;

import idv.lance.starter.intercepter.Converter;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class I18nConverter implements Converter {

  public static final String SQL_SELECT_MULTIPLE =
      "select id, %s as name from i18n where id in (%s)";

  private final JdbcTemplate jdbcTemplate;

  @Override
  public Map<String, String> convert(Collection<String> i18Key) {
    final String ids = String.join(",", Collections.nCopies(i18Key.size(), "?"));
    String sql = String.format(SQL_SELECT_MULTIPLE, LocaleContextHolder.getLocale(), ids);
    List<Pair<String, String>> list =
        jdbcTemplate.query(
            sql,
            (rs, rownum) -> Pair.of(rs.getString("id"), rs.getString("name")), i18Key.toArray());
    return list.stream().collect(Collectors.toMap(Pair::getKey, Pair::getValue, (a, b) -> a));
  }
}

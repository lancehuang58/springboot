package idv.lance.entity;

import idv.lance.starter.intercepter.I18n;
import lombok.Data;

@Data
public class TagConfig {

  private Long id;
  @I18n
  private String label;
  private Long many;
  private String uiType;
  private String selectCate;
  private Long parentSelectCate;
  private String tagKey;
  private String aspectDataCategory;
  @I18n
  private String placeholder;
}

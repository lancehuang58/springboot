package idv.lance.dao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.delta.dms.i18n.annotation.I18nMapping;
import lombok.Data;

@Data
public class TagConfig {

  @TableId
  private String id;

  @I18nMapping
  private String label;
  private Long many;
  private String uiType;
  private String selectCate;
  private Long parentSelectCate;
  private String tagKey;
  private String aspectDataCategory;

  @I18nMapping
  private String placeholder;
}

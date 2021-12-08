package idv.lance.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

@Data
public class I18n {

  private String id;
  @TableField(value = "zh_TW")
  private String zhTw;
  @TableField(value = "en_US")
  private String enUs;
  @TableField(value = "zh_CN")
  private String zhCn;
}

package idv.lance.dao.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.delta.dms.i18n.property.Confidential;
import lombok.Data;

@Data
@TableName("v_user")
public class UserView {
  @TableId
  private String uid;
  @TableField("sAmAccount")
  private String account;
  @Confidential
  private String name;
  private Boolean status;
}

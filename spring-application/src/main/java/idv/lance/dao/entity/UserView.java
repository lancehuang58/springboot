package idv.lance.dao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import idv.lance.starter.property.Confidential;
import lombok.Data;

@Data
@TableName("v_user")
public class UserView {
  @TableId
  private String uid;
  private String account;
  @Confidential
  private String name;
  private Boolean status;
}

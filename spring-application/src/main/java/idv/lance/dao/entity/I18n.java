package idv.lance.dao.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class I18n {
    @TableId
    private String id;
    private String zhTw;
    private String enUs;
    private String zhCn;
}

package idv.lance.starter.entity;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class I18nEntity {
    private String id;
    private String zhTw;
    private String zhCn;
    private String enUs;
}

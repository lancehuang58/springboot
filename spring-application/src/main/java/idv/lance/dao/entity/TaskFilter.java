package idv.lance.dao.entity;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

@Data
public class TaskFilter {
    private long id;
    private String name;
    private String gid;
    private String scope;
    private String filter;
    private Boolean type;
    private String createUserId;
    private Long createTime;
    private String modifiedUserIs;
    private Long modifiedTime;
    private String fieldSetting;
    @Version
    private Long versionLock;
}

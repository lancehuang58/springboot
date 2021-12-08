package idv.lance.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import idv.lance.entity.UserView;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserViewMapper extends BaseMapper<UserView> {
}

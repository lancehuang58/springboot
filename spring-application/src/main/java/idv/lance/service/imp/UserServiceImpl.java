package idv.lance.service.imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import idv.lance.dao.entity.UserView;
import idv.lance.dao.mapper.UserViewMapper;
import idv.lance.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    UserViewMapper mapper;

    @Override
    public List<UserView> users() {
        return mapper.selectList(new QueryWrapper<>());
    }
}

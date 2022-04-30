package idv.lance.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import idv.lance.dao.entity.UserView;
import idv.lance.dao.mapper.UserViewMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDao {

    @Autowired
    UserViewMapper userMapper;

    public List<UserView> findByName(String name) {
        QueryWrapper<UserView> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", name);
        return userMapper.selectList(queryWrapper);
    }
}

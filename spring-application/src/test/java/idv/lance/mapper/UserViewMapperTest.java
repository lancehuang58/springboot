package idv.lance.mapper;

import static org.junit.jupiter.api.Assertions.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import idv.lance.entity.UserView;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserViewMapperTest {
  @Autowired UserViewMapper userViewMapper;

  @Test
  void testLoadUser() {
    QueryWrapper<UserView> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("uid", "UnOT");
    final UserView user = userViewMapper.selectOne(queryWrapper);
    Assertions.assertEquals("lance.huang", user.getAccount());
  }

  @Test
  void test_paging() {
    Page<UserView> page = new Page<>(1, 10);
    QueryWrapper<UserView> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("status", false);
    final Page<UserView> userViewPage = userViewMapper.selectPage(page, queryWrapper);
    userViewPage.getRecords().forEach(System.out::println);
  }
}

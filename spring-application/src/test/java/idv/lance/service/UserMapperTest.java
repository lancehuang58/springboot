package idv.lance.service;

import idv.lance.dao.UserDao;
import idv.lance.dao.entity.UserView;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserMapperTest {

    @Autowired
    UserDao userDao;

    @Test
    public void testSelect() {
        List<UserView> users = userDao.findByName("SRV-IAD1.A");
        Assertions.assertThat(users).isNotEmpty();
    }
}

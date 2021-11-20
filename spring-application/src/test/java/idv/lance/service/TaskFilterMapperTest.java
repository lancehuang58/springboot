package idv.lance.service;

import idv.lance.dao.TaskFilterDao;
import idv.lance.dao.entity.TaskFilter;
import idv.lance.dao.mapper.TaskFilterMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class TaskFilterMapperTest {

    @Autowired
    TaskFilterDao taskFilterDao;

    @Test
    public void testSelect() {
        List<TaskFilter> userFilters = taskFilterDao.findPersonalFilter("UnOT");
        userFilters.forEach(System.out::println);
    }
}

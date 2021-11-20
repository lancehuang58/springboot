package idv.lance.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import idv.lance.dao.entity.TaskFilter;
import idv.lance.dao.mapper.TaskFilterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class TaskFilterDao {

    @Autowired
    TaskFilterMapper taskFilterMapper;

    public List<TaskFilter> findPersonalFilter(String userId) {
        QueryWrapper<TaskFilter> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("create_user_id", userId);
        queryWrapper.eq("type", true);
        queryWrapper.in("scope", Arrays.asList("private", "system"));
        List<TaskFilter> taskFilters = taskFilterMapper.selectList(queryWrapper);
        return taskFilters;
    }
}

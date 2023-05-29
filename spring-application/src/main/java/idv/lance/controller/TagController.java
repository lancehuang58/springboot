package idv.lance.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import idv.lance.dao.entity.TagConfig;
import idv.lance.dao.mapper.TagConfigMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class TagController {
  private final TagConfigMapper mapper;

  @GetMapping("/tag/{id}")
  public TagConfig getConfig(@PathVariable String id) {
    log.info("get tag {}", id);
    TagConfig tagConfig = mapper.selectById(id);
    log.info("tag config {}", tagConfig);
    return tagConfig;
  }

  @GetMapping("/tags")
  public List<TagConfig> findAll() {
    return mapper.selectList(new QueryWrapper<>());
  }
}

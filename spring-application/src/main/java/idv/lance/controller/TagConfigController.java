package idv.lance.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import idv.lance.entity.TagConfig;
import idv.lance.mapper.TagConfigMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
@Slf4j
@RequiredArgsConstructor
@RestController
public class TagConfigController {

  private final TagConfigMapper tagConfigMapper;

  @GetMapping("/tagconfig/{label}")
  public TagConfig getTagConfigByLabel(@PathVariable String label) {
    QueryWrapper<TagConfig> queryWrapper = new QueryWrapper<>();
    queryWrapper.eq("label", label);
    return tagConfigMapper.selectOne(queryWrapper);
  }
}

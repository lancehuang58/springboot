package idv.lance.controller;

import idv.lance.dao.entity.TagConfig;
import idv.lance.dao.mapper.TagConfigMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class TagController {
  private final TagConfigMapper mapper;

  @GetMapping("/tag/{id}")
  public TagConfig getConfig(@PathVariable Long id) {
    return mapper.selectById(id);
  }
}

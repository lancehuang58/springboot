package idv.lance.controller;

import com.delta.dms.i18n.tool.I18nUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class I18nMappingController {

  @GetMapping("/i18nMapping/{id}")
  public String hello(@PathVariable String id) {
    return I18nUtil.mapping(id);
  }

  @GetMapping("/i18nMapping/en/{id}")
  public String hello2(@PathVariable String id) {
    return I18nUtil.mappingWithLocale(id, Locale.ENGLISH);
  }

  @GetMapping("/hello3/{id}/{param1}")
  public String hello3(@PathVariable String id, @PathVariable String param1) {
    return I18nUtil.mappingWithLocale(id, Locale.ENGLISH, param1);
  }
}

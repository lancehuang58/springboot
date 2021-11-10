package idv.lance.controller;

import idv.lance.service.HelloService;
import idv.lance.vo.NameValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    HelloService helloService;

    @Autowired
    MessageSource messageSource;

    @GetMapping("/hello")
    public String hello() {
        return helloService.sayHello();
    }

    @RequestMapping(value = {"/title"}, method = {RequestMethod.GET}, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<NameValue> changeLocale(HttpServletRequest request, HttpServletResponse response) {
        Locale locale = LocaleContextHolder.getLocale();
        log.info("locale {}", locale);
        log.info("app.title {}", messageSource.getMessage("app.title", null, locale));
        NameValue nameValue = new NameValue("title", messageSource.getMessage("app.title", null, locale));
        return new ResponseEntity<NameValue>(nameValue, HttpStatus.OK);
    }
}

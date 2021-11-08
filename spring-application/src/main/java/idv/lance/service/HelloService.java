package idv.lance.service;

import idv.lance.aop.LogTime;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HelloService {
    @LogTime
    public String sayHello() {
        log.info("hello");
        return "hello";
    }

    @LogTime
    public void core() {
        log.info("log for core");
    }
}

package idv.lance.service;

import idv.lance.aop.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HelloService {
    @Log
    public String sayHello() {
        log.info("hello");
        return "hello";
    }

    @Log
    public void core() {
        log.info("log for core");
    }
}

package idv.lance.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class HelloService {
    public void sayHello() {
        log.info("hello");
    }
}

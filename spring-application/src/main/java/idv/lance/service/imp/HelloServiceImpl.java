package idv.lance.service.imp;

import idv.lance.aop.LogTime;
import idv.lance.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class HelloServiceImpl implements HelloService {
    @Override
    @LogTime
    public String sayHello() {
        log.info("hello");
        return "hello";
    }

    @Override
    @LogTime
    public void core() {
        log.info("log for core");
    }
}

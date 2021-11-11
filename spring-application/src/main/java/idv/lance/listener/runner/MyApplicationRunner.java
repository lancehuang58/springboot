package idv.lance.listener.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


//--Mygroup=a,b --filePath=c:/data/1.txt -myargs=2 testNoArg TestNoarg2=3
@Slf4j
@Component
@Order(2)
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("run by my application runner");
        
    }
}

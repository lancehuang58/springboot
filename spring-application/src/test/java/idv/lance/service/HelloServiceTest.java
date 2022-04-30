package idv.lance.service;

import idv.lance.service.imp.HelloServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class HelloServiceTest {

    @Autowired
    HelloServiceImpl helloService;

    @Test
    public void test_load_context() {
        helloService.sayHello();
    }


}
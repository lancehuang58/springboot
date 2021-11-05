package idv.lance.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class HelloServiceTest {

    @Autowired
    HelloService helloService;

    @Test
    public void test_load_context() {
        helloService.sayHello();
    }
}
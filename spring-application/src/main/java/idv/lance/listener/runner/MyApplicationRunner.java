package idv.lance.listener.runner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

//--Mygroup=a,b --filePath=c:/data/1.txt -myargs=2 testNoArg TestNoarg2=3
@Slf4j
@Component
@Order(2)
public class MyApplicationRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        log.info("run by my application runner");
        printArgs(args);
    }
    public void printArgs(ApplicationArguments arguments) {
        System.out.println("# 非选项参数数量: " + arguments.getNonOptionArgs().size());
        System.out.println("# 选项参数数量: " + arguments.getOptionNames().size());
        System.out.println("# 非选项参具参数:");
        arguments.getNonOptionArgs().forEach(System.out::println);

        System.out.println("# 选项参数具体参数:");
        arguments.getOptionNames().forEach(optionName -> {
            System.out.println("--" + optionName + "=" + arguments.getOptionValues(optionName));
        });
    }
}

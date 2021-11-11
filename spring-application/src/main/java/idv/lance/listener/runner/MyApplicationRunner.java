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
        System.out.println("# NonOptionArgs: " + arguments.getNonOptionArgs().size());
        System.out.println("# OptionName size: " + arguments.getOptionNames().size());
        System.out.println("# NonOptionArgs:");
        arguments.getNonOptionArgs().forEach(System.out::println);

        System.out.println("# OptionNames:");
        arguments.getOptionNames().forEach(optionName -> {
            System.out.println("--" + optionName + "=" + arguments.getOptionValues(optionName));
        });
    }
}

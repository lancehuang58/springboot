package idv.lance.service;

import idv.lance.aop.LogTime;

public interface HelloService {
    @LogTime
    String sayHello();

    @LogTime
    void core();
}

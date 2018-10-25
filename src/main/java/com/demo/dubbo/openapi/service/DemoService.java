package com.demo.dubbo.openapi.service;


import com.demo.dubbo.openapi.domain.Person;

/**
 * Demo服务接口
 */
public interface DemoService {

    String sayHello(Person person);

}

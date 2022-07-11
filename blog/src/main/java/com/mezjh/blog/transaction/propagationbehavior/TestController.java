package com.mezjh.blog.transaction.propagationbehavior;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 使用Controller进行测试
 * @author ZJH
 * @date 2021/7/6 16:50
 */
@RestController
@RequestMapping("/transaction")
public class TestController {

    @Resource
    private Pb1RequiredA pb1RequiredA;
    @Resource
    private Pb2SupportsA pb2SupportsA;
    @Resource
    private Pb4RequiresNewA pb4RequiresNewA;
    @Resource
    private Pb7NestedA pb7NestedA;

    @GetMapping("/test")
    public void test() {
//        pb1RequiredA.methodA1();
//        pb1RequiredA.methodA2();
//        pb2SupportsA.methodA1();
//        pb2SupportsA.methodA2();
        pb4RequiresNewA.methodA1();
//        pb7NestedA.methodA1();
    }

}

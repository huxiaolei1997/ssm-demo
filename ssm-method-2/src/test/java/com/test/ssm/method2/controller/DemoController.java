package com.test.ssm.method2.controller;

import com.test.ssm.method2.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * demo 类
 *
 * @author 胡晓磊
 * @version $Id: DemoController, v0.1
 * @company 杭州网络科技有限公司
 * @date 2018年11月27日 14:09 胡晓磊 Exp $
 */
@RestController
public class DemoController {

    @Autowired
    private DemoService demoService;

    @RequestMapping(value = "hello/{name}", method = RequestMethod.GET)
    public String hello(@PathVariable("name") String name ) {
        String value = demoService.hello(name);
        System.out.println("value = " + value);
        return value;
    }
}

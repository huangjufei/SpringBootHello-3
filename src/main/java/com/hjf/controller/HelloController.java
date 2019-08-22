package com.hjf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hjf.properties.BoyProperties;


/**
 * @RestController Spring4之后新加的注解;告诉spring这时controller层
 * 且还类返回的格式是全是json
 * @RestController = @Controller + @ResponseBody
 */
@RestController
public class HelloController {

    //配置内容的注入
    @Value("${cupSize}")
    private String cupSize;
    @Value("${age}")
    private Integer age;
    @Value("${content}")
    private String content;
    @Autowired
    private BoyProperties boyProperties;

    //映射多个路径
    @RequestMapping(value = {"/hi","/Hi"}, method = RequestMethod.GET)
    public String hi() {
        return "index";
    }
    
    //@GetMapping =  @RequestMapping + method = RequestMethod.GET
    @RequestMapping(value = "/param2", method = RequestMethod.GET)
    public String param2(@RequestParam(value = "id", required = false, defaultValue = "0") Integer myId) {
        return "id: " + myId;
    }
    
    @RequestMapping(path = "/hello", method = RequestMethod.GET)
    public String hello() {
        return "Hello SpringBoot! <br> " +
                "cupSize is " + cupSize + ", age is " + age + ". <br> " +
                "content is " + content + ". <br>" +
                boyProperties.toString();
    }
}

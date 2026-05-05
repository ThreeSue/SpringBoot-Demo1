package com.sue.demo1.controller;

import com.sue.demo1.model.StudentConfigurationProperties;
import com.sue.demo1.model.StudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: 2412-21-苏世睿
 * @Description : [一句话描述这个类的作用]
 * @Version : [v1.1]
 * @Date: 2026/3/11
 * @Time: 10:34
 */

@RestController // 能对外访问
public class HelloController {
    @Autowired  // 获取容器中的组件使用。自动注入StudentModel对象，Spring会自动创建一个StudentModel对象，并将其注入到HelloController中
    private StudentModel studentModel; //注入StudentModel对象

    @Autowired
    private StudentConfigurationProperties studentConfigurationProperties; //注入StudentConfigurationProperties对象

    // http://localhost:8080/hello?name=xiaoming
    // Get
    @GetMapping("/hello") //路径映射
    public String hello(String name){  //方法体
        return "我是" + studentModel.getName() + ",我今年" + studentModel.getAge() + "岁，你好~~"; // 返回字符串
    }

    @GetMapping("/hello1") //路径映射
    public String hello1(){  //方法体
        return studentConfigurationProperties.toString();
    }

}

package com.macc.demo1.controller;

import com.macc.demo1.model.StudentConfigurationProperties;
import com.macc.demo1.model.StudentModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private StudentModel studentModel;

    @Autowired
    private StudentConfigurationProperties studentConfigurationProperties;

    // Get
    @GetMapping("/hello")
    public String hello(String name){
        return "我是" + studentModel.getName() + ",我今年"+ studentModel.getAge()+ "岁，你好~~";
    }

    @GetMapping("/hello1")
    public String hello1(){
        return studentConfigurationProperties.toString();
    }

}

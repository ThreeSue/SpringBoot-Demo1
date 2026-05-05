package com.sue.demo1.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Author: 2412-21-苏世睿
 * @Description : [一句话描述这个类的作用]
 * @Version : [v1.1]
 * @Date: 2026/3/11
 * @Time: 11:22
 */
@Component // 注册成组件。将这个类交给Spring管理，才能使用@Value注解读取配置文件中的值
public class StudentModel {

    @Value("${student.name}")
    private String name;

    @Value("${student.age}")
    private Integer age;

    @Value("${student.sex}")
    private String sex;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}

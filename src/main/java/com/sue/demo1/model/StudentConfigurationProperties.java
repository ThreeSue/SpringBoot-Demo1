package com.sue.demo1.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: 2412-21-苏世睿
 * @Description : [一句话描述这个类的作用]
 * @Version : [v1.1]
 * @Date: 2026/3/17
 * @Time: 15:58
 */

@Component // 注册成组件。将这个类交给Spring管理，才能使用@ConfigurationProperties注解读取配置文件中的值
@ConfigurationProperties(prefix = "student") // 读取配置文件中以student开头的属性值，并将其绑定到这个类的属性上
public class StudentConfigurationProperties {

    private String name;

    private Integer age;

    private String sex;

    private List<String> friend;
// 引入getters和setters方法，供Spring使用反射机制将配置文件中的值注入到这个类的属性中
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

    public List<String> getFriend() {
        return friend;
    }

    public void setFriend(List<String> friend) {
        this.friend = friend;
    }

    @Override
    public String toString() {
        return "StudentConfigurationProperties{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", sex='" + sex + '\'' +
                ", friend=" + friend +
                '}';
    }


}


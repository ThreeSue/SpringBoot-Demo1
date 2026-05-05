package com.sue.demo1.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author: 2412-21-苏世睿
 * @Description : [一句话描述这个类的作用]
 * @Version : [v1.1]
 * @Date: 2026/3/17
 * @Time: 17:08
 */

@Data
@TableName("employee") // 指定数据库表名为employee
public class EmployeeModel {

    private Integer id;

    private String name;

    private LocalDate hiredate;

    private String gender;

    private Integer department;

    @TableField(exist = false) // 告诉MyBatis这个字段在数据库表中不存在，作为拓展字段存储
    private String departmentName;

}

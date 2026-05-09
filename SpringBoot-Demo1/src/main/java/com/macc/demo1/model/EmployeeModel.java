package com.macc.demo1.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDate;

@Data
@TableName("employee")
public class EmployeeModel {

    private Integer id;

    private String name;

    private LocalDate hiredate;

    private String gender;

    private Integer department;

    @TableField(exist = false)
    private String departmentName;


}

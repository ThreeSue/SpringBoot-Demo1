package com.macc.demo1.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("department")
public class DepartmentModel {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String departmentName;

    private String location;

    private String manager;


}

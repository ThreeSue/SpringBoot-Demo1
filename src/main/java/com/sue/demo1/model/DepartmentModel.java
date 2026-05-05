package com.sue.demo1.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: 蘇世睿
 * @Class: 移動互聯2412
 * @SeatNo: 21
 * @Description : [一句话描述这个类的作用]
 * @Version : [v1.1]
 * @Date: 2026/3/25
 * @Time: 11:25
 */

@Data
@TableName("department")
public class DepartmentModel {

    private Integer id;

    private String departmentName;

    private String manager;

    private String location;
}

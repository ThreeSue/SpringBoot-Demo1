package com.sue.demo1.model.req;

import lombok.Data;

import java.time.LocalDate;

/**
 * @Author: 蘇世睿
 * @Class: 移動互聯2412
 * @SeatNo: 21
 * @Description : [一句话描述这个类的作用]
 * @Version : [v1.1]
 * @Date: 2026/4/13
 * @Time: 16:20
 */

@Data
public class EmployeeSearchReq {

    private Integer id;

    private String name;

    private LocalDate[] hiredate;

    private String gender;

    private String department;

}

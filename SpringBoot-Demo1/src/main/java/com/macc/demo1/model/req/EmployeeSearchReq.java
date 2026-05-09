package com.macc.demo1.model.req;

import lombok.Data;

import java.time.LocalDate;

@Data
public class EmployeeSearchReq {

    private Integer id;

    private String name;

    private LocalDate[] hiredate;

    private String gender;

    private String department;


}

package com.macc.demo1;

import com.macc.demo1.mapper.EmployeeMapper1;
import com.macc.demo1.model.EmployeeModel;
import com.macc.demo1.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class EmployeeTest {

    @Autowired
    private EmployeeMapper1 employeeMapper1;

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void getEmployeeListTest(){
        System.out.println(employeeService.list());
    }

    @Test
    void addEmployee() {
        EmployeeModel employeeModel = new EmployeeModel();
        employeeModel.setName("小刘");
        employeeModel.setDepartment("软件部");
        employeeModel.setHiredate(LocalDate.now());
        employeeModel.setGender("男");
        employeeMapper1.addEmployee(employeeModel);
    }
}

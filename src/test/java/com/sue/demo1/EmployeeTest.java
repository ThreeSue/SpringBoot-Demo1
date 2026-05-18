package com.sue.demo1;

import com.sue.demo1.mapper.EmployeeMapper1;
import com.sue.demo1.model.EmployeeModel;
import com.sue.demo1.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: 2412-21-苏世睿
 * @Description : [测试类]
 * @Version : [v1.1]
 * @Date: 2026/3/18
 * @Time: 10:33
 */

@SpringBootTest // 可以使用springboot容器中注册好的组件
public class EmployeeTest {

    @Autowired
    private EmployeeMapper1 employeeMapper1;

    @Autowired
    private EmployeeService employeeService;

    // 查询
    @Test
    public void getEmployeeListTest(){
//        List<EmployeeModel> employeeList = employeeMapper1.getEmployeeList();
//        System.out.println(employeeList);
        System.out.println(employeeService.list());

    }

    // 新增员工数据
    @Test
    public void insertEmployeeTest(){
        EmployeeModel employee = new EmployeeModel();
        employee.setName("王四");
        employee.setHiredate(LocalDate.of(2022, 1, 1));
        employee.setGender("男");
        employee.setDepartment(1);
        employeeMapper1.addEmployee(employee);
    }

    // 删除员工数据
    @Test
    public void deleteEmployeeTest() {
        Integer targetId = 4; // 把数字换成你想删除的员工id号码
        int rows = employeeMapper1.deleteEmployeeById(targetId);
        System.out.println("删除结果：" + rows);
    }

    // 修改员工数据
    @Test
    public void updateEmployeeTest() {
        EmployeeModel employee = new EmployeeModel();
        employee.setId(3); // id索引
        // 需要修改的员工主键
        employee.setName("修改test");
        employee.setHiredate(LocalDate.of(2023, 5, 20));
        employee.setGender("男");
        employee.setDepartment(1);
        employeeMapper1.updateEmployee(employee);
    }

    // 模糊查询
    @Test
    public void findEmployeesByNameTest() {
        String keyword = "王"; // 查询内容
        List<EmployeeModel> employees = employeeMapper1.findEmployeesByName(keyword);
        System.out.println(employees);
    }

    // 日期查询
    @Test
    public void findEmployeesByHireDateBetweenTest() {
        LocalDate start = LocalDate.of(2021, 1, 1); // Start Date
        LocalDate end = LocalDate.of(2021, 12, 31); // End Date
        List<EmployeeModel> employees = employeeMapper1.findEmployeesByHireDateBetween(start, end);
        System.out.println(employees);
    }


}

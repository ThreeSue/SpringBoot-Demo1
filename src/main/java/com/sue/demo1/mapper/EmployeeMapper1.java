package com.sue.demo1.mapper;

import com.sue.demo1.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author: 2412-21-苏世睿
 * @Description : [执行数据库增删改查层]
 * @Version : [v1.1]
 * @Date: 2026/3/17
 * @Time: 17:16
 */

@Repository // 标识这是一个数据访问层组件，Spring会自动扫描并注册这个类为一个Bean
public class EmployeeMapper1 {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    // 注入JdbcTemplate对象，JdbcTemplate是Spring提供的一个用于操作数据库的工具类，可以简化数据库操作

    // 查询员工列表
    public List<EmployeeModel> getEmployeeList() {
        String sql = "select * from employee";
        List<EmployeeModel> query = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EmployeeModel.class));
        return query;
    }

    // 新增员工数据
    public void addEmployee(EmployeeModel employeeModel) {
        String sql = "insert into employee (name, hiredate, gender, department) values (?, ?, ?, ?)";
        jdbcTemplate.update(
                sql,
                employeeModel.getName(),
                employeeModel.getHiredate(),
                employeeModel.getGender(),
                employeeModel.getDepartment()
        );

    }

    // 删除员工数据
    public int deleteEmployeeById(Integer id) {
        String sql = "delete from employee where id = ?";
        return jdbcTemplate.update(sql, id);
    }

    // 修改员工数据
    public void updateEmployee(EmployeeModel employeeModel) {
        String sql = "update employee set name = ?, hiredate = ?, gender = ?, department = ? where id = ?";
        jdbcTemplate.update(
                sql,
                employeeModel.getName(),
                employeeModel.getHiredate(),
                employeeModel.getGender(),
                employeeModel.getDepartment(),
                employeeModel.getId()
        );
    }

    // 模糊查询
    public List<EmployeeModel> findEmployeesByName(String keyword) {
        String sql = "select * from employee where name like ?";
        String pattern = "%" + keyword + "%"; // "%李%"
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EmployeeModel.class), pattern);
    }

    // 根据日期查询
    public List<EmployeeModel> findEmployeesByHireDateBetween(LocalDate start, LocalDate end) {
        String sql = "select * from employee where hiredate between ? and ?";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EmployeeModel.class), start, end);

    }
}





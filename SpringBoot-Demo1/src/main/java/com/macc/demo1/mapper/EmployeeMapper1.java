package com.macc.demo1.mapper;

import com.macc.demo1.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class EmployeeMapper1 {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<EmployeeModel> getEmpoloyeeList(){
        String sql = "Select * From employee";
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(EmployeeModel.class));
    }

    public void addEmployee(EmployeeModel employeeModel){
        String sql = "insert into employee(name,hiredate,gender,department) values(?,?,?,?)";
        int update = jdbcTemplate.update(sql, employeeModel.getName(), employeeModel.getHiredate(), employeeModel.getGender(), employeeModel.getDepartment());
        if (update > 0) {
            System.out.println("新增成功");
        } else {
            System.out.println("新增失败");
        }
    }

    // 查询 （修改）增删改

}

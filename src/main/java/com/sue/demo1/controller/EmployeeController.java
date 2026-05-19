package com.sue.demo1.controller;

import ch.qos.logback.core.util.StringUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sue.demo1.model.DepartmentModel;
import com.sue.demo1.model.EmployeeModel;
import com.sue.demo1.model.req.EmployeeSearchReq;
import com.sue.demo1.service.DepartmentService;
import com.sue.demo1.service.EmployeeService;
import com.sue.demo1.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 员工控制器
 */
@RestController
@RequestMapping("/employee")
//@CrossOrigin // 防止前后端 跨域 问题
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private DepartmentService departmentService;

    /**
     * 获取所有员工信息
     */
    @GetMapping
    public R<List<EmployeeModel>> getEmployeeList(EmployeeSearchReq req){
        System.out.println(req);
        String department = req.getDepartment();
        String gender = req.getGender();
        String name = req.getName();
//        lamba （Java lamba语法链式调用） 查询包装器
        LambdaQueryWrapper<EmployeeModel> wrapper = new LambdaQueryWrapper<>();
        // 第一个参数：判断是否要带上这个查询条件
        // 第二个参数：条件匹配的列
        // 第三个参数：查询的条件
        wrapper.like(StrUtil.isNotBlank(name),EmployeeModel::getName, name); //like模糊查询
        wrapper.eq(StrUtil.isNotBlank(gender),EmployeeModel::getGender, gender); //eq 精确查询
        wrapper.like(StrUtil.isNotBlank(department),EmployeeModel::getDepartment, department);
        // 日期范围查询    400 传入参数非法
        if(ArrayUtil.isNotEmpty(req.getHiredate())){
            wrapper.between(EmployeeModel::getHiredate,req.getHiredate()[0],req.getHiredate()[1]);
        }

        // 填充部门的名称
        List<EmployeeModel> empList = employeeService.list(wrapper);
        empList.forEach(item -> {
            // 部门ID
            Integer departmentId = item.getDepartment();
            DepartmentModel deptModel = departmentService.getById(departmentId);
            item.setDepartmentName(deptModel.getDepartmentName());

        });

        return R.ok(empList);
    }

    // application/json form-data

    /**
     * 添加员工信息
     * @param employeeModel
     */
    @PostMapping
    public R<Void> addEmployee(@RequestBody EmployeeModel employeeModel){
        employeeService.save(employeeModel);
        return R.ok();
    }

    /**
     * 根据员工id查询员工信息
     */
    @GetMapping("{id}")
    public R<EmployeeModel> getEmployeeById(@PathVariable Integer id){
        return R.ok(employeeService.getById(id));
    }

    /**
     * 修改员工信息
     * @param employeeModel
     */
    @PutMapping
    public R<Void> updateEmployee(@RequestBody EmployeeModel employeeModel){
        employeeService.updateById(employeeModel);
        return R.ok();
    }

    // body json @RequestBody
    // query参数传参 www.xxx?id?
    // Restfull （路径传参）

    /**
     * 删除员工信息
     * @param id 员工id
     */
    @DeleteMapping("{id}")
    public R<Void> delEmployee(@PathVariable Integer id){
        employeeService.removeById(id);
        return R.ok();
    }

//    下节课：聚合查询 联表查询 分组查询

}

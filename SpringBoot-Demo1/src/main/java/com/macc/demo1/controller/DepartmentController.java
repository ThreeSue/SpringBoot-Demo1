package com.macc.demo1.controller;

import com.macc.demo1.model.DepartmentModel;
import com.macc.demo1.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门控制器
 */
@RestController
@RequestMapping("/department")
@CrossOrigin // 允许所有请求访问跨域
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;


    /**
     * 查询所有部门列表
     * @return
     */
    @GetMapping
    public List<DepartmentModel> getDepartmentList(){
        return departmentService.list();
    }

    /**
     * 根据ID查询部门信息
     */
    @GetMapping("{id}")
    public DepartmentModel getDepartmentById(@PathVariable Long id){
        return departmentService.getById(id);
    }

    /**
     * 创建新部门
     */
    @PostMapping
    public void addDepartment(@RequestBody DepartmentModel departmentModel){
        departmentService.save(departmentModel);
    }

    /**
     * 修改部门信息
     */
    @PutMapping
    public void updateDepartment(@RequestBody DepartmentModel departmentModel){
        departmentService.updateById(departmentModel);
    }

    /**
     * 根据Id删除部门
     */
    @DeleteMapping("{id}")
    public void delDepartment(@PathVariable Long id){
        departmentService.removeById(id);
    }

}

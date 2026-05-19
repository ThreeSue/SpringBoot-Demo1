package com.sue.demo1.controller;

/**
 * @Author: 蘇世睿
 * @Class: 移動互聯2412
 * @SeatNo: 21
 * @Description : [一句话描述这个类的作用]
 * @Version : [v1.1]
 * @Date: 2026/3/25
 * @Time: 11:24
 */

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sue.demo1.model.DepartmentModel;
import com.sue.demo1.model.req.DepartmentSearchReq;
import com.sue.demo1.service.DepartmentService;
import com.sue.demo1.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 部门控制器
 */
@RestController
@RequestMapping("/department")
//@CrossOrigin
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    /**
     * 获取部门列表
     */
    @GetMapping
    public R<List<DepartmentModel>> getDepartmentList(DepartmentSearchReq req) {
//        String departmentName = req.getDepartmentName();
//        String manager = req.getManager();
//        String location = req.getLocation();
//        LambdaQueryWrapper<DepartmentModel> wrapper = new LambdaQueryWrapper<>();
//        wrapper.like(StrUtil.isNotBlank(departmentName), DepartmentModel::getDepartmentName, departmentName);
//        wrapper.like(StrUtil.isNotBlank(manager), DepartmentModel::getManager, manager);
//        wrapper.like(StrUtil.isNotBlank(location), DepartmentModel::getLocation, location);
//        return departmentService.list(wrapper);
        return R.ok(departmentService.list());
    }

    /**
     * 根据id查询部门信息
     */
    @GetMapping("{id}")
    public R<DepartmentModel> getDepartmentById(@PathVariable Integer id){
        return R.ok(departmentService.getById(id));
    }

    /**
     * 添加部门
     */
    @PostMapping
    public R<Void> addDepartment(@RequestBody DepartmentModel departmentModel) {
        departmentService.save(departmentModel);
        return R.ok();
    }


    /**
     * 修改部门
     */
    @PutMapping
    public R<Void> updateDepartment(@RequestBody DepartmentModel departmentModel) {
        departmentService.updateById(departmentModel);
        return R.ok();
    }

    /**
     * 根据id删除部门
     */
    @DeleteMapping("{id}")
    public R<Void> delDepartment(@PathVariable Integer id) {
        departmentService.removeById(id);
        return R.ok();
    }
    

}

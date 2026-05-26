package com.sue.demo1.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sue.demo1.model.SysUserModel;
import com.sue.demo1.service.SysUserService;
import com.sue.demo1.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: 蘇世睿
 * @Class: 移動互聯2412
 * @SeatNo: 21
 * @Description : [一句话描述这个类的作用]
 * @Version : [v1.1]
 * @Date: 2026/5/13
 * @Time: 11:51
 */

@RestController
@RequestMapping("/user")
//@CrossOrigin
public class UserController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping
    public R<List<SysUserModel>> getUserList(String account, String nickname) {
        QueryWrapper<SysUserModel> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(account), "account", account);
        wrapper.like(StrUtil.isNotBlank(nickname), "nickname", nickname);
        return R.ok(sysUserService.list(wrapper));
    }

    @PostMapping
    public R<Void> addUser(@RequestBody SysUserModel sysUserModel) {
        sysUserService.save(sysUserModel);
        return R.ok();
    }

    @PutMapping
    public R<Void> updateUser(@RequestBody SysUserModel sysUserModel) {
        sysUserService.updateById(sysUserModel);
        return R.ok();
    }

    @GetMapping("{id}")
    public R<SysUserModel> getUserById(@PathVariable Integer id) {
        return R.ok(sysUserService.getById(id));
    }

    @DeleteMapping("{id}")
    public R<Void> delUser(@PathVariable Integer id) {
        sysUserService.removeById(id);
        return R.ok();
    }
}

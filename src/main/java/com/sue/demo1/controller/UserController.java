package com.sue.demo1.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sue.demo1.model.UserModel;
import com.sue.demo1.service.UserService;
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
    private UserService userService;

    @GetMapping
    public R<List<UserModel>> getUserList(String account, String nickname) {
        QueryWrapper<UserModel> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(account), "account", account);
        wrapper.like(StrUtil.isNotBlank(nickname), "nickname", nickname);
        return R.ok(userService.list(wrapper));
    }

    @PostMapping
    public R<Void> addUser(@RequestBody UserModel userModel) {
        userService.save(userModel);
        return R.ok();
    }

    @PutMapping
    public R<Void> updateUser(@RequestBody UserModel userModel) {
        userService.updateById(userModel);
        return R.ok();
    }

    @GetMapping("{id}")
    public R<UserModel> getUserById(@PathVariable Integer id) {
        return R.ok(userService.getById(id));
    }

    @DeleteMapping("{id}")
    public R<Void> delUser(@PathVariable Integer id) {
        userService.removeById(id);
        return R.ok();
    }
}

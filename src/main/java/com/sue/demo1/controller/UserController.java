package com.sue.demo1.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sue.demo1.model.UserModel;
import com.sue.demo1.service.UserService;
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
    public List<UserModel> getUserList(String account, String nickname) {
        QueryWrapper<UserModel> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotBlank(account), "account", account);
        wrapper.like(StrUtil.isNotBlank(nickname), "nickname", nickname);
        return userService.list(wrapper);
    }

    @PostMapping
    public void addUser(@RequestBody UserModel userModel) {
        userService.save(userModel);
    }

    @PutMapping
    public void updateUser(@RequestBody UserModel userModel) {
        userService.updateById(userModel);
    }

    @GetMapping("{id}")
    public UserModel getUserById(@PathVariable Integer id) {
        return userService.getById(id);
    }

    @DeleteMapping("{id}")
    public void delUser(@PathVariable Integer id) {
        userService.removeById(id);
    }
}

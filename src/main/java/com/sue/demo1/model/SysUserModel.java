package com.sue.demo1.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @Author: 蘇世睿
 * @Class: 移動互聯2412
 * @SeatNo: 21
 * @Description : [一句话描述这个类的作用]
 * @Version : [v1.1]
 * @Date: 2026/5/13
 * @Time: 11:52
 */
@Data
@TableName("sys_user")
public class SysUserModel {

    @TableId(type = IdType.AUTO)
    private Integer id;

    private String account;

    private String password;

    private String passwordHash;

    private String nickname;

    private String avatar;
}



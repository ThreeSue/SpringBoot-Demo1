package com.sue.demo1.model.req;

import lombok.Data;

/**
 * @Author: 蘇世睿
 * @Class: 移動互聯2412
 * @SeatNo: 21
 * @Description : [一句话描述这个类的作用]
 * @Version : [v1.1]
 * @Date: 2026/5/20
 * @Time: 10:36
 */

@Data
public class RegisterReq {

    // 账号
    private String account;

    // 密码
    private String password;

    // 二次确认密码
    private String rePassword;

    // 验证码的uuid
    private String uuid;

    //
    private String code;
}

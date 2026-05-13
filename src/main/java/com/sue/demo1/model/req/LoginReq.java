package com.sue.demo1.model.req;

import lombok.Data;

/**
 * @Author: 蘇世睿
 * @Class: 移動互聯2412
 * @SeatNo: 21
 * @Description : [一句话描述这个类的作用]
 * @Version : [v1.1]
 * @Date: 2026/5/12
 * @Time: 16:18
 */

@Data
public class LoginReq {
    private String account;

    private String password;

    private String uuid;

    private String imageCode;

}


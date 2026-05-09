package com.sue.demo1.model.resp;

import lombok.Data;

/**
 * @Author: 蘇世睿
 * @Class: 移動互聯2412
 * @SeatNo: 21
 * @Description : [一句话描述这个类的作用]
 * @Version : [v1.1]
 * @Date: 2026/5/9
 * @Time: 16:54
 */

@Data
public class CaptchaResp {

    // 用户单次请求的唯一标识（用于区分不同用户请求的验证码）
    private String uuid;
    //
    private String img;


}

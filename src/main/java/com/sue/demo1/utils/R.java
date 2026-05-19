package com.sue.demo1.utils;

import lombok.Data;

/**
 * @Author: 蘇世睿
 * @Class: 移動互聯2412
 * @SeatNo: 21
 * @Description : [统一返回值]
 * @Version : [v1.1]
 * @Date: 2026/5/19
 * @Time: 17:11
 */
@Data
public class R<T> {

    // 200代表成goon 500代表失败
    private Integer code;

    // 返回数据
    private T data;
// 范型T 代表可能是任意的值
    // 错误提示
    private String msg;

    // 请求成功并且携带数据
    public static <T> R<T> ok(T data){
        R<T> r = new R();
        r.setCode(200);
        r.setMsg(null);
        r.setData(data);
        return r;
    }

    // 请求成功不携带数据
    public static R<Void> ok(){
        R<Void> r = new R<>();
        r.setCode(200);
        r.setMsg(null);
        r.setData(null);
        return r;
    }

    // 请求失败携带错误数据
    public static R<Void> fail(String msg){
        R<Void> r = new R<>();
        r.setCode(500);
        r.setMsg(msg);
        r.setData(null);
        return r;
    }
}

package com.sue.demo1.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.core.math.Calculator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import com.sue.demo1.model.req.LoginReq;
import com.sue.demo1.model.resp.CaptchaResp;
import jakarta.servlet.ServletResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.HashMap;

/**
 * @Author: 蘇世睿
 * @Class: 移動互聯2412
 * @SeatNo: 21
 * @Description : [一句话描述这个类的作用]
 * @Version : [v1.1]
 * @Date: 2026/5/9
 * @Time: 16:05
 */

@RestController
//@CrossOrigin
public class AuthController {

    private HashMap<String, Double> resultMap = new HashMap<>();

    @GetMapping("/genCaptcha")
    public CaptchaResp getCaptchaCode(ServletResponse response) throws IOException {
        // 定义验证码的类型 和生成的长度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100);
        // 设置生成器为则运算的形式(数字)
        captcha.setGenerator(new MathGenerator(1));
        String code = captcha.getCode();
        double result = Calculator.conversion(code);
        // 对象 -> uuid （唯一标识） 图片（让用户输入答案的验证码图片）
        CaptchaResp captchaResp = new CaptchaResp();
        // 唯一标识（在某个时间段内唯一）
        String uuid = IdUtil.fastSimpleUUID();
        resultMap.put(uuid, result);
        captchaResp.setUuid(uuid);
        // 想办法把图片用字符串表示（Base64编码）
        String imgBase64 = captcha.getImageBase64Data();
        captchaResp.setImg(imgBase64);
        return captchaResp;

    }

    //uuid account password imageCode
    @PostMapping("/login")
    public void login(@RequestBody LoginReq loginReq) {
        // 只关注数据
        // 关心我的 错误 错误提示
        // 引入一个统一返回值的类
        // data 数据 msg 错误提示 code 200/500 用于区分一个请求成功还是失败
        // 取出之前发验证码时的结果，准备与用户提交的验证码输入结果进行匹配
        try{
            Double codeResult = resultMap.get(loginReq.getUuid());
            String imageCode = loginReq.getImageCode();
            double inputCode = NumberUtil.parseDouble(imageCode);
            if (codeResult == inputCode) {
                System.out.println("验证码正确");
            } else {
                System.out.println("验证码错误");
            }
        } catch (RuntimeException e) {
            System.out.println("验证码错误");
        }



    }

}

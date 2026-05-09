package com.sue.demo1.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.core.util.IdUtil;
import com.sue.demo1.model.resp.CaptchaResp;
import jakarta.servlet.ServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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
public class AuthController {

    @GetMapping("/genCaptcha")
    public CaptchaResp getCaptchaCode(ServletResponse response) throws IOException {
        // 定义验证码的类型 和生成的长度
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100);
        // 设置生成器为则运算的形式(数字)
        captcha.setGenerator(new MathGenerator(1));

//        // 将验证码文件值输出到浏览器
//        captcha.write(response.getOutputStream());

        // 对象 -> uuid （唯一标识） 图片（让用户输入答案的验证码图片）
        CaptchaResp captchaResp = new CaptchaResp();
        // 唯一标识（在某个时间段内唯一）
        String uuid = IdUtil.fastSimpleUUID();
        captchaResp.setUuid(uuid);
        // 想办法把图片用字符串表示（Base64编码）
        String imgBase64 = captcha.getImageBase64Data();
        captchaResp.setImg(imgBase64);
        return captchaResp;

    }

}

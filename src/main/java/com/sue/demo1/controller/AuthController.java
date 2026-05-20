package com.sue.demo1.controller;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.ShearCaptcha;
import cn.hutool.captcha.generator.MathGenerator;
import cn.hutool.core.math.Calculator;
import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.sue.demo1.model.UserModel;
import com.sue.demo1.model.req.LoginReq;
import com.sue.demo1.model.req.RegisterReq;
import com.sue.demo1.model.resp.CaptchaResp;
import com.sue.demo1.service.UserService;
import com.sue.demo1.utils.EncryptUtils;
import com.sue.demo1.utils.R;
import jakarta.servlet.ServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
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

    // 登录的验证码
    private HashMap<String, Double> resultMap = new HashMap<>();

    // 注册的验证码
    private HashMap<String,String> registerMap = new HashMap<>();

    @Autowired
    private UserService userService;

    @GetMapping("/genCaptcha")
    public R<CaptchaResp> getCaptchaCode(ServletResponse response) throws IOException {
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
        return R.ok(captchaResp);

    }

    @GetMapping({ "/genRegisterCaptcha"})
    public R<CaptchaResp> genRegisterCaptcha(ServletResponse response) throws IOException {
        ShearCaptcha captcha = CaptchaUtil.createShearCaptcha(200, 100, 4, 10);
        String imageBase64 = captcha.getImageBase64Data();
        String code = captcha.getCode();
        String uuid = IdUtil.fastSimpleUUID();
        System.out.println("code=" + code);
        // 把结果存到map 方便后续注册时比对
        registerMap.put(uuid, code);
        // 组装验证码前端数据
        CaptchaResp captchaResp = new CaptchaResp();
        captchaResp.setImg(imageBase64);
        captchaResp.setUuid(uuid);
        return R.ok(captchaResp);
    }

    //uuid account password imageCode
    @PostMapping("/login")
    public R<Void> login(@RequestBody LoginReq loginReq) {
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
                return R.ok();
            } else {
                return R.fail("验证码错误");
            }
        } catch (RuntimeException e) {
            return R.fail("验证码错误");
        }
    }

    @PostMapping("/register")
    public R<Void> register(@RequestBody RegisterReq registerReq) {
        String account = registerReq.getAccount();
        String uuid = registerReq.getUuid();
        String code = registerReq.getCode();
        String password = registerReq.getPassword();
        String rePassword = registerReq.getRePassword();

        if (StrUtil.hasBlank(account, password, rePassword, uuid, code)) {
            return R.fail("注册信息不能为空");
        }

        // 1.账号是唯一的，且不可修改 查询数据库是否有相同账号，如果有相同账号要返回异常
        // 1-1 使用mybatis-plus 查询 account 是否已经在数据库了，如果已经在了就要抛出异常
        LambdaQueryWrapper<UserModel> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserModel::getAccount, account);
        long count = userService.count(wrapper);
        if (count > 0) {
            return R.fail("此账号已被注册");
        }

        // 2.校验账号（手机号）密码（字母大小写+数字+特殊符号） 是否符合格式 正则表达式（可以先不加）
        // 3.判断第一次输入的密码和第二次输入的密码是否一致
        if (!StrUtil.equals(password, rePassword)) {
            return R.fail("第一次输入的密码与第二次输入的密码不一致");
        }

        // 4.判断验证码是否正确
        String resultCode = registerMap.get(uuid);
        boolean equals = StrUtil.equalsIgnoreCase(code, resultCode);
        if (!equals) {
            return R.fail("验证码错误");
        }
        registerMap.remove(uuid);

        // 5.要将用户提交的原密码进行加密
        String encryptPassword = EncryptUtils.encrypt(password);

        // 6.调用 mybatis-plus的save方法插入数据库
        UserModel userModel = new UserModel();

        userModel.setAccount(account);
        userModel.setPassword(password);
        userModel.setPasswordHash(encryptPassword);

        boolean save = userService.save(userModel);
        if (!save) {
            return R.fail("注册失败");
        }
        return R.ok();
    }

}

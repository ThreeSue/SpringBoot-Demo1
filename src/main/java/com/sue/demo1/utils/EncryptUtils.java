package com.sue.demo1.utils;

import cn.hutool.crypto.symmetric.SymmetricAlgorithm;
import cn.hutool.crypto.symmetric.SymmetricCrypto;

/**
 * @Author: 蘇世睿
 * @Class: 移動互聯2412
 * @SeatNo: 21
 * @Description : [加密与解密]
 * @Version : [v1.1]
 * @Date: 2026/5/19
 * @Time: 16:35
 */
public class EncryptUtils {

    // 加密 解密
    private static String key = "移动互联2412";

    public static String encrypt(String content){
        // 根据密钥解密数据 -> 产出密文
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key.getBytes());
        return aes.encryptHex(content);

    }

    public static String decrypt(String encryptContent){
        // 根据密钥和密文解密 -> 产出明文
        SymmetricCrypto aes = new SymmetricCrypto(SymmetricAlgorithm.AES, key.getBytes());
        return aes.decryptStr(encryptContent);

    }

    static void main(String[] args) {
        // 注册 密码 -> 密文 -> 存到数据库>
        String password = "ThreeSue";
        String encryptPassword = EncryptUtils.encrypt(password);
        System.out.println("加密后的密码：" + encryptPassword);

        // 192526e27fd69672e1b93b8cdbae513c

        // 登录 用户输入明文 -> 去数据库解密 -> 匹配用户输入数据
        String decryptPassword = EncryptUtils.decrypt(encryptPassword);
        System.out.println("解密后的密码：" + decryptPassword);
    }
}

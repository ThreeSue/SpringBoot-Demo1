<script setup>
import http from '@/utils/http'
import { ref } from "vue";  
import axios from "axios";
const captchaImg = ref('')
const account = ref('');
const password = ref('');
const imageCode = ref('');
const uuid = ref('');



const getCaptcha = () => {
  http.get("/genCaptcha").then((resp) => {
    captchaImg.value = resp.img;
    uuid.value = resp.uuid;
  });
}

const login = () => {
  http.post("/login",{
    account: account.value,
    password: password.value,
    imageCode: imageCode.value,
    uuid: uuid.value,
  }).then((resp) => {});
}

getCaptcha()


</script>

<template>
  <div class="login-page">
    <el-card class="login-card">
      <template #header>
        <div class="login-title">登录</div>
      </template>

      <el-form label-width="80px">
        <el-form-item label="账号">
          <el-input v-model="account" placeholder="请输入账号" clearable />
        </el-form-item>

        <el-form-item label="密码">
          <el-input v-model="password" placeholder="请输入密码" show-password type="password" />
        </el-form-item>

        <el-form-item label="验证码">
          <div class="captcha-row">
            <el-input v-model="imageCode" placeholder="请输入验证码" clearable />
            <img 
            @click="getCaptcha"
            :src="captchaImg"
            class="captcha-img" 
            alt="验证码" 
            title="点击刷新验证码" 
            />
          </div>
        </el-form-item>

        <el-form-item>
          <el-button @click="login" type="primary" :loading="loading">登录</el-button>
          <!-- <el-button @click="getCaptcha">刷新验证码</el-button> -->
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<style scoped>
.login-page {
  display: flex;
  align-items: flex-start;
  justify-content: center;
  min-height: 70vh;
  padding-top: 80px;
}

.login-card {
  width: 420px;
}

.login-title {
  font-size: 20px;
  font-weight: 600;
  text-align: center;
}

.captcha-row {
  display: flex;
  gap: 12px;
  width: 100%;
}

.captcha-img {
  width: 120px;
  height: 40px;
  cursor: pointer;
  border: 1px solid #dcdfe6;
  border-radius: 4px;
  object-fit: cover;
}
</style>

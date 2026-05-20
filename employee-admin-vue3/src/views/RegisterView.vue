<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import http from '@/utils/http'

const router = useRouter()

const account = ref('')
const password = ref('')
const rePassword = ref('')
const code = ref('')
const uuid = ref('')
const captchaImg = ref('')

const getRegisterCaptcha = () => {
  http.get('/genRegisterCaptcha').then((resp) => {
    uuid.value = resp.uuid
    captchaImg.value = resp.img
  })
}

const register = () => {
  http.post('/register', {
    account: account.value,
    password: password.value,
    rePassword: rePassword.value,
    uuid: uuid.value,
    code: code.value,
  }).then(() => {
    ElMessage.success('注册成功')
    router.push('/login')
  }).catch(() => {
    getRegisterCaptcha()
  })
}

getRegisterCaptcha()
</script>

<template>
  <div class="register-page">
    <el-card class="register-card">
      <template #header>
        <div class="register-title">注册</div>
      </template>

      <el-form label-width="90px">
        <el-form-item label="账号">
          <el-input v-model="account" placeholder="请输入账号" clearable />
        </el-form-item>

        <el-form-item label="密码">
          <el-input v-model="password" placeholder="请输入密码" type="password" show-password />
        </el-form-item>

        <el-form-item label="确认密码">
          <el-input v-model="rePassword" placeholder="请再次输入密码" type="password" show-password />
        </el-form-item>

        <el-form-item label="验证码">
          <div class="captcha-row">
            <el-input v-model="code" placeholder="请输入验证码" clearable />
            <img
              :src="captchaImg"
              class="captcha-img"
              alt="验证码"
              title="点击刷新验证码"
              @click="getRegisterCaptcha"
            />
          </div>
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="register">注册</el-button>
          <el-button @click="router.push('/login')">返回登录</el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<style scoped>
.register-page {
  display: flex;
  justify-content: center;
  padding-top: 80px;
  min-height: 70vh;
}

.register-card {
  width: 430px;
}

.register-title {
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

<script setup>
  import {ref} from "vue"
  const isCollapse = ref(false)
</script>

<template>
<!--
active-text-color: 激活菜单的文字颜色
background-color: 菜单背景颜色
text-color: 文字颜色显示
default-active: 默认激活的menu 和 下方的el-menu-item进行联动
-->
  <el-menu
      router
      class="el-menu-custom"
      :collapse="isCollapse"
      active-text-color="#ffd04b"
      background-color="#545c64"
      default-active="employee"
      text-color="#fff"
  >
    <el-menu-item index="employee">
      <el-icon><UserFilled /></el-icon>
      <span>员工管理</span>
    </el-menu-item>
    <el-menu-item index="department">
      <el-icon><HomeFilled /></el-icon>
      <span>部门管理</span>
    </el-menu-item>
  </el-menu>
</template>

<style scoped>
.el-menu-custom {
  height: 100vh
}
</style>
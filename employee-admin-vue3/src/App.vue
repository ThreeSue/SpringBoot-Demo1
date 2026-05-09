<script setup>
import AsideLayout from "@/layout/aside/index.vue";

</script>

<template>
  <el-container class="app-layout">
    <el-aside class="app-aside" width="200px">
      <AsideLayout />
    </el-aside>
    <el-container>
      <el-header>Header</el-header>
      <el-main><router-view /></el-main>
    </el-container>
  </el-container>
</template>

<style scoped>
.app-layout {
  min-height: 100vh;
}

.app-aside {
  display: flex;
  flex-direction: column;
  background-color: #545c64;
}
</style>

<style>
#app {
  min-height: 100vh;
}
</style>

<script setup>
import AsideLayout from "@/layout/aside/index.vue";

</script>

<template>
  <el-container>
    <el-aside width="200px">

      <AsideLayout/>

    </el-aside>
    <el-container>
      <el-header>Header</el-header>
      <el-main><router-view/></el-main>
    </el-container>
  </el-container>
</template>

<style scoped>



</style>

<script setup>

import axios from 'axios'
import {ref} from 'vue'
import {ElMessage, ElMessageBox} from "element-plus";

// Promise
// then 成功
// catch 失败

const searchForm = ref({
  departmentName: '',
  manager: '',
  location: '',
})

const fetchData = () =>{
  axios.get("http://localhost:9090/department",{
    params:{
      departmentName:searchForm.value.departmentName,
      manager:searchForm.value.manager,
      location:searchForm.value.location
    }
  }).then((resp) => {
    tableData.value = resp.data
  })
}

fetchData()

const handleDelete = (scope) => {
  ElMessageBox.confirm(
      `是否确认删除【${scope.row.departmentName}】部门？`,
      '删除部门',
      {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
      })
      .then(() => {
        const id = scope.row.id
        console.log(scope)
        console.log("id",scope.row.id)
        console.log("id",scope.row.departmentName)
        axios.delete(`http://localhost:9090/department/${id}`).then(() => {
          ElMessage({
            message: '部门删除成功',
            type: 'success',
          });
          // 删除成功后刷新列表
          fetchData();
        })
      })
      .catch(() => {});
}

const tableData = ref([])

const dialogVisible = ref(false)

const dialogTitle = ref('新增部门')

const form = ref({
  departmentName: '',
  manager: '',
  location: '',
})

const handleAdd = () => {

  if(form.value.id){
    axios.put("http://localhost:9090/department", form.value).then(() => {
      ElMessage({
        message: '部门修改成功',
        type: 'success',
      });
      dialogVisible.value = false;
      fetchData();
    })
  }else{
    axios.post("http://localhost:9090/department", form.value).then(() => {
      ElMessage({
        message: '部门新增成功',
        type: 'success',
      });
      dialogVisible.value = false;
      fetchData();
    })
  }
    
}

const openDialog = (id) => {

  form.value = {
    departmentName: '',
    manager: '',
    location: '',
  }

  if(id){
    dialogTitle.value = '修改部门';
    axios.get(`http://localhost:9090/department/${id}`).then((resp) => {
      form.value = resp.data;
      dialogVisible.value = true;
    })
  }else{
    dialogTitle.value = '新增部门';
    dialogVisible.value = true;
  }
}
</script>

<template>

  <el-form inline>
    <el-form-item label="负责人姓名">
      <el-input v-model="searchForm.manager" placeholder="请填写负责人姓名" />
    </el-form-item>

    <el-form-item label="部门">
      <el-input v-model="searchForm.departmentName" placeholder="请输入部门" />
    </el-form-item>

    <el-form-item label="地址">
      <el-input v-model="searchForm.location" placeholder="请输入地址" />
    </el-form-item>

    <el-form-item>
      <el-button type="primary" @click="fetchData()">搜索</el-button>
      <el-button type="primary" @click="fetchData()">重置</el-button>
    </el-form-item>

  </el-form>

  <el-button type="primary" @click="openDialog()">新增部门</el-button>
  <el-table :data="tableData" style="width: 100%" align="center">
    <el-table-column prop="id" label="#ID" align="center"/>
    <el-table-column prop="departmentName" label="部门名称" align="center"/>
    <el-table-column prop="manager" label="负责人" align="center"/>
    <el-table-column prop="location" label="部门地址" align="center"/>
    <el-table-column fixed="right" label="操作" align="center">
      <template #default="scope">
        <el-button link type="primary" size="small" @click="openDialog(scope.row.id)">
          修改
        </el-button>
        <el-button @click="handleDelete(scope)" link type="danger" size="small">
          删除
        </el-button>
      </template>
    </el-table-column>
  
  </el-table>

  <el-dialog v-model="dialogVisible" title="新增部门" width="45%">

    <el-form label-width="auto">
      <el-form-item label="部门名称" placeholder="请填写部门名称">
        <el-input v-model="form.departmentName" />
      </el-form-item>
      <el-form-item label="负责人" placeholder="请填写负责人">
        <el-input v-model="form.manager" />
      </el-form-item>
      <el-form-item label="部门地址" placeholder="请填写部门地址">
        <el-input v-model="form.location" />
      </el-form-item>
    </el-form>

    <template #footer>
      <el-button @click="dialogVisible = false">取消</el-button>
      <el-button type="primary" @click="handleAdd">确定</el-button>
    </template>

  </el-dialog>



</template>

<style scoped>

</style>
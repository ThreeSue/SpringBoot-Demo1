<script setup>
import http from '@/utils/http'
import axios from 'axios'
import { ref } from 'vue'
import { ElMessage, ElMessageBox } from "element-plus";
import { Search, Refresh } from '@element-plus/icons-vue'

// Promise
// then 成功
// catch 失败

const searchForm = ref({
  name: '',
  hiredate: '',
  gender: '',
  department: '',

})

const fetchData = () => {
  // 获取到用户输入的筛选内容
  http.get("/employee", {
    params: {
      name: searchForm.value.name,
      gender: searchForm.value.gender,
      department: searchForm.value.department,
      hiredate: searchForm.value.hiredate
    }
  }).then((resp) => {
    console.log(resp)
    tableData.value = resp
  })
}
// 页面加载时先调用一次fetchData函数，获取数据
fetchData()

const handleDelete = (scope) => {

  ElMessageBox.confirm(
    `是否确认删除【${scope.row.name}】员工？`,
    '删除员工',
    {
      confirmButtonText: '确定',
      cancelButtonText: '取消',
      type: 'warning',
    })
    .then(() => {
      const id = scope.row.id
      console.log(scope)
      console.log("id", scope.row.id)
      console.log("id", scope.row.name)
      http.delete(`/employee/${id}`).then(() => {
        ElMessage({
          message: '员工删除成功',
          type: 'success',
        });
        // 删除成功后刷新列表
        fetchData();
      })
    })
    .catch(() => {

    });
}

const tableData = ref([])

const dialogVisible = ref(false)

const dialogTitle = ref('新增员工')

const form = ref({
  name: '',
  hiredate: '',
  gender: '',
  department: '',
})



const handleAdd = () => {

  if (form.value.id) {
    http.put("/employee", form.value).then(() => {
      // 提示
      ElMessage({
        message: '员工修改成功',
        type: 'success',
      });
      // 关闭弹窗
      dialogVisible.value = false;
      // 新增成功后刷新列表
      fetchData();
    })
  } else {
    http.post("/employee", form.value).then(() => {
      // 提示
      ElMessage({
        message: '员工新增成功',
        type: 'success',
      });
      // 关闭弹窗
      dialogVisible.value = false;
      // 新增成功后刷新列表
      fetchData();

    })

  }
}

const openDialog = (id) => {
  // 先清空form
  form.value = {
    name: '',
    hiredate: '',
    gender: '',
    department: '',
  }

  if (id) {
    dialogTitle.value = '修改员工'
    // id有值，走修改逻辑 resp就是employeeModel对象
    http.get(`/employee/${id}`).then((resp) => {
      form.value = resp // 将employeeModel对象赋值给form
      dialogVisible.value = true
    })
  } else {
    // id没有值，走新增逻辑
    dialogTitle.value = '新增员工'
    dialogVisible.value = true
  }
}

const reset = () => {
  searchForm.value = {
    name: '',
    hiredate: '',
    gender: '',
    department: '',
  };
  fetchData()
}




const deptData = ref([])

const fetchDeptData = () =>{
  http.get("/department").then((resp) => {
    deptData.value = resp
    console.log(deptData.value);
  })
  
}
fetchDeptData()

</script>

<template>

  <el-form inline>

    <el-form-item label="员工姓名">
      <el-input v-model="searchForm.name" placeholder="请填写员工姓名" />
    </el-form-item>

    <el-form-item label="员工部门">
      <el-input v-model="searchForm.department" placeholder="员工部门" />

    </el-form-item>

    <el-form-item label="入职日期">
      <el-date-picker value-format="YYYY-MM-DD" v-model="searchForm.hiredate" type="daterange" range-separator="To"
        start-placeholder="Start date" end-placeholder="End date" :size="size" />
    </el-form-item>

    <el-form-item label="性别">
      <el-radio-group v-model="searchForm.gender">
        <el-radio value="男">男</el-radio>
        <el-radio value="女">女</el-radio>
      </el-radio-group>
    </el-form-item>

    <el-form-item>
      <el-button type="primary" :icon="Search" @click="fetchData()">搜索</el-button>
      <el-button type="info" :icon="Refresh" @click="reset()">重置</el-button>
    </el-form-item>
  </el-form>

  <el-button type="primary" @click="openDialog()">新增员工</el-button>
  <el-table :data="tableData" style="width: 100%" align="center">
    <el-table-column prop="id" label="#ID" align="center" />
    <el-table-column prop="name" label="员工姓名" align="center" />
    <el-table-column prop="hiredate" label="入职日期" align="center" />
    <el-table-column prop="gender" label="性别" align="center" />
    <el-table-column prop="departmentName" label="部门" align="center" />
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

  <el-dialog v-model="dialogVisible" :title="dialogTitle" width="45%">
    <el-form label-width="auto">

      <el-form-item label="员工姓名" placeholder="请填写员工姓名">
        <el-input v-model="form.name" />
      </el-form-item>

      <el-form-item label="入职日期">
        <el-date-picker v-model="form.hiredate" type="date" placeholder="请填写入职日期" style="width: 100%" />
      </el-form-item>

      <el-form-item label="性别">
        <el-radio-group v-model="form.gender">
          <el-radio value="男">男</el-radio>
          <el-radio value="女">女</el-radio>
        </el-radio-group>
      </el-form-item>

      <el-form-item label="员工部门">
        <!-- <el-input v-model="form.department" placeholder="请员工部门" /> -->
        <el-select v-model="form.department" placeholder="Select" style="width: 240px">
          <el-option v-for="item in deptData" 
          :key="item.id" 
          :label="item.departmentName" 
          :value="item.id" />
        </el-select>
      </el-form-item>

    </el-form>

    <template #footer>
      <el-button @click="dialogVisible = false">取消</el-button>
      <el-button type="primary" @click="handleAdd">确定</el-button>
    </template>

  </el-dialog>

</template>

<style scoped></style>
<script setup>
import axios from "axios";
import { ref } from "vue";
import { ElMessage, ElMessageBox } from "element-plus";
const tableData = ref([]);

// 获取所有的部门信息
const fetchData = () => {
  axios.get("http://localhost:9090/department").then((resp) => {
    tableData.value = resp.data;
  });
};

fetchData();

const handleClick = () => {
  console.log("click");
};

const handleDelete = (id, departmentName) => {
  ElMessageBox.confirm(`是否确定删除【${departmentName}】部门`, "删除提示", {
    confirmButtonText: "确认",
    cancelButtonText: "取消",
    type: "warning",
  })
    .then(() => {
      axios.delete("http://localhost:9090/department/" + id).then((resp) => {
        ElMessage({
          message: "删除成功",
          type: "success",
        });
        // 刷新部门信息
        fetchData();
      });
    })
    .catch(() => {});
};
// 打开填写弹窗
const dialogTitle = ref("新增部门");
const openAddDialog = (id) => {
  if (id) {
    dialogTitle.value = "修改部门";
    axios.get("http://localhost:9090/department/" + id).then((resp) => {
      console.log(resp.data);
      form.value = resp.data;
    });
  } else {
    dialogTitle.value = "新增部门";
  }
  dialogVisible.value = true;
  // 清空表单
  form.value = {
    departmentName: "",
    location: "",
    manager: "",
  };
};

const handleAdd = () => {
  if (form.value.id) {
    axios.put("http://localhost:9090/department", form.value).then((resp) => {
      ElMessage({
        message: "修改成功",
        type: "success",
      });
      dialogVisible.value = false;
      fetchData();
    });
  } else {
    axios.post("http://localhost:9090/department", form.value).then((resp) => {
      // 提示新增成功
      ElMessage({
        message: "新增成功",
        type: "success",
      });
      // 关闭弹窗
      dialogVisible.value = false;
      // 刷新部门信息
      fetchData();
    });
  }
};

const dialogVisible = ref(false);

const form = ref({
  departmentName: "",
  location: "",
  manager: "",
});
</script>

<template>
  <el-button type="primary" @click="openAddDialog()">新增部门</el-button>

  <el-table :data="tableData" style="width: 100%">
    <el-table-column prop="id" label="#ID" align="center" />
    <el-table-column prop="departmentName" label="部门名称" align="center" />
    <el-table-column prop="location" label="部门地址" align="center" />
    <el-table-column prop="manager" label="管理者" align="center" />
    <el-table-column fixed="right" label="操作" align="center">
      <template #default="scope">
        <el-button
          link
          type="primary"
          size="small"
          @click="openAddDialog(scope.row.id)"
        >
          修改
        </el-button>
        <el-button
          @click="handleDelete(scope.row.id, scope.row.departmentName)"
          link
          type="danger"
          size="small"
          >删除</el-button
        >
      </template>
    </el-table-column>
  </el-table>

  <el-dialog v-model="dialogVisible" :title="dialogTitle" width="45%">
    <el-form label-width="auto">
      <el-form-item label="部门名称">
        <el-input v-model="form.departmentName" />
      </el-form-item>
      <el-form-item label="部门地址">
        <el-input v-model="form.location" />
      </el-form-item>
      <el-form-item label="管理者">
        <el-input v-model="form.manager" />
      </el-form-item>
    </el-form>
    <template #footer>
      <el-button @click="dialogVisible = false">取消</el-button>
      <el-button type="primary" @click="handleAdd">确认</el-button>
    </template>
  </el-dialog>
</template>

<style scoped>
</style>
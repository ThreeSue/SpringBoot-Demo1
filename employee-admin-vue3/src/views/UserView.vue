<script setup>
import http from '@/utils/http'
import axios from 'axios'
import { ref } from 'vue'
import { ElMessage, ElMessageBox } from 'element-plus'
import { Search, Refresh } from '@element-plus/icons-vue'

const searchForm = ref({
	account: '',
	nickname: '',
})

const tableData = ref([])

const fetchData = () => {
	http
		.get('/user', {
			params: {
				account: searchForm.value.account,
				nickname: searchForm.value.nickname,
			},
		})
		.then((resp) => {
			tableData.value = resp
		})
}

fetchData()

const reset = () => {
	searchForm.value = {
		account: '',
		nickname: '',
	}
	fetchData()
}

const dialogVisible = ref(false)
const dialogTitle = ref('新增用户')
const submitLoading = ref(false)

const form = ref({
	account: '',
	nickname: '',
	password: '',
})

const openDialog = (id) => {
	form.value = {
		account: '',
		nickname: '',
		password: '',
	}

	if (id) {
		dialogTitle.value = '修改用户'
		http.get(`/user/${id}`).then((resp) => {
			form.value = resp
			dialogVisible.value = true
		})
	} else {
		dialogTitle.value = '新增用户'
		dialogVisible.value = true
	}
}

const handleSubmit = () => {
	submitLoading.value = true

	if (form.value.id) {
		http
			.put('/user', form.value)
			.then(() => {
				ElMessage({
					message: '用户修改成功',
					type: 'success',
				})
				dialogVisible.value = false
				fetchData()
			})
			.catch(() => {
				ElMessage.error('用户修改失败，请确认后端已重启并支持 PUT /user')
			})
			.finally(() => {
				submitLoading.value = false
			})
	} else {
		http
			.post('/user', form.value)
			.then(() => {
				ElMessage({
					message: '用户新增成功',
					type: 'success',
				})
				dialogVisible.value = false
				fetchData()
			})
			.catch(() => {
				ElMessage.error('用户新增失败')
			})
			.finally(() => {
				submitLoading.value = false
			})
	}
}

const handleDelete = (scope) => {
	ElMessageBox.confirm(`是否确认删除【${scope.row.account}】用户？`, '删除用户', {
		confirmButtonText: '确定',
		cancelButtonText: '取消',
		type: 'warning',
	})
		.then(() => {
			http.delete(`/user/${scope.row.id}`).then(() => {
				ElMessage({
					message: '用户删除成功',
					type: 'success',
				})
				fetchData()
			})
		})
		.catch(() => {})
}
</script>

<template>
	<el-form inline>
		<el-form-item label="账号">
			<el-input v-model="searchForm.account" placeholder="请输入账号" />
		</el-form-item>

		<el-form-item label="昵称">
			<el-input v-model="searchForm.nickname" placeholder="请输入昵称" />
		</el-form-item>

		<el-form-item>
			<el-button type="primary" :icon="Search" @click="fetchData()">搜索</el-button>
			<el-button type="info" :icon="Refresh" @click="reset()">重置</el-button>
		</el-form-item>
	</el-form>

	<el-button type="primary" @click="openDialog()">新增用户</el-button>

	<el-table :data="tableData" style="width: 100%" align="center">
		<el-table-column prop="id" label="#ID" align="center" />
		<el-table-column prop="account" label="账号" align="center" />
		<el-table-column prop="nickname" label="昵称" align="center" />
		<el-table-column prop="password" label="密码" align="center" />
		<el-table-column fixed="right" label="操作" align="center">
			<template #default="scope">
				<el-button link type="primary" size="small" @click="openDialog(scope.row.id)">修改</el-button>
				<el-button link type="danger" size="small" @click="handleDelete(scope)">删除</el-button>
			</template>
		</el-table-column>
	</el-table>

	<el-dialog v-model="dialogVisible" :title="dialogTitle" width="45%">
		<el-form label-width="auto">
			<el-form-item label="账号">
				<el-input v-model="form.account" placeholder="请输入账号" />
			</el-form-item>

			<el-form-item label="昵称">
				<el-input v-model="form.nickname" placeholder="请输入昵称" />
			</el-form-item>

			<el-form-item label="密码">
				<el-input v-model="form.password" placeholder="请输入密码" />
			</el-form-item>
		</el-form>

		<template #footer>
			<el-button @click="dialogVisible = false">取消</el-button>
			<el-button type="primary" :loading="submitLoading" @click="handleSubmit">确定</el-button>
		</template>
	</el-dialog>
</template>

<style scoped></style>

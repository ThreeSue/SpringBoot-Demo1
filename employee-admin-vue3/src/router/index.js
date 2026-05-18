import { createWebHashHistory, createRouter } from 'vue-router'

import EmployeeView from '@/views/EmployeeView.vue'
import DepartmentView from '@/views/DepartmentView.vue'
import LoginView from '@/views/LoginView.vue'
import MainLayout from '@/layout/MainLayout.vue'
import UserView from "@/views/UserView.vue";
// 子路由
// employee department 需要一个父级 父级就是布局文件 
//  login 不需要父级 可以直接渲染
const routes = [
    { path: '/', component: MainLayout,children: [
        { path: '/employee', component: EmployeeView },
        { path: '/department', component: DepartmentView },
        { path: '/user', component: UserView }

    ]},
    { path: '/login', component: LoginView },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes,
})

export default router
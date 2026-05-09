import { createWebHashHistory, createRouter } from 'vue-router'

import EmployeeView from '@/views/EmployeeView.vue'
import DepartmentView from '@/views/DepartmentView.vue'

const routes = [
    { path: '/', redirect:'/employee'},
    { path: '/employee', component: EmployeeView },
    { path: '/department', component: DepartmentView },
]

const router = createRouter({
    history: createWebHashHistory(),
    routes,
})

export default router
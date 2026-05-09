import { createApp } from 'vue'
import ElementPlus from 'element-plus'
// 引入重置样式表
import 'normalize.css'
// 引入element-plus 的样式代码
import 'element-plus/dist/index.css'
// 引入element-plus icon 组件
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
// 引入vue-router
import router from "@/router"
import App from './App.vue'

const app = createApp(App)
// 将所有的icon组件全局注册，令icon不使用import导入即可使用
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}

app.use(router)
app.use(ElementPlus)
app.mount('#app')
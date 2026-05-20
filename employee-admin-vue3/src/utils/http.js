import axios from 'axios'
import { ElMessage  } from 'element-plus';

const http = axios.create({
    baseURL: 'http://localhost:9090',
    timeout: 5000,
})

// 添加响应拦截器
http.interceptors.response.use(function (response) {

    if (response.data.code !== 200) {
        ElMessage.error(response.data.msg);
        return Promise.reject(response.data);
    }

    return response.data.data;
  }, function (error) {
    ElMessage.error('请求失败');
    return Promise.reject(error);
  });

export default http;

// 导入axios
import axios from 'axios'

// 封装自己的get/post方法
export default {
  get: function (path = '', data = {}) {
    return new Promise(function (resolve, reject) {
      axios.get(path, {
        params: data
      })
        .then(function (response) {
          resolve(response.data)
        })
        .catch(function (error) {
          reject(error)
        })
    })
  },
  post: function (path = '', data = {}) {
    return new Promise(function (resolve, reject) {
      axios.post(path, data)
        .then(function (response) {
          resolve(response)
        })
        .catch(function (error) {
          reject(error)
        })
    })
  },
  delete: function (path = '', data = {}) {
    return new Promise(function (resolve, reject) {
      axios.delete(path, { data: data })
        .then(function (response) {
          resolve(response.data)
        })
        .catch(function (error) {
          reject(error)
        })
    })
  },
  put: function (path = '', data = {}) {
    return new Promise(function (resolve, reject) {
      axios.put(path, data)
        .then(function (response) {
          resolve(response.data)
        })
        .catch(function (error) {
          reject(error)
        })
    })
  },
  all: function (list) {
    return new Promise(function (resolve, reject) {
      axios.all(list)
        .then(axios.spread(function (...result) {
          // 两个请求现在都执行完成
          resolve(result)
        }))
        .catch(function (err) {
          reject(err)
        })
    })
  }
}

// let count = 0
// // 添加请求拦截器
// axios.interceptors.request.use(function(config) {
//   // 在发送请求之前做些什么
//   // 把token带入请求头中
//   config.headers.Authorization = sessionStorage.getItem('token');
//   count++;
//   // 调用网络加载指示器loading插件显示
//   that.$loading({
//     lock: true,
//     text: '正在加载中...',
//     // spinner: 'el-icon-loading',
//     background: 'rgba(0, 0, 0, 0.7)'
//   });
//   /*  const curPath = config.url || '';
//   const curMethod = config.method || '';
//   if (curPath.startsWith('/api/v1')) {
//     const flag = isRequest(actionRights, curPath, curMethod);
//     if (!flag) {
//       config.cancelToken = source.token;
//       source.cancel('没有对应的请求权限');
//       that.$message.error('没有对应的请求权限');
//     }
//   } */
//   return config;
// }, (error) => {
//   // 对请求错误做些什么
//   that.$nextTick(() => { // 以服务的方式调用的 Loading 需要异步关闭
//     that.$loading().close();
//   });
//   return Promise.reject(error);
// });
//
// // 添加响应拦截器
// axios.interceptors.response.use(function(response) {
//   // 对响应数据做点什么
//   count--;
//   if (count === 0) {
//     // 调用网络加载指示器loading插件隐藏
//     that.$nextTick(() => { // 以服务的方式调用的 Loading 需要异步关闭
//       that.$loading().close();
//     });
//   }
//   return response;
// }, (error) => {
//   // 对响应错误做点什么
//   that.$nextTick(() => { // 以服务的方式调用的 Loading 需要异步关闭
//     that.$loading().close();
//   });
//   return Promise.reject(error);
// });

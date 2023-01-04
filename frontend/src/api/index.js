// 导入封装好的网络请求类工具
import Network from './network'

// 用户注册登录相关结构
export const register = (data) => Network.post('/register', data)
export const login = (data) => Network.post('/login', data)

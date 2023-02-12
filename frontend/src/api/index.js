// 导入封装好的网络请求类工具
import Network from './network'

// 用户注册登录相关结构
export const register = (data) => Network.post('/register', data)
export const login = (data) => Network.post('/login', data)

// 问卷采集相关接口
export const uploadInformation = (data) => Network.post('/api/questionnaire/information', data)
export const uploadFeedback = (data) => Network.post('/api/questionnaire/feedback', data)

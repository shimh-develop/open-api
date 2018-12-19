/**
 * http配置
 */

import axios from 'axios'
import router from '@/router/index'
import CONSTANT from '@/constant'
import store from '@/store'
import {getToken, setToken} from '@/token'
import {Message} from 'element-ui'

// axios 配置
axios.defaults.timeout = 20000;
axios.defaults.baseURL = DocConfig.server;

// http request 拦截器
axios.interceptors.request.use(config => {
  if (getToken()) {
    config.headers[CONSTANT.TOKEN_REQUEST_NAME] = getToken()
  }
  return config;
}, err => {
  return Promise.reject(err);
});

// http response 拦截器
axios.interceptors.response.use(response => {
  // 新token
  if (response.headers[CONSTANT.TOKEN_RESPONSE_NAME]) {

    setToken(response.headers[CONSTANT.TOKEN_RESPONSE_NAME])
    store.commit('SET_TOKEN', response.headers[CONSTANT.TOKEN_RESPONSE_NAME])
  }

  const data = response.data;

  if (data.error_code === 20001) {
    store.dispatch('fedLogOut')
    Message({
      type: 'warning',
      showClose: true,
      message: '未登录，请登录哦'
    })

    return Promise.reject('error');
  }

  if (data.error_code === 30001) {
    Message({
      type: 'warning',
      showClose: true,
      message: '服务异常'
    })

    return Promise.reject('error');
  }

  if (data.error_code === 70003) {
    store.dispatch('fedLogOut')
    Message({
      type: 'warning',
      showClose: true,
      message: '登录超时，请重新登录哦'
    })
    return Promise.reject('error');
  }

  if (data.error_code === 70001) {
    router.push({
      path: '/noAccessPermission'
    });
    return Promise.reject('error');
  }
  return response;

}, error => {
  return Promise.reject(error.response.data)
});

export default axios;

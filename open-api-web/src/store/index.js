import Vuex from 'vuex'
import Vue from 'vue'
import {getToken, setToken, removeToken} from '@/token'
import CONSTANT from '@/constant'
import axios from '@/http'

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    id: '',
    username: '',
    name: '',
    avatar: '',
    admin: '',
    token: getToken(),
  },
  mutations: {
    SET_TOKEN: (state, token) => {
      state.token = token;
    },
    SET_USERNAME: (state, username) => {
      state.username = username
    },
    SET_NAME: (state, name) => {
      state.name = name
    },
    SET_AVATAR: (state, avatar) => {
      state.avatar = avatar
    },
    SET_ADMIN: (state, admin) => {
      state.admin = admin
    },
    SET_ID: (state, id) => {
      state.id = id
    }
  },
  actions: {
    login({commit}, user) {
      return new Promise((resolve, reject) => {
        var url = DocConfig.server+'/auth/login';
        var params = new URLSearchParams();
        params.append('username', user.username);
        params.append('password', user.password);
        axios.post(url, params).then(res => {
          if(res.data.error_code === 0) {
            commit('SET_TOKEN', res.data.data[CONSTANT.TOKEN_RESPONSE_NAME])
            setToken(res.data.data[CONSTANT.TOKEN_RESPONSE_NAME])
          }
          resolve(res)
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 获取用户信息
    getUserInfo({commit, state}) {
      let that = this
      return new Promise((resolve, reject) => {

        var url = DocConfig.server+'/user/info';
        axios.get(url).then(res => {
          if(res.data.error_code === 0) {
            const user = res.data.data
            if (user) {
              commit('SET_USERNAME', user.username)
              commit('SET_NAME', user.nickname)
              commit('SET_AVATAR', user.avatar)
              commit('SET_ADMIN', user.admin)
              commit('SET_ID', user.id)
            } else {
              commit('SET_USERNAME', '')
              commit('SET_NAME', '')
              commit('SET_AVATAR', '')
              commit('SET_ADMIN', '')
              commit('SET_ID', '')
              removeToken()
            }
          }
          resolve(res)
        }).catch(error => {
          reject(error)
        })
      })
    },
    // 退出
    logout({commit, state}) {
      return new Promise((resolve, reject) => {
        var url = DocConfig.server+'/auth/logout';
        axios.get(url).then(data => {
          resolve(data)
        }).catch(error => {
          reject(error)
        }).finally(() => {
          commit('SET_TOKEN', '')
          commit('SET_USERNAME', '')
          commit('SET_NAME', '')
          commit('SET_AVATAR', '')
          commit('SET_ADMIN', '')
          commit('SET_ID', '')
          removeToken()
        })
      })
    },
    // 前端 退出
    fedLogOut({commit}) {
      return new Promise(resolve => {
        commit('SET_TOKEN', '')
        commit('SET_USERNAME', '')
        commit('SET_NAME', '')
        commit('SET_AVATAR', '')
        commit('SET_ADMIN', '')
        commit('SET_ID', '')
        removeToken()
        resolve()
      }).catch(error => {
        reject(error)
      })
    },
    register({commit}, user) {
      return new Promise((resolve, reject) => {
        var url = DocConfig.server+'/auth/register';
        var params = new URLSearchParams();
        params.append('username', user.username);
        params.append('nickname', user.nickname);
        params.append('password', user.password);
        axios.post(url, params).then((data) => {
          commit('SET_TOKEN', data.data[CONSTANT.TOKEN_RESPONSE_NAME])
          setToken(data.data[CONSTANT.TOKEN_RESPONSE_NAME])
          resolve()
        }).catch((error) => {
          reject(error)
        })
      })
    }
  }
})

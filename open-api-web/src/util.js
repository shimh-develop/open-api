import store from '@/store'

//全局函数/变量
export default {
  install(Vue, options) {

    Vue.prototype.getRootPath = function () {
      return window.location.protocol + '//' + window.location.host + window.location.pathname
    }

    /*判断是否是移动设备*/
    Vue.prototype.isMobile = function () {
      return navigator.userAgent.match(/iPhone|iPad|iPod|Android|android|BlackBerry|IEMobile/i) ? true : false;
    }

    Vue.prototype.$login = function () {
      if(store.state.username) {
        return true
      }
      return false
    }

    Vue.prototype.$admin = function () {
      if(store.state.username && store.state.admin) {
        return true
      }
      return false
    }

  }
}

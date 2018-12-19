import Vue from 'vue'
import Router from 'vue-router'
import Index from '@/components/Index'
import UserLogin from '@/components/user/Login'
import UserSetting from '@/components/user/Setting'
import UserRegister from '@/components/user/Register'
import UserResetPassword from '@/components/user/ResetPassword'
import ResetPasswordByUrl from '@/components/user/ResetPasswordByUrl'
import ItemIndex from '@/components/item/Index'
import ItemAdd from '@/components/item/Add'
import ItemPassword from '@/components/item/Password'
import ItemShow from '@/components/item/show/Index'
import ItemExport from '@/components/item/export/Index'
import ItemSetting from '@/components/item/setting/Index'
import PageIndex from '@/components/page/Index'
import PageEdit from '@/components/page/edit/Index'
import PageDiff from '@/components/page/Diff'
import Catalog from '@/components/catalog/Index'
import Notice from '@/components/notice/Index'
import Admin from '@/components/admin/Index'
import Team from '@/components/Team/Index'
import TeamMember from '@/components/Team/Member'
import TeamItem from '@/components/Team/Item'

import OpenApi from '@/components/open/api/'
import NoAccessPermission from '@/components/NoAccessPermission'

import { getToken} from "../token";

import { Message } from 'element-ui';

import store from '../store'

Vue.use(Router)

const router = new Router({
  routes: [
    // {
    //   path: '/',
    //   name: 'Index',
    //   component: Index,
    //   children: [
    //     {
    //       path: '',
    //       name: "OpenApi",
    //       component: OpenApi
    //     }
    //   ]
    // },
    {
      path: '/',
      name: 'OpenApi',
      component: OpenApi
    },
    {
      path: '/noAccessPermission',
      name: 'NoAccessPermission',
      component: NoAccessPermission
    },
    {
      path: '/login',
      name: 'UserLogin',
      component: UserLogin
    },
    {
      path: '/user/setting',
      name: 'UserSetting',
      component: UserSetting,
      meta: {
        requireLogin: true
      }
    },
    {
      path: '/register',
      name: 'UserRegister',
      component: UserRegister
    },
    {
      path: '/user/resetPassword',
      name: 'UserResetPassword',
      component: UserResetPassword
    },
    {
      path: '/user/ResetPasswordByUrl',
      name: 'ResetPasswordByUrl',
      component: ResetPasswordByUrl
    },
    {
      path: '/item/index',
      name: 'ItemIndex',
      component: ItemIndex,
      meta: {
        requireLogin: true
      }
    },
    {
      path: '/item/add',
      name: 'ItemAdd',
      component: ItemAdd,
      meta: {
        requireLogin: true
      }
    },
    {
      path: '/item/password/:item_id',
      name: 'ItemPassword',
      component: ItemPassword
    },
    {
      path: '/:item_id',
      name: 'ItemShow',
      component: ItemShow
    },
    {
      path: '/item/export/:item_id',
      name: 'ItemExport',
      component: ItemExport
    },
    {
      path: '/item/setting/:item_id',
      name: 'ItemSetting',
      component: ItemSetting,
      meta: {
        requireLogin: true
      }
    },
    {
      path: '/page/:page_id',
      name: 'PageIndex',
      component: PageIndex
    },
    {
      path: '/page/edit/:item_id/:page_id',
      name: 'PageEdit',
      component: PageEdit,
      meta: {
        requireLogin: true
      }
    },
    {
      path: '/page/diff/:page_id/:page_history_id',
      name: 'PageDiff',
      component: PageDiff
    },
    {
      path: '/catalog/:item_id',
      name: 'Catalog',
      component: Catalog
    },
    {
      path: '/notice/index',
      name: 'Notice',
      component: Notice
    },
    {
      path: '/admin/index',
      name: 'Admin',
      component: Admin,
      meta: {
        requireLogin: true
      }
    },
    {
      path: '/team/index',
      name: 'Team',
      component: Team
    },
    {
      path: '/team/member/:team_id',
      name: 'TeamMember',
      component: TeamMember
    },
    {
      path: '/team/item/:team_id',
      name: 'TeamItem',
      component: TeamItem
    },
  ]
})

router.beforeEach((to, from, next) => {

  if (getToken()) {

    if (to.path === '/login') {
      next({path: '/'})
    } else {
      if (!store.state.username || store.state.username.length === 0) {
        store.dispatch('getUserInfo').then(data => { //获取用户信息
          next()
        }).catch(() => {
          next({path: '/'})
        })
      } else {
        next()
      }
    }
  } else {
    if (to.matched.some(r => r.meta.requireLogin)) {
      Message({
        type: 'warning',
        showClose: true,
        message: '请先登录哦'
      })
      next({path: '/'})
    }
    else {
      next();
    }
  }
})


export default router

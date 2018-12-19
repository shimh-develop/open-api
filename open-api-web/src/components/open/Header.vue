<template>
  <header class="header">
    <h2 class="title">
      <img class="logo" src="../../../static/logo/w_64.png"/>
      <router-link to="/">{{$t('open_platform_name')}}</router-link>
    </h2>
    <el-menu
      class="main-menu"
      default-active="1"
      mode="horizontal"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#409EFF">
      <el-menu-item index="1" >{{$t('menu_api_doc')}}</el-menu-item>

    </el-menu>

    <el-menu
      :router="true"
      class="user-menu"
      mode="horizontal"
      background-color="#545c64"
      text-color="#fff"
      active-text-color="#409EFF">
      <el-menu-item index="item/index" v-if="$login()">{{$t('menu_api_management')}}</el-menu-item>
      <el-menu-item index="admin/index" v-if="$admin()">{{$t('menu_background_management')}}</el-menu-item>
      <el-menu-item index="/login" v-if="!$login()">{{$t("login")}}</el-menu-item>
      <el-submenu index="hello" v-if="$login()">
        <template slot="title">{{ $store.state.username}}</template>
        <el-menu-item index @click="logout">{{$t('logout')}}</el-menu-item>
      </el-submenu>
    </el-menu>

  </header>
</template>

<script>
  export default {
    name: "Header",
    methods: {
      logout() {
        this.$store.dispatch('logout').then(() => {
          this.$router.push({path: '/'})
        }).catch((error) => {
          if (error !== 'error') {
            this.$message({message: error, type: 'error', showClose: true});
          }
        })
      }
    }
  }
</script>

<style scoped>
  .header {
    background-color: #545c64;
    height: 60px;
    position: relative;
  }
  .title {
    float: left;
    line-height: 60px;
    height: 60px;
    margin:0px;

  }
  .title a {
    color:#fff
  }
  .logo {
    float: left;
    height: 60px;
  }
  .main-menu {
    display: inline-block;
    margin-left: 60px
  }
  .user-menu {
    display: inline-block;
    float: right;
    padding-right: 30px
  }
  .user-menu .el-submenu .el-menu-item {
    min-width: 20px!important;
  }
</style>

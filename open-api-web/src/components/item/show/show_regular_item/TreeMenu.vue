<template>
  <div>
  <el-submenu  v-if="catalogs.length" v-for="(c ,catalog_index) in catalogs" :index="c.cat_id + ''" :key="c.cat_id">
    <!-- 二级目录名 -->
    <template slot="title"> <img src="static/images/folder.png"  class="icon-folder menu-icon-folder ">{{c.cat_name}}</template>

    <!-- 二级目录的页面 -->
    <el-menu-item-group v-if="c.pages.length" v-for="(page ,page_index) in c.pages"  :key="page.page_id">
      <el-menu-item :index="page.page_id + ''">{{page.page_title}}</el-menu-item>
    </el-menu-item-group>

    <tree-menu :catalogs="c.catalogs"></tree-menu>

  </el-submenu>
  </div>
</template>

<script>
  export default {
    name: "TreeMenu",
    props: {
      catalogs: Array
    }
  }
</script>

<style scoped>
  .icon-folder{
    width: 18px;
    height: 15px;
    cursor: pointer;
  }

  .menu-icon-folder{
    margin-right: 5px;
    margin-top: -5px;
  }
</style>

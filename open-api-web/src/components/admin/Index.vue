<template>

  <div class="hello">

    <el-container>
      <el-header>
        <div class="header_title">{{$t('background')}}</div>
        <router-link class="goback" to="/item/index">{{$t('menu_api_management')}}</router-link>
        <router-link class="goback" to="/">{{$t('open_platform_name')}}</router-link>
      </el-header>
      <el-container>
        <el-aside width="150px">
          <el-menu
            default-active="1"
            class="el-menu-vertical-demo"
            background-color="#545c64"
            text-color="#fff"
            @select="select_menu"
            active-text-color="#ffd04b">
            <el-menu-item index="1">
              <i class="el-icon-menu"></i>
              <span slot="title">{{$t('user_management')}}</span>
            </el-menu-item>
            <!--<el-menu-item index="2">-->
              <!--<i class="el-icon-tickets"></i>-->
              <!--<span slot="title">{{$t('item_management')}}</span>-->
            <!--</el-menu-item>-->
          </el-menu>
        </el-aside>
        <el-container>
          <el-main>

            <User v-if="open_menu_index == 1 "></User>
            <Item v-if="open_menu_index == 2 "></Item>

          </el-main>
          <el-footer>
            <!-- something -->
          </el-footer>
        </el-container>
      </el-container>
    </el-container>

  </div>
</template>

<style scoped>
  .el-header {
    background-color: #fff;
    color: #333;
    text-align: center;
    line-height: 60px;
    border-bottom: 1px solid #ddd;
    padding-left: 0px;

  }

  .el-footer {
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    padding-top: 10px;
    background-color: rgb(84, 92, 100);
    color: #333;
    text-align: center;
    line-height: 200px;
    height: calc(100% - 60px);
    position: absolute;
  }

  .el-menu {
    border-right: 0px;
  }

  .el-main {
    margin-left: 200px;
    overflow: visible;
  }

  body > .el-container {
    position: absolute;
    height: 100%;
    width: 100%;

  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 260px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }

  .goback {
    float: right;
    margin-right: 20px;
  }

  .header_title {
    float: left;
    width: 150px;
    font-size: 24px;
    background-color: rgb(84, 92, 100);
    color: #fff;
  }
</style>

<script>
  import Item from '@/components/admin/item/Index'
  import User from '@/components/admin/user/Index'

  export default {
    data() {
      return {
        open_menu_index: 1,
      };
    },
    components: {
      Item,
      User,
    },
    methods: {
      select_menu(index, indexPath) {
        this.open_menu_index = 0;
        this.$nextTick(() => {
          this.open_menu_index = index;
        });

      }
    },
    mounted() {


    },
    beforeDestroy() {
      this.$message.closeAll();
      /*去掉添加的背景色*/
      document.body.removeAttribute("class", "grey-bg");
    }
  }
</script>

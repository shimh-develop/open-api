<template>
  <div class="hello">
    <Header></Header>

    <el-container>
      <el-card class="center-card">
        <template>
          <el-button type="text" @click="goback" class="goback-btn ">{{$t('goback')}}</el-button>
          <el-tabs value="first" type="card">
            <el-tab-pane :label="$t('base_info')" name="first">

              <Info></Info>

            </el-tab-pane>


            <!--<el-tab-pane :label="$t('member_manage')" name="second">-->

              <!--<Member></Member>-->

            <!--</el-tab-pane>-->

            <el-tab-pane :label="$t('advance_setting')" name="third">

              <Advanced></Advanced>

            </el-tab-pane>

            <!--<el-tab-pane :label="$t('open_api')" name="four">-->

              <!--<OpenApi></OpenApi>-->

            <!--</el-tab-pane>-->

          </el-tabs>
        </template>
      </el-card>
    </el-container>

    <Footer></Footer>

  </div>
</template>

<script>

  import Info from '@/components/item/setting/Info'
  import Member from '@/components/item/setting/Member'
  import Advanced from '@/components/item/setting/Advanced'
  import OpenApi from '@/components/item/setting/OpenApi'

  export default {
    name: 'Index',
    components: {
      Info,
      Member,
      Advanced,
      OpenApi
    },
    data() {
      return {
        userInfo: {}
      }
    },
    methods: {
      goback() {
        this.$router.go(-1);
      }
    },
    beforeCreate() {
      /*给body添加类，设置背景色*/
      document.getElementsByTagName("body")[0].className = "grey-bg";
    },
    beforeDestroy() {
      /*去掉添加的背景色*/
      document.body.removeAttribute("class", "grey-bg");
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

  .center-card a {
    font-size: 12px;
  }

  .center-card {
    text-align: center;
    width: 600px;
    min-height: 500px;
    max-height: 700px;
  }

  .infoForm {
    width: 350px;
    margin-left: 60px;
    margin-top: 30px;
  }

  .goback-btn {
    z-index: 999;
    margin-left: 500px;
  }
</style>

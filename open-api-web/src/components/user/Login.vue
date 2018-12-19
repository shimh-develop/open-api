<template>
  <div class="hello">
    <Header></Header>

    <el-container>
      <el-card class="center-card">
        <el-form status-icon label-width="0px" class="demo-ruleForm" @keyup.enter.native="onSubmit">
          <h2>{{$t("login")}}</h2>
          <el-form-item label="">
            <el-input type="text" auto-complete="off" :placeholder="$t('username_description')"
                      v-model="username"></el-input>
          </el-form-item>

          <el-form-item label="">
            <el-input type="password" auto-complete="off" v-model="password" :placeholder="$t('password')"></el-input>
          </el-form-item>

          <el-form-item label="">
            <el-button type="primary" style="width:100%;" @click="onSubmit">{{$t("login")}}</el-button>
          </el-form-item>

          <el-form-item label="">
            <router-link to="/">{{$t('open_platform_name')}}</router-link>

          </el-form-item>
        </el-form>
      </el-card>
    </el-container>

    <Footer></Footer>

  </div>
</template>

<script>


export default {
  name: 'Login',
  components: {},
  data() {
    return {
      username: '',
      password: ''
    }

  },
  methods: {
    onSubmit() {
      const user = {
        username: this.username,
        password: this.password
      }
      this.$store.dispatch('login', user).then((res) => {
        if(res.data.error_code === 0){
          this.$router.go(-1)
        } else {
          this.$message({message: res.data.error_message, type: 'error', showClose: true});
        }
      }).catch((error) => {
        if (error !== 'error') {
          this.$message({message: error, type: 'error', showClose: true});
        }
      })
    }

  },
  mounted() {
    var that = this;
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
  }

  .v_code_img {
    margin-top: 20px;
  }

</style>

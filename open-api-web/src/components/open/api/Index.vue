<template>
  <div class="container">
    <Header></Header>
    <div class="main-content">
      <el-aside class="el-aside" id="left-side">
        <LeftMenu backgroundColor="#fff" :open-api="true" :get_page_content="get_page_content" :item_info="item_info"
                  v-if="item_info"></LeftMenu>
      </el-aside>

      <el-container class="right-side" id="right-side">
        <el-main class="page_content_main" id="page_content_main">
          <div class="doc-title-box" v-if="page_id">
            <span id="doc-title-span" class="dn"></span>
            <h2 id="doc-title">{{page_title}}</h2>
          </div>
          <Editormd v-bind:content="content" type="html" v-if="page_id"></Editormd>
        </el-main>
      </el-container>

    </div>

    <Footer class="footer"></Footer>
  </div>

</template>

<script>

  import Editormd from '@/components/common/Editormd'
  import LeftMenu from '@/components/item/show/show_regular_item/LeftMenu'
  import Header from '../Header'
  import Footer from '../Footer'


  export default {
    name: "Index",
    data() {
      return {
        item_info: '',
        content: "",
        page_id: '',
        page_title: '',
        page_info: ''
      }
    },
    components: {
      Editormd,
      LeftMenu,
      Header,
      Footer
    },
    methods: {
      get_open_api_item() {
        var that = this;
        var url = DocConfig.server + '/api/item/getOpenApiInfo';
        var loading = that.$loading();

        that.axios.get(url).then(function (response) {
          if (response.data.error_code === 0) {
            var item = response.data.data;
            if (item && item.item_id) {
              if (item.pages && item.pages.length > 0) {
                item.default_page_id = item.pages[0].page_id + ''
              }
              that.item_info = item;
            }
          } else {
            that.$alert(response.data.error_message);
          }
        }).finally(() => {
          loading.close();
        })
      },
      get_item_menu() {
        var that = this;
        var loading = that.$loading();
        var item_id = 68;
        var page_id = this.$route.query.page_id ? this.$route.query.page_id : 0;

        var url = DocConfig.server + '/api/item/info';

        var params = new URLSearchParams();
        params.append('item_id', item_id);

        that.axios.post(url, params)
          .then(function (response) {
            loading.close();
            if (response.data.error_code === 0) {
              var item = response.data.data;
              if (item && item.item_id) {
                if (page_id <= 0) {
                  if (item.pages.length > 0) {
                    item.default_page_id = item.pages[0].page_id + ''
                  }
                } else {
                  item.default_page_id = page_id
                }

                that.item_info = item;
              }
            } else {
              that.$alert(response.data.error_message);
            }
          }).catch((e) => {
        });
        //设置一个最长关闭时间
        setTimeout(() => {
          loading.close();
        }, 20000);
      },
      //获取页面内容
      get_page_content(page_id) {
        if (page_id <= 0) {
          return;
        }
        ;
        //根据屏幕宽度进行响应(应对移动设备的访问)
        if (this.isMobile() || window.screen.width < 1000) {
          this.$nextTick(() => {
            this.AdaptToMobile();
          });
        }
        var that = this;
        var url = DocConfig.server + '/api/page/info';
        //var loading = that.$loading({target:".page_content_main",fullscreen:false});
        var params = new URLSearchParams();
        params.append('page_id', page_id);
        that.axios.post(url, params)
          .then(function (response) {
            //loading.close();
            if (response.data.error_code === 0 && response.data.data) {
              that.content = response.data.data.page_content;

              that.page_title = response.data.data.page_title;
              that.page_info = response.data.data;
              //切换变量让它重新加载、渲染子组件
              that.page_id = 0;
              that.$nextTick(() => {
                that.page_id = page_id;
              });

            } else {


            }

          });
      },
      //根据屏幕宽度进行响应(应对移动设备的访问)
      AdaptToMobile() {
        this.hide_menu();
        this.show_page_bar = false;

      }
    },
    mounted() {
      //根据屏幕宽度进行响应(应对移动设备的访问)
      if (this.isMobile() || window.screen.width < 1000) {
        this.$nextTick(() => {
          this.AdaptToMobile();
        });
      }
      this.get_open_api_item()
      document.getElementsByTagName("body")[0].className = "grey-bg";
    },
    beforeDestroy() {
      /*去掉添加的背景色*/
      document.body.removeAttribute("class", "grey-bg");
    }

  }
</script>

<style scoped>

  .main-content {
    min-height: 800px;
    margin: 40px 80px;
    background-color: white !important;
    padding-bottom: 60px;
  }

  .el-aside {
    overflow: auto;
    color: #333;
    position: absolute;
    min-height: 800px;
    height: 800px;
    border-right: solid 1px #e6e6e6;
  }

  .page_content_main {
    width: 800px;
    margin: 0 80px;
    height: 50%;
    overflow: visible;
  }

  .right-side {
    margin-left: 300px;
  }

  .doc-title-box {
    height: auto;
    margin: 30px 30px 10px 30px;
    width: auto;
    border-bottom: 1px solid #ebebeb;
    padding-bottom: 10px;
  }

  .editormd-html-preview {
    width: 95%;
    font-size: 16px;
  }
</style>

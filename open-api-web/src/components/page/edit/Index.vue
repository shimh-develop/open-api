<template>
  <div class="hello" @keydown.ctrl.83.prevent="save" @keydown.meta.83.prevent="save">
    <Header></Header>

    <el-container class="container-narrow">

      <el-row class="masthead">
        <el-form :inline="true" class="demo-form-inline" size="small">
          <el-form-item :label="$t('title')+' : '">
            <el-input placeholder="" v-model="title"></el-input>
          </el-form-item>

          <el-form-item :label="$t('catalog')+' : '">

            <el-cascader
              v-model="cat_id"
              :options="catalogs"
              :props="{value: 'cat_id',label:'cat_name',children:'catalogs'}"
              change-on-select
            ></el-cascader>

          </el-form-item>

          <el-form-item :label="$t('s_number')+' : '">
            <el-input :placeholder="$t('optional')" class="num" v-model="s_number"></el-input>
          </el-form-item>

          <el-form-item class="pull-right">

            <el-button type="" size="medium" @click="save">{{$t('save')}}</el-button>
            <el-button type="" size="medium" @click="goback">{{$t('goback')}}</el-button>
          </el-form-item>
        </el-form>

        <el-row class="fun-btn-group">
          <el-button type="" size="medium" @click="insert_api_template">{{$t('insert_apidoc_template')}}</el-button>
          <el-button type="" size="medium" @click="insert_database_template">{{$t('insert_database_doc_template')}}
          </el-button>
          <el-dropdown split-button type="" style="margin-left:100px;" size="medium" trigger="hover">
            {{$t('json_tools')}}
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item @click.native="ShowJsonToTable">{{$t('json_to_table')}}</el-dropdown-item>
              <el-dropdown-item @click.native="ShowJsonBeautify">{{$t('beautify_json')}}</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>

        </el-row>

        <Editormd v-bind:content="content" ref="Editormd" type="editor"></Editormd>


      </el-row>

      <!-- 更多模板 -->
      <TemplateList :callback="insertValue" ref="TemplateList"></TemplateList>

      <!-- 历史版本 -->
      <HistoryVersion :callback="insertValue" :is_show_recover_btn="true" ref="HistoryVersion"></HistoryVersion>

      <!-- Json转表格 组件 -->
      <JsonToTable :callback="insertValue" ref="JsonToTable"></JsonToTable>

      <!-- Json格式化 -->
      <JsonBeautify :callback="insertValue" ref="JsonBeautify"></JsonBeautify>

    </el-container>
    <Footer></Footer>
    <div class=""></div>
    <!-- 模板存放的地方 -->
    <div id="api-doc-templ" ref="api_doc_templ" style="display:none">

      **简要描述：**

      - 用户注册接口

      **请求URL：**
      - ` http://xx.com/api/user/register `

      **请求方式：**
      - POST

      **参数：**

      |参数名|必选|类型|说明|
      |:---- |:---|:----- |----- |
      |username |是 |string |用户名 |
      |password |是 |string | 密码 |
      |name |否 |string | 昵称 |

      **返回示例**

      ```
      {
      "error_code": 0,
      "data": {
      "uid": "1",
      "username": "12154545",
      "name": "吴系挂",
      "groupid": 2 ,
      "reg_time": "1436864169",
      "last_login_time": "0",
      }
      }
      ```

      **返回参数说明**

      |参数名|类型|说明|
      |:----- |:-----|----- |
      |groupid |int |用户组id，1：超级管理员；2：普通用户 |

      **备注**

      - 更多返回错误代码请看首页的错误代码描述


    </div>
    <div id="database-doc-templ" ref="database_doc_templ" style="display:none">

      - 用户表，储存用户信息

      |字段|类型|空|默认|注释|
      |:---- |:------- |:--- |-- -|------ |
      |uid |int(10) |否 | | |
      |username |varchar(20) |否 | | 用户名 |
      |password |varchar(50) |否 | | 密码 |
      |name |varchar(15) |是 | | 昵称 |
      |reg_time |int(11) |否 | 0 | 注册时间 |

      - 备注：无


    </div>

  </div>
</template>

<style scoped>


  .container-narrow {
    margin: 0 auto;
    max-width: 90%;
  }

  .masthead {
    width: 100%;
    margin-top: 5px;
  }

  .cat {
    width: 200px;
  }

  .num {
    width: 60px;

  }

  .fun-btn-group {
    margin-top: 15px;
    margin-bottom: 15px;
  }

</style>

<script>
  import Editormd from '@/components/common/Editormd'
  import JsonToTable from '@/components/common/JsonToTable'
  import JsonBeautify from '@/components/common/JsonBeautify'
  import TemplateList from '@/components/page/edit/TemplateList'
  import HistoryVersion from '@/components/page/edit/HistoryVersion'

  export default {
    data() {
      return {
        catalogs: [],
        currentDate: new Date(),
        itemList: {},
        content: "",
        title: "",
        item_id: 0,
        cat_id: ['-'],
        s_number: '',
        page_id: '',
        copy_page_id: '',

      };
    },
    computed: {},
    components: {
      Editormd,
      JsonToTable,
      JsonBeautify,
      TemplateList,
      HistoryVersion
    },
    methods: {
      //获取页面内容
      get_page_content(page_id) {
        if (!page_id) {
          var page_id = this.page_id;
        }
        ;
        var that = this;
        var url = DocConfig.server + '/api/page/info';
        var params = new URLSearchParams();
        params.append('page_id', page_id);
        that.axios.post(url, params)
          .then(function (response) {
            if (response.data.error_code === 0) {
              var page = response.data.data

              that.content = page.page_content;
              setTimeout(function () {
                that.insertValue(that.content, 1);
              }, 500);
              setTimeout(function () {
                //如果长度大于3000,则关闭预览
                if (that.content.length > 3000) {
                  that.editor_unwatch();
                } else {
                  that.editor_watch();
                }
              }, 1000);
              that.title = page.page_title;
              that.item_id = page.item_id;
              that.s_number = page.s_number;
              //回填目录
              if (page.catalog) {
                var cat_id = []
                var get_cat_id = (cat) => {
                  cat_id.push(cat.cat_id)
                  if (cat.parentCatalog) {
                    get_cat_id(cat.parentCatalog)
                  }
                }
                get_cat_id(page.catalog)
                that.cat_id = cat_id.reverse()
              } else {
                that.cat_id = ['-']
              }

            } else {
              that.$alert(response.data.error_message);
            }

          })
          .catch(function (error) {
            console.log(error);
          });
      },

      //获取所有目录
      get_catalog(item_id) {
        var that = this;
        var url = DocConfig.server + '/api/catalog/catListGroup';
        var params = new URLSearchParams();
        params.append('item_id', item_id);
        that.axios.post(url, params)
          .then(function (response) {
            if (response.data.error_code === 0) {
              var data = response.data.data;
              that.catalogs = [
                {
                  cat_id: '-',
                  cat_name: '无',
                }, ...data.catalogs];
            } else {
              that.$alert(response.data.error_message);
            }

          })
          .catch(function (error) {
            console.log(error);
          });
      },
      //插入数据到编辑器中。插入到光标处。如果参数is_cover为真，则清空后再插入(即覆盖)。
      insertValue(value, is_cover) {
        if (value) {
          let childRef = this.$refs.Editormd;//获取子组件
          if (is_cover) {
            // 清空
            childRef.clear();
          }
          ;
          childRef.insertValue(value); //调用子组件的方法
        }
        ;

      },

      //插入api模板
      insert_api_template() {
        this.insertValue(this.$refs.api_doc_templ.innerHTML);
      },

      //插入数据字典模板
      insert_database_template() {
        this.insertValue(this.$refs.database_doc_templ.innerHTML);
      },
      //关闭预览
      editor_unwatch() {
        let childRef = this.$refs.Editormd;//获取子组件
        childRef.editor_unwatch();
        if (sessionStorage.getItem("page_id_unwatch_" + this.page_id)) {

        } else {
          this.$alert("检测到本页面内容比较多，暂时关闭了html实时预览功能，以防止过多内容造成页面卡顿。你可以在编辑栏中找到预览按钮进行手动打开。");
          sessionStorage.setItem("page_id_unwatch_" + this.page_id, 1)
        }

      },
      //
      editor_watch() {
        let childRef = this.$refs.Editormd;//获取子组件
        childRef.editor_watch();
      },
      //json转参数表格
      ShowJsonToTable() {
        let childRef = this.$refs.JsonToTable;//获取子组件
        childRef.dialogFormVisible = true;
      },
      //json格式化
      ShowJsonBeautify() {
        let childRef = this.$refs.JsonBeautify;//获取子组件
        childRef.dialogFormVisible = true;
      },
      //更多模板、模板列表
      ShowTemplateList() {
        let childRef = this.$refs.TemplateList;//获取子组件
        childRef.show();
      },

      //展示历史版本
      ShowHistoryVersion() {
        let childRef = this.$refs.HistoryVersion;//获取子组件
        childRef.show();
      },

      save() {
        var that = this;
        var loading = that.$loading();
        let childRef = this.$refs.Editormd;
        var content = childRef.getMarkdown();
        var cat_id = this.cat_id;
        var item_id = that.$route.params.item_id;
        var page_id = that.$route.params.page_id;
        var url = DocConfig.server + '/api/page/save';

        var p = {
          page_id,
          s_number: that.s_number,
          page_title: that.title,
          page_content: content,
        }

        if (cat_id.length && cat_id[0] !== '-' && cat_id[0] !== '') {
          p.catalog = {cat_id: cat_id[cat_id.length - 1]}
        } else {
          p.item = {item_id: item_id}
        }

        that.axios({
          url: url,
          method: 'post',
          data: p
        })
          .then(function (response) {
            loading.close();
            if (response.data.error_code === 0) {
              that.$message({
                showClose: true,
                message: that.$t("save_success"),
                type: 'success'
              });
              localStorage.removeItem("page_content");
              if (page_id <= 0) {
                that.$router.push({path: '/page/edit/' + item_id + '/' + response.data.data.page_id});
              }
              ;
            } else {
              that.$alert(response.data.error_message);
            }

          });
        //设置一个最长关闭时间
        setTimeout(() => {
          loading.close();
        }, 20000);
      },
      goback() {
        localStorage.removeItem("page_content");
        var url = '/' + this.$route.params.item_id;
        this.$router.push({path: url, query: {page_id: this.$route.params.page_id}});
      },
      dropdown_callback(data) {
        if (data) {
          data();
        }
        ;
      },
      //另存为模板
      save_to_template() {
        var that = this;
        let childRef = this.$refs.Editormd;
        var content = childRef.getMarkdown();
        this.$prompt(that.$t("save_templ_title"), ' ', {}).then(function (data) {
          var url = DocConfig.server + '/api/template/save';
          var params = new URLSearchParams();
          params.append('template_title', data.value);
          params.append('template_content', content);
          that.axios.post(url, params)
            .then(function (response) {
              if (response.data.error_code === 0) {
                that.$alert(that.$t("save_templ_text"));
              } else {
                that.$alert(response.data.error_message);
              }

            });
        });
      },

      /** 粘贴上传图片 **/
      upload_paste_img(e) {
        var that = this;
        var url = DocConfig.server + '/api/page/uploadImg';
        var clipboard = e.clipboardData;
        for (var i = 0, len = clipboard.items.length; i < len; i++) {
          if (clipboard.items[i].kind == 'file' || clipboard.items[i].type.indexOf('image') > -1) {
            var imageFile = clipboard.items[i].getAsFile();
            var form = new FormData;
            form.append('t', 'ajax-uploadpic');
            form.append('editormd-image-file', imageFile);
            var loading = '';
            var callback = function (type, data) {
              type = type || 'before';
              switch (type) {
                // 开始上传
                case 'before':
                  loading = that.$loading();
                  break;
                // 服务器返回错误
                case 'error':
                  loading.close();
                  that.$alert('图片上传失败');
                  break;
                // 上传成功
                case 'success':
                  loading.close();
                  if (data.success == 1) {
                    var value = '![](' + data.url + ')';
                    that.insertValue(value);
                  } else {
                    that.$alert(data.message);
                  }

                  break;
              }
            };
            $.ajax({
              url: url,
              type: "POST",
              dataType: "json",
              data: form,
              processData: false,
              contentType: false,
              beforeSend: function () {
                callback('before');
              },
              error: function () {
                callback('error');
              },
              success: function (data) {
                callback('success', data);
              }
            })
            e.preventDefault();
          }
        }
      }
    },

    mounted() {
      var that = this;
      this.page_id = this.$route.params.page_id;
      this.copy_page_id = this.$route.query.copy_page_id ? this.$route.query.copy_page_id : '';

      if (this.copy_page_id > 0) {
        this.get_page_content(this.copy_page_id);
      }
      else if (this.page_id > 0) {
        this.get_page_content(this.page_id);
      } else {
        this.item_id = this.$route.params.item_id;
        this.content = '';
      }
      this.get_catalog(this.$route.params.item_id);

      /** 监听粘贴上传图片 **/
      document.addEventListener('paste', this.upload_paste_img);

    },

    beforeDestroy() {

      //解除对粘贴上传图片的监听
      document.removeEventListener('paste', this.upload_paste_img);
    }
  }
</script>

<template>
  <div class="hello">
    <Header></Header>

    <el-container>
      <el-card class="center-card">
        <el-button type="text" class="add-cat" @click="add_cat">{{$t('add_cat')}}</el-button>
        <el-button type="text" class="goback-btn" @click="goback">{{$t('goback')}}</el-button>
        <tree-catalog @edit="edit" @delete_cat="delete_cat" :catalogs="get_catalog_table"></tree-catalog>
      </el-card>
      <el-dialog :visible.sync="dialogFormVisible" width="300px">
        <el-form>
          <el-form-item :label="$t('cat_name')+' : '">
            <el-input :placeholder="$t('input_cat_name')" v-model="MyForm.cat_name"></el-input>
          </el-form-item>
          <el-form-item :label="$t('parent_cat_name')+' : '">
            <el-cascader
              :placeholder="$t('none')"
              v-model="MyForm.parent_cat_id"
              :options="catalogs"
              :props="{value: 'cat_id',label:'cat_name',children:'catalogs'}"
              change-on-select
            ></el-cascader>
          </el-form-item>
          <el-form-item :label="$t('s_number')">
            <el-input :placeholder="$t('s_number_explain')" v-model="MyForm.s_number"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">{{$t('cancel')}}</el-button>
          <el-button type="primary" @click="MyFormSubmit">{{$t('confirm')}}</el-button>
        </div>
      </el-dialog>
    </el-container>

    <Footer></Footer>
  </div>
</template>

<script>
  import TreeCatalog from './TreeCatalog'

  const EMPTY_FROM = {
    cat_id: '',
    parent_cat_id: ['-'],
    cat_name: '',
    s_number: ''
  }

  export default {
    name: 'Login',
    components: {
      TreeCatalog
    },
    data() {
      return {
        MyForm: EMPTY_FROM,
        catalogs: [],
        source_catalogs: [],
        catalogs_level_2: [],
        dialogFormVisible: false,
      }

    },
    computed: {
      get_catalog_table() {
        return this.source_catalogs.slice(1)
      }
    },
    methods: {
      get_catalog() {
        var that = this;
        var url = DocConfig.server + '/api/catalog/catListGroup';
        var params = new URLSearchParams();
        params.append('item_id', that.$route.params.item_id);
        that.axios.post(url, params)
          .then(function (response) {
            if (response.data.error_code === 0) {
              var catalogs = response.data.data.catalogs;
              //生成目录树
              var catalog_tree = []
              var add_cat_id = (arr, catalogs) => {
                for(let i = 0; i < catalogs.length; i++) {
                  var new_arr = [...arr, catalogs[i].cat_id]
                  if(catalogs[i].catalogs &&  catalogs[i].catalogs.length) {
                    add_cat_id(new_arr, catalogs[i].catalogs)
                  } else {
                    catalog_tree.push(new_arr)
                  }
                }
              }
              add_cat_id([], catalogs)
              that.catalog_tree = catalog_tree
              // 所有目录
              that.source_catalogs = [
                {
                  cat_id: '-',
                  cat_name: '无',
                }, ...catalogs];
            } else {
              that.$alert(response.data.error_message);
            }

          })
          .catch(function (error) {
            console.log(error);
          });
      },
      MyFormSubmit() {
        var that = this;
        var url = DocConfig.server + '/api/catalog/save';

        var parent_cat_id = this.MyForm.parent_cat_id

        var p = {
          s_number: this.MyForm.s_number,
          cat_name: this.MyForm.cat_name,
          cat_id: this.MyForm.cat_id,
        }

        if (parent_cat_id.length && parent_cat_id[0] !== '-' && parent_cat_id[0] !== '') {
          p.parentCatalog = {cat_id: parent_cat_id[parent_cat_id.length - 1]}
        } else {
          p.item = {item_id: that.$route.params.item_id}
        }

        that.axios({
          url: url,
          method: 'post',
          data: p
        }).then(function (response) {
          if (response.data.error_code === 0) {
            that.dialogFormVisible = false;
            that.get_catalog();
            that.MyForm = [];
          } else {
            that.$alert(response.data.error_message);
          }
        }).catch(function (error) {
          console.log(error);
        });
      },
      edit(row) {
        var temp = {};
        temp = JSON.parse(JSON.stringify(row));

        //获取目录 除掉自己
        const source_catalogs = JSON.parse(JSON.stringify(this.source_catalogs))

        var delete_me = (catalogs) => {
          for(let i = 0; i < catalogs.length; i++) {
            if(catalogs[i].cat_id === temp.cat_id) {
              catalogs.splice(i, 1)
            } else {
              if(catalogs[i].catalogs &&  catalogs[i].catalogs.length) {
                delete_me(catalogs[i].catalogs)
              }
            }
          }
        }
        delete_me(source_catalogs)
        this.catalogs = source_catalogs
        //根据目录树回填目录
        const catalog_tree = this.catalog_tree

        for(let i = 0; i < catalog_tree.length; i++) {
          const arr = catalog_tree[i]
          if(arr.indexOf(temp.cat_id) > -1) {
            temp.parent_cat_id = arr.slice(0, arr.indexOf(temp.cat_id))
            break
          }
        }
        // 没有则为根目录
        if (!temp.parent_cat_id || temp.parent_cat_id.length < 1) {
          temp.parent_cat_id = ['-']
        }

        this.MyForm = temp;
        this.dialogFormVisible = true;
      },
      delete_cat(cat_id) {
        var that = this;
        var url = DocConfig.server + '/api/catalog/delete';

        this.$confirm(that.$t('confirm_delete'), ' ', {
          confirmButtonText: that.$t('confirm'),
          cancelButtonText: that.$t('cancel'),
          type: 'warning'
        }).then(() => {
          var params = new URLSearchParams();
          params.append('item_id', that.$route.params.item_id);
          params.append('cat_id', cat_id);

          that.axios.post(url, params)
            .then(function (response) {
              if (response.data.error_code === 0) {
                that.get_catalog();
              } else {
                that.$alert(response.data.error_message);
              }

            });
        })
      },
      add_cat() {
        this.MyForm = Object.assign({}, EMPTY_FROM);
        this.catalogs = this.source_catalogs
        this.dialogFormVisible = true;

      },
      goback() {
        var url = '/' + this.$route.params.item_id;
        this.$router.push({path: url})
      }
    },
    mounted() {
      this.get_catalog();
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

  .hello {
    text-align: left;
  }

  .add-cat {
    margin-left: 10px;
  }

  .center-card {
    text-align: left;
    width: 800px;
    height: 500px;
    overflow: auto;
  }

  .goback-btn {
    z-index: 999;
  }
</style>


<template>
  <div class="hello">

    <el-menu  @select="select_menu"
      :background-color="backgroundColor"
      text-color=""
      active-text-color="#008cff"
      :default-active="item_info.default_page_id"
      :default-openeds='openeds'
    >

      <el-input
        v-if="!openApi"
        @keyup.enter.native="input_keyword"
        :placeholder="$t('input_keyword')"
        v-model="keyword">

      </el-input>

      <div class="new-bar" v-if="!openApi && $login()">
        <el-tooltip class="item" effect="dark" :content="$t('new_page')" placement="left">
              <i class="el-icon-plus" @click="new_page"></i>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" :content="$t('new_catalog')" placement="right">
              <img src="static/images/folder.png" @click="mamage_catalog" class="icon-folder">
        </el-tooltip>
      </div>

      <!-- 一级页面 -->
      <el-menu-item  v-if="item_info.pages.length " v-for="(page ,index) in item_info.pages" :index="page.page_id + ''" :key="page.page_id" >
        <i style="margin-left:30px;"></i>
        {{page.page_title}}
      </el-menu-item>

        <!-- 目录开始 -->
      <tree-munu :catalogs="item_info.catalogs"></tree-munu>

    </el-menu>
  </div>
</template>


<script>
  import Editormd from '@/components/common/Editormd'
  import TreeMunu from './TreeMenu'
  export default {
  props:{
    backgroundColor: {
      type: String,
      default: '#fafafa'
    },
    openApi: {
      type: Boolean,
      default: false
    },
    get_page_content:'',
    item_info:'',
    search_item:''
  },
    data() {
      return {
        keyword:'',
        openeds:[],
        menu:''
      }
    },
  components:{
    Editormd,
    TreeMunu
  },
  methods:{
    //选中菜单的回调
    select_menu(index, indexPath){
      if(!this.openApi) { // 开放文档
        this.change_url(index);
      }
      this.get_page_content(index);
    },
    new_page(){
      var url = '/page/edit/'+this.item_info.item_id+'/0';
      this.$router.push({path:url});
    },

    mamage_catalog(){
      var url = '/catalog/'+this.item_info.item_id;
      this.$router.push({path:url});
    },

    //改变url
    change_url(page_id){
        var base_url = '';
        var item_domain = '';
        var domain = this.item_info.item_domain ? this.item_info.item_domain : this.item_info.item_id ;
        this.$router.replace({
            path: '/'+domain,
            query: {page_id:page_id}
        });
    },

    input_keyword(){
      this.search_item(this.keyword);
    }

  },
  mounted () {
    var that = this ;
    this.menu = this.item_info.menu ;
    var item_info = this.item_info ;
    //默认展开页面
    if (item_info.default_page_id > 0 ) {
      that.select_menu(item_info.default_page_id);
      if (item_info.default_cat_id4) {
        that.openeds = [ item_info.default_cat_id4,item_info.default_cat_id3, item_info.default_cat_id2, item_info.default_page_id];
      }
      else if (item_info.default_cat_id3) {
        that.openeds = [ item_info.default_cat_id3, item_info.default_cat_id2, item_info.default_page_id];
      }
      else if (item_info.default_cat_id2) {
        that.openeds = [ item_info.default_cat_id2, item_info.default_page_id];
      };
    }
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

  .el-header {
    color: #333;
    line-height: 60px;
  }

  .el-aside {
    color: #333;
    position:fixed;
    height: calc(100% - 20px);

  }
.el-input-group__append button.el-button{
    background-color: #ffffffa3;

  }
.new-bar{
  margin-left: 190px;
  font-size: 20px;
  margin-top: 10px;
  margin-bottom: 5px;
}
.new-bar i{
  cursor:pointer ;
}

.new-bar i:first-child{
  margin-right: 15px;
}
.icon-folder{
  width: 18px;
  height: 15px;
  cursor: pointer;
}

.menu-icon-folder{
  margin-right: 5px;
  margin-top: -5px;
}
.el-menu{
  border-right:none;
}

.el-menu-item, .el-submenu__title{
    height: 46px;
    line-height: 46px;
}
.el-submenu .el-menu-item {
    height: 40px;
    line-height: 40px;
}

</style>

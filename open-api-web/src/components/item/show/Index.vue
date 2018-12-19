<template>
  <div class="hello">
    <Header> </Header>

    <!-- 展示常规项目 -->
    <ShowRegularItem :item_info="item_info" :search_item="search_item" v-if="item_info && (item_info.item_type == 1 || item_info.item_type == 0)"></ShowRegularItem>
    <!-- 展示单页项目 -->
    <ShowSinglePageItem :item_info="item_info" v-if="item_info && item_info.item_type == 2 "></ShowSinglePageItem>

    <Footer> </Footer>

  </div>
</template>



<script>
  import ShowRegularItem from '@/components/item/show/show_regular_item/Index'
  import ShowSinglePageItem from '@/components/item/show/show_single_page_item/Index'
  export default {
    data() {
      return {
        item_info:'' ,
      }
    },
  components:{
    ShowRegularItem,
    ShowSinglePageItem
  },
  methods:{
    //获取菜单
    get_item_menu(keyword){
        if (!keyword) {
          keyword = '' ;
        };
        var that = this ;
        var loading = that.$loading();
        var item_id = this.$route.params.item_id ? this.$route.params.item_id : 0;
        var page_id = this.$route.query.page_id ? this.$route.query.page_id : 0  ;

        var url = DocConfig.server+'/api/item/info';

        var params = new URLSearchParams();
        params.append('item_id',  item_id);
        params.append('keyword',  keyword);
        if ( !that.keyword) {
          params.append('default_page_id',page_id  );
        };

        that.axios.post(url, params)
          .then(function (response) {
            loading.close();
            if (response.data.error_code === 0 ) {
              var item = response.data.data ;
              if(item && item.item_id) {
                if (page_id <= 0 || keyword) {
                  if (item.pages.length > 0) {
                    item.default_page_id = item.pages[0].page_id + ''
                  }
                } else {
                  item.default_page_id = page_id
                }

                that.item_info = item ;
                document.title = that.item_info.item_name +"-" + that.$t('open_platform_name');
              } else {
                that.$message({
                  showClose: true,
                  message: '项目不存在！',
                  type: 'warning'
                });
              }
            } else{
              that.$alert(response.data.error_message);
            }
          });
        //设置一个最长关闭时间
        setTimeout(() => {
          loading.close();
        }, 20000);
    },
    search_item(keyword){
      this.item_info = '';
      this.get_item_menu(keyword);
    }
  },
  mounted () {
    this.get_item_menu();

  },
  beforeDestroy(){
    this.$message.closeAll();
    /*去掉添加的背景色*/
    document.body.removeAttribute("class","grey-bg");
    document.title = this.$t('open_platform_name') ;
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>

</style>

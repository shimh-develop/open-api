<template>
  <div class="hello">
      <ul class="page-bar">
        <li>
            <el-tooltip class="item" effect="dark" :content="$t('edit_page')" placement="right">
                  <el-button type="text" icon="el-icon-edit" @click="edit_page"></el-button>
            </el-tooltip>
        </li>
        <li>
            <el-tooltip class="item" effect="dark" :content="$t('share_page')" placement="right">
                  <el-button type="text" icon="el-icon-share" @click="share_page"></el-button>
            </el-tooltip>

          </li>
        <li>
          <el-dropdown @command="dropdown_callback">
            <span class="el-dropdown-link">
              <i class="el-icon-arrow-down el-icon--down"></i>
            </span>
            <el-dropdown-menu slot="dropdown">
              <el-dropdown-item :command="show_page_info">{{$t('detail')}}</el-dropdown-item>
              <el-dropdown-item :command="delete_page">{{$t('delete')}}</el-dropdown-item>
            </el-dropdown-menu>
          </el-dropdown>
        </li>
      </ul>

  <el-dialog
    :title="$t('share_page')"
    :visible.sync="dialogVisible"
    width="600px"
    :modal="false"
    class="text-center"
    >

    <p>{{$t('item_page_address')}} : <code >{{share_page_link}}</code></p>
    <p><a href="javascript:;" class="home-phone-butt" v-clipboard:copyhttplist="copyText1" v-clipboard:success="onCopy">{{$t('copy_link')}}</a></p>
    <div style="border-bottom: 1px solid #eee;width: 100px;margin: 0 auto" id="share_page_link"></div>

    <p >{{$t('single_page_address')}} : <code id="share-single-link">{{share_single_link}}</code></p>
    <p><a href="javascript:;" class="home-phone-butt" v-clipboard:copyhttplist="copyText2" v-clipboard:success="onCopy">{{$t('copy_link')}}</a></p>
    <div style="border-bottom: 1px solid #eee;width: 100px;margin: 0 auto" id="share_single_link"></div>

    <span slot="footer" class="dialog-footer">
      <el-button type="primary" @click="dialogVisible = false">{{$t('confirm')}}</el-button>
    </span>
  </el-dialog>

    <!-- 历史版本 -->
    <HistoryVersion :page_id="page_id" :is_show_recover_btn="false" :is_modal="false" callback="insertValue" ref="HistoryVersion"></HistoryVersion>

  </div>
</template>


<style scoped>
  .page-bar{

  }

</style>

<script>
  import HistoryVersion from '@/components/page/edit/HistoryVersion'
  import QRCode from 'qrcodejs2'
  export default {
  props:{
    item_id:'',
    page_id:'',
    page_info:{},
    item_info:'',
  },
    data() {
      return {
        menu: [],
        dialogVisible:false,
        qr_page_link: '',
        qr_single_link:'',
        share_page_link:"",
        share_single_link:"",
        copyText1:'',
        copyText2:'',
      }
    },
  components:{
    HistoryVersion
  },
  methods:{
    edit_page(){
      var page_id = this.page_id > 0 ? this.page_id : 0 ;
      var url = '/page/edit/'+this.item_id+'/'+page_id;
      this.$router.push({path:url}) ;
    },
    share_page(){
      var page_id = this.page_id > 0 ? this.page_id : 0 ;
      this.share_page_link = this.getRootPath()+"#/"+this.item_id +'?page_id='+page_id ;
      this.share_single_link= this.getRootPath()+"#/page/"+page_id ;
      var that = this
      this.$nextTick(() => {
        if(that.qr_page_link) {
          that.qr_page_link.clear()
          that.qr_page_link.makeCode(that.share_page_link);
          that.qr_single_link.clear()
          that.qr_single_link.makeCode(that.share_single_link);
        } else {
          that.qr_page_link = new QRCode("share_page_link", {
            text: that.share_page_link,
            width: 100,
            height: 100,
            colorDark: '#000000',
            colorLight: '#ffffff'
          })
          that.qr_single_link = new QRCode("share_single_link", {
            text: that.share_single_link,
            width: 100,
            height: 100,
            colorDark: '#000000',
            colorLight: '#ffffff'
          })
        }
      })
      this.dialogVisible = true;
      this.copyText1 = this.share_page_link;
      this.copyText2 = this.share_single_link;
    },
    dropdown_callback(data){
      if (data) {
        data();
      };
    },
    show_page_info(){
      var html ="本页面由 "+this.page_info.author_username+' 于 '+this.page_info.create_time+' 更新';
      this.$alert(html, { confirmButtonText: '确定', callback: action => {} });
    },

    //展示历史版本
    ShowHistoryVersion(){
        let childRef = this.$refs.HistoryVersion ;//获取子组件
        childRef.show() ;
    },

    delete_page(){
      var page_id = this.page_id > 0 ? this.page_id : 0 ;
      var that = this ;
      var url = DocConfig.server+'/api/page/delete';

      this.$confirm(that.$t('comfirm_delete'), ' ', {
        confirmButtonText: that.$t('confirm'),
        cancelButtonText: that.$t('cancel'),
        type: 'warning'
      }).then(() => {
        var params = new URLSearchParams();
        params.append('page_id',  page_id);
        that.axios.post(url, params)
        .then(function (response) {
          if (response.data.error_code === 0 ) {
            that.$router.push({path: '/' + that.item_id}) ;
            window.location.reload();
          }else{
            that.$alert(response.data.error_message);
          }
        });
      }).catch(() => {});
    },
    onCopy(){
      this.$message(this.$t("copy_success"));
    },
  },
  mounted () {
    var that = this ;
    document.onkeydown=function(e){  //对整个页面文档监听 其键盘快捷键
      var keyNum=window.event ? e.keyCode :e.which;  //获取被按下的键值
      if (keyNum == 69 && e.ctrlKey) {  //Ctrl +e 为编辑
        that.edit_page();
        e.preventDefault();
      };

      if (keyNum == 46 && e.ctrlKey) {  //Ctrl +del 为删除
        that.delete_page();
        e.preventDefault();
      };

    }
  }
};
</script>

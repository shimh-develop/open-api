<template>

  <div class="user">

    <el-row :gutter="4">
      <el-col :span="4">
        <el-input size="small" v-model="username" placeholder="用户名"></el-input>
      </el-col>
      <el-col :span="3">
        <el-button size="small" @click="onSubmit">{{$t('search')}}</el-button>
      </el-col>
      <el-col :span="2">
        <el-button type="primary" size="small" @click="dialogAddVisible = true">{{$t('add_user')}}</el-button>
      </el-col>
    </el-row>

    <el-table
      border
      :data="users"
      style="width: 100%">
      <el-table-column
        prop="username"
        label="用户名"
        width="200">
      </el-table-column>
      <el-table-column
        prop="admin"
        label="用户角色"
        :formatter="userRole"
        width="100">
      </el-table-column>
      <el-table-column
        prop="createDate"
        label="创建时间"
        width="160">
      </el-table-column>
      <el-table-column
        prop="item_domain"
        label="操作">
        <template slot-scope="scope">
          <el-button @click="click_password(scope.row)" type="text" size="small">修改密码</el-button>
          <el-button @click="delete_user(scope.row)" type="text" size="small">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog :visible.sync="dialogVisible" :modal="false" width="300px">
      <el-form>
        <el-form-item label="">
          <el-input type="password" placeholder="新密码" v-model="Form.new_password"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">{{$t('cancel')}}</el-button>
        <el-button type="primary" @click="change_password">{{$t('confirm')}}</el-button>
      </div>
    </el-dialog>

    <el-dialog :visible.sync="dialogAddVisible" :modal="false" width="300px">
      <el-form>
        <el-form-item label="">
          <el-input type="text" placeholder="用户名" v-model="addForm.username"></el-input>
        </el-form-item>
        <el-form-item label="">
          <el-input type="password" placeholder="密码" v-model="addForm.password"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogAddVisible = false">{{$t('cancel')}}</el-button>
        <el-button type="primary" @click="add_user">{{$t('confirm')}}</el-button>
      </div>
    </el-dialog>

  </div>

</template>

<style scoped>
  .user {
    margin: 20px 60px;
  }

  .el-table {
    margin-top: 30px;
  }

</style>

<script>

  export default {
    data() {
      return {
        users: [],
        username: '',
        Form: {
          new_password: ''
        },
        addForm: {
          username: '',
          password: '',
        },
        dialogVisible: false,
        dialogAddVisible: false,
        password_uid: ''
      };
    },
    methods: {
      get_user_list() {
        var that = this;
        var url = DocConfig.server + '/user/listUsers';

        var params = new URLSearchParams();
        params.append('username', this.username);
        that.axios.post(url, params).then(function (response) {
          if (response.data.error_code === 0) {
            var json = response.data.data;
            that.users = json;
          } else {
            that.$alert(response.data.error_message);
          }
        });
      },
      userRole(row, column) {
        return row.admin ? '管理员': '普通用户'
      },
      onSubmit() {
        this.get_user_list();
      },
      delete_user(row) {
        var that = this;
        var url = DocConfig.server + '/user/delete';
        this.$confirm(that.$t('confirm_delete'), ' ', {
          confirmButtonText: that.$t('confirm'),
          cancelButtonText: that.$t('cancel'),
          type: 'warning'
        }).then(() => {
          var params = new URLSearchParams();
          params.append('id', row.id);
          that.axios.post(url, params)
            .then(function (response) {
              if (response.data.error_code === 0) {
                that.$message.success("删除成功");
                that.get_user_list();
                that.username = '';
              } else {
                that.$alert(response.data.error_message);
              }

            });
        })
      },
      click_password(row) {
        this.dialogVisible = true;
        this.password_uid = row.username
      },
      change_password() {
        var that = this;
        var url = DocConfig.server + '/user/changePassword';

        var params = new URLSearchParams();
        params.append('username', that.password_uid);
        params.append('password', this.Form.new_password);

        that.axios.post(url, params)
          .then(function (response) {
            if (response.data.error_code === 0) {
              that.dialogVisible = false;
              that.Form.new_password = '';
              that.$message.success(that.$t("success"));

            } else {
              that.$alert(response.data.error_message);
            }

          })
          .catch(function (error) {
            console.log(error);
          });
      },
      add_user() {
        var that = this;
        var url = DocConfig.server + '/user/add';

        var params = new URLSearchParams();
        params.append('username', that.addForm.username);
        params.append('password', this.addForm.password);

        that.axios.post(url, params)
          .then(function (response) {
            if (response.data.error_code === 0) {
              that.dialogAddVisible = false;
              that.addForm.password = '';
              that.addForm.username = '';
              that.$message.success(that.$t("add_user_success"));
              that.get_user_list();
            } else {
              that.$message({message: response.data.error_message, type: 'error', showClose: true});
            }
          })
          .catch(function (error) {
          });
      }
    },
    mounted() {
      this.get_user_list();

    },
    beforeDestroy() {
      this.$message.closeAll();
      /*去掉添加的背景色*/
      document.body.removeAttribute("class", "grey-bg");
    }
  }
</script>

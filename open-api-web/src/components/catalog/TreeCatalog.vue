<template>
<div>
  <el-table
    v-if="catalogs && catalogs.length"
    :data="catalogs"
    border
    style="width: 100%">
    <el-table-column type="expand">
      <template slot-scope="scope">
        <tree-catalog @edit="edit" @delete_cat="delete_cat" :catalogs="scope.row.catalogs"></tree-catalog>
      </template>
    </el-table-column>
    <el-table-column
      label="目录名"
      prop="cat_name">
    </el-table-column>
    <el-table-column
      label="添加时间"
      prop="create_time">
    </el-table-column>
    <el-table-column
      label="序号"
      prop="s_number">
    </el-table-column>
    <el-table-column
      prop=""
      width="110"
      :label="$t('operation')">
      <template slot-scope="scope">
        <el-button @click="edit(scope.row)" type="text" size="small">{{$t('edit')}}</el-button>
        <el-button @click="delete_cat(scope.row.cat_id)" type="text" size="small">{{$t('delete')}}</el-button>
      </template>
    </el-table-column>
  </el-table>

</div>

</template>

<script>
  export default {
    name: "TreeCatalog",
    props: {
      catalogs: Array
    },
    methods: {
      edit(category) {
        this.$emit('edit', category)
      },
      delete_cat(cat_id) {
        this.$emit('delete_cat', cat_id)
      }
    }
  }
</script>

<style >
  .el-table__expanded-cell[class*=cell] {
    padding: 10px 10px!important;
  }
</style>

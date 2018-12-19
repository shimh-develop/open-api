<template>

  <div>

    <el-alert
      title="没有访问权限 2秒后跳转开放平台首页"
      type="error"
      center
      show-icon>
    </el-alert>

  </div>

</template>

<script>
  export default {
    name: "NoAccessPermission",
    created() {
      setTimeout(() => {
        this.$router.push({
          path: '/'
        });
      }, 2000)
    }
  }
</script>

<style scoped>

</style>

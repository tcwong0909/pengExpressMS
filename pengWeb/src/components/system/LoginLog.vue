<template>
  <el-table
    :data="loginLogs"
    style="width: 100%">
    <el-table-column
      type="index"
      label="序号">
    </el-table-column>
    <el-table-column
      prop="username"
      label="登陆账号"
      width="180">
    </el-table-column>
    <el-table-column
      prop="loginIp"
      label="登陆IP"
      width="180">
    </el-table-column>
    <el-table-column
      prop="loginTime"
      label="登陆时间">
    </el-table-column>
  </el-table>

</template>

<script>
  export default {
    name: "LoginLog",
    data() {
      return {
        loginLogs:[],
        loginLogsRequest:{}
      }
    },
    methods: {
      getLoginLogs(){
        this.postRequest("/pengms/loginlog/listLoginLog", this.loginLogsRequest).then(res => {
          this.loginLogs = res.data.list;
        });
      }
    },
    mounted() {
      this.getLoginLogs();
    }
  }
</script>

<style scoped>

</style>

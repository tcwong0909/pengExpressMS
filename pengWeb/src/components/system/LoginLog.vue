<template>
  <div>
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

    <div style="display: flex;justify-content: flex-end;padding: 15px">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        layout="total, sizes, prev, pager, next, jumper"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="pageSize"
        :total="total">
      </el-pagination>
    </div>
  </div>


</template>

<script>
  export default {
    name: "LoginLog",
    data() {
      return {
        total:null,
        pageSize:10,
        currentPage:1,
        loginLogs:[],
        loginLogsRequest:{}
      }
    },
    methods: {
      getLoginLogs(){
        this.loginLogsRequest.pageNum = this.currentPage;
        this.loginLogsRequest.pageSize = this.pageSize;
        this.postRequest("/pengms/loginlog/listLoginLog", this.loginLogsRequest).then(res => {
          this.loginLogs = res.data.list;
          this.total = res.data.total;
        });
      },
      handleSizeChange(size){
        this.pageSize = size;
        this.getLoginLogs();
      },
      handleCurrentChange(page){
        this.currentPage = page;
        this.getLoginLogs();
      }
    },
    mounted() {
      this.getLoginLogs();
    }
  }
</script>

<style scoped>

</style>

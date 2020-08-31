<template>
  <div v-loading="loading" element-loading-text="拼命加载中"  >
    <div>
      <el-card  body-style="padding:10px" shadow="never" style="display: flex;align-items: center">
        <el-form>
          <tr>
            <td>
              <el-input placeholder="请输入内容" suffix-icon="el-icon-search" v-model="searchSyslog.fkTypeid">
                <template slot="prepend">操作类型</template>
              </el-input>
            </td>
            <td>
              <el-input placeholder="请输入内容" suffix-icon="el-icon-search" v-model="searchSyslog.behavior">
                <template slot="prepend">内容关键字</template>
              </el-input>
            </td>
            <td style="padding-left: 10px">
              <el-tag>异常状态</el-tag>
              <template>
                <el-select v-model="searchSyslog.isexception" placeholder="请选择">
                  <el-option label="全部" :value="''"></el-option>
                  <el-option label="正常" :value="1"></el-option>
                  <el-option label="异常" :value="2"></el-option>
                </el-select>
              </template>
            </td>
            <td style="padding-left: 10px">
              <el-button type="danger" icon="el-icon-full-screen" @click="resetSearch">重置</el-button>
            </td>
            <td style="padding-left: 10px">
              <el-button type="primary" icon="el-icon-search" @click="doSearch">搜索</el-button>
            </td>
          </tr>
        </el-form>
      </el-card>
    </div>
    <el-card shadow="never" body-style="padding:0;padding-top:1px">
      <div style="margin-top: 5px;">
        <el-table
          :data="systemLogs"
          border
          style="width: 100%">
          <el-table-column
            type="index"
            label="序号">
          </el-table-column>
          <el-table-column
            prop="operationType"
            label="操作类型"
            width="80">
          </el-table-column>
          <el-table-column
            prop="username"
            width="80"
            label="用户名">
          </el-table-column>
          <el-table-column
            prop="userAccount"
            width="80"
            label="用户账号">
          </el-table-column>
          <el-table-column
            prop="ip"
            width="100"
            label="登录IP">
          </el-table-column>
          <el-table-column
            prop="description"
            label="操作描述"
            width="150">
          </el-table-column>
          <el-table-column
            prop="creatTime"
            label="操作时间"
            width="180">
          </el-table-column>
          <el-table-column
            width="80"
            label="是否异常">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.exceptionFlag===1" type="success">正常</el-tag>
              <el-tag v-else-if="scope.row.exceptionFlag===0" >异常</el-tag>
              <el-tag v-else type="warning">未知</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            prop="params"
            label="操作内容"
            width="600">
          </el-table-column>
          <el-table-column
            prop="exceptionError"
            width="800"
            label="异常信息">
          </el-table-column>
        </el-table>
      </div>
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
    </el-card>

  </div>
</template>

<script>
  export default {
    name: "SysLog",
    data(){
      return{
        loading:true,
        total:null,
        pageSize:10,
        currentPage:1,
        searchSyslog:{
          fkTypeid:'',
          fkUserid:'',
          isexception:'',
          behavior:''
        },
        syslogs:[],
        systemLogRequest:{},
        systemLogs:[],
      }
    },
    watch:{
      searchSyslog:{
        handler(){
          this.doSearch();
        },
        immediate:true,
        deep:true
      }
    },
    mounted(){
      this.loadSyslogs();
      this.getSystemLogs();
    },
    methods:{
      resetSearch(){
        this.searchSyslog={
          fkTypeid:'',
          fkUserid:'',
          isexception:'',
          behavior:''
        }
      },

      getSystemLogs() {
        this.systemLogRequest.pageNum = this.currentPage;
        this.systemLogRequest.pageSize = this.pageSize;
        this.postRequest("/pengms/operateLog/listOperateLog", this.systemLogRequest).then(res => {
          this.systemLogs = res.data.list;
          this.total = res.data.total;

        });
      },
      loadSyslogs(){
        this.postRequest("/pengms/syslog/getAllByPage?page="+this.currentPage+"&size="+this.pageSize+"&behavior="+this.searchSyslog.behavior+
          "&fkTypeid="+this.searchSyslog.fkTypeid+"&fkUserid="+this.searchSyslog.fkUserid+"&isexception="+this.searchSyslog.isexception).then(res=>{
          if (res){
            this.loading=false;
            this.syslogs=res.data.data;
            this.total = res.data.total;
          }
        })
      },
      doSearch(){
        this.currentPage = 1;
        this.pageSize=10;
        this.postRequest("/pengms/syslog/getAllByPage?page="+this.currentPage+"&size="+this.pageSize+"&behavior="+this.searchSyslog.behavior+
          "&fkTypeid="+this.searchSyslog.fkTypeid+"&fkUserid="+this.searchSyslog.fkUserid+"&isexception="+this.searchSyslog.isexception).then(res=>{
          if (res){
            this.syslogs=res.data.data;
            this.total = res.data.total;
          }
        })
      },
      handleSizeChange(size){
        this.pageSize = size;
        this.getSystemLogs();
      },
      handleCurrentChange(page){
        this.currentPage = page;
        this.getSystemLogs();
      },
    }
  }
</script>

<style scoped>

</style>

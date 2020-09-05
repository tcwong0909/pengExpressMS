<template>
  <div v-loading="loading" element-loading-text="拼命加载中">
    <div  >
      <el-button type="primary" icon="el-icon-plus" size="small" @click="showDialog('add')">添加角色</el-button>
    </div>
    <el-card shadow="never" body-style="padding:0;padding-top:1px">
      <div>
        <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible">
          <el-form :model="role">
            <el-row style="margin-bottom: 15px">
              <el-tag>角色名称</el-tag>
              <el-input v-model="role.rolename" style="width: 10vw"></el-input>
            </el-row>
            <el-row>
              <el-tag>角色权限</el-tag>
              <el-input v-model="role.rolepurview" style="width: 10vw"></el-input>
            </el-row>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="addRole">确 定</el-button>
          </div>
        </el-dialog>
      </div>
      <div style="margin-top: 5px;">
        <el-table
          :data="roleList"
          border
          style="width: 100%">
          <el-table-column
            type="index"
            width="50"
            label="序号">
          </el-table-column>
          <el-table-column
            prop="name"
            label="角色名称"
            width="110">
          </el-table-column>
          <el-table-column
            prop="description"
            label="角色描述"
            width="200">
          </el-table-column>
          <el-table-column
            prop="editorAccount"
            label="编辑人"
            width="110">
          </el-table-column>
          <el-table-column
            prop="updateTime"
            label="编辑时间"
            width="180">
          </el-table-column>
          <el-table-column
            label="操作">
            <template slot-scope="scope">
              <el-button type="primary" size="mini" @click="showDialog(scope.row)">编辑</el-button>
              <el-button type="primary" size="mini" @click="showDialog(scope.row)">权限管理</el-button>
              <el-button type="danger" size="mini" @click="deleteById(scope.row.id)">删除</el-button>
            </template>
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
    name: "RolesManager",
    data(){
      return{
        loading:true,
        total:0,
        pageSize:10,
        currentPage:1,
        dialogFormVisible:false,
        dialogTitle:'',
        role:{
          rolename:'',
          rolepurview:''
        },
        roleRequest:{},
        roleList:[],
      }
    },
    mounted(){
      this.getRoleList();
    },
    methods:{

      handleSizeChange(size){
        this.pageSize = size;
        this.getRoleList();
      },
      handleCurrentChange(page){
        this.currentPage = page;
        this.getRoleList();
      },

      initRole(){
        this.role={
          rolename:'',
          rolepurview:''
        }
      },

      addRole(){
        if (this.role.roleid) {
          this.putRequest('/pengms/role/edit',this.role).then(res=>{
            if (res){
              this.dialogFormVisible = false;
              this.initRole();
              this.getRoleList();
            }
          });
          return;
        }
        this.postRequest("/pengms/role/add",this.role).then(res=>{
          if (res){
            this.dialogFormVisible = false;
            this.initRole();
            this.getRoleList();
          }
        })
      },
      getRoleList() {
        const roleRequest = this.roleRequest;
        roleRequest.pageNum = this.currentPage;
        roleRequest.pageSize = this.pageSize;
        this.postRequest("/pengms/role/listRoles", roleRequest).then(result => {
          if (result.code === "0000") {
            this.loading = false;
            this.roleList = result.data.list;
            this.total = result.data.total;
          }
        });
      },
      deleteById(id){
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.deleteRequest("/pengms/role/delete/"+id).then(res=>{
              if (res){
                this.getRoleList();
              }
            }
          );
          this.$message({
            type: 'success',
            message: '删除成功!'
          });
        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      showDialog(data){
        this.dialogFormVisible=true;
        if(data === 'add'){
          this.dialogTitle = '添加角色';
          return;
        }
        this.dialogTitle='编辑';
        this.role = data;
      },
    }
  }
</script>

<style scoped>

</style>

<template>
  <div v-loading="loading" element-loading-text="拼命加载中">
    <div>
      <el-card  body-style="padding:10px" shadow="never" style="display: flex;align-items: center">
        <el-form>
          <tr>
            <td style="width: 16vw">
              <el-input placeholder="请输入姓名" suffix-icon="el-icon-search" v-model="userRequest.username">
                <template slot="prepend">用户姓名</template>
              </el-input>
            </td >
            <td style="width: 16vw">
              <el-input placeholder="请输入账号" suffix-icon="el-icon-search" v-model="userRequest.userAccount">
                <template slot="prepend" >用户账号</template>
              </el-input>
            </td>
            <td style="padding-left: 10px">
              <el-tag>性别</el-tag>
              <template>
                <el-select v-model="userRequest.sex" placeholder="请选择">
                  <el-option label="男" :value="1"></el-option>
                  <el-option label="女" :value="0"></el-option>
                </el-select>
              </template>
            </td>
            <td style="padding-left: 10px">
              <el-tag>角色</el-tag>
              <template >
                <el-select
                  v-model="userRequest.roleId"
                  @visible-change="selectSearchChanges"
                  placeholder="请选择">
                  <el-option
                    v-for="role in roles"
                    :key="role.roleid"
                    :label="role.rolename"
                    :value="role.roleid">
                  </el-option>
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
      <div  >
        <el-button type="primary" icon="el-icon-plus" size="small" @click="showDialog('add')" :disabled="true">添加用户</el-button>
        <el-button type="danger" icon="el-icon-minus" size="small" @click="multiDelete" :disabled="multipleSelection.length===0 || true">批量删除</el-button>
      </div>
      <div>
        <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible">
          <el-form :model="user">
            <el-row style="margin-bottom: 5px">
              <el-col :span="12">
                <el-tag style="width: 5vw">用户姓名</el-tag>
                <el-input v-model="user.username" style="width: 10vw"></el-input>
              </el-col>
              <el-col :span="12">
              <el-tag style="width: 5vw">性别</el-tag>
              <el-select v-model="user.sex" style="width: 10vw">
                <el-option label="男" value="1"></el-option>
                <el-option label="女" value="2"></el-option>
              </el-select>
              </el-col>
            </el-row>
            <el-row style="margin-bottom: 5px">
              <el-col :span="12">
              <el-tag style="width: 5vw">用户账号</el-tag>
              <el-input v-model="user.account" style="width: 10vw" ></el-input>
              </el-col>
              <el-col :span="12">
              <el-tag style="width: 5vw">联系电话</el-tag>
              <el-input v-model="user.phone" style="width: 10vw" ></el-input>
              </el-col>
            </el-row>
            <el-row style="margin-bottom: 5px">
              <el-col :span="12">
              <el-tag style="width: 5vw">邮箱</el-tag>
              <el-input v-model="user.email" style="width: 10vw" ></el-input>
              </el-col>
            </el-row>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible = false">取 消</el-button>
            <el-button type="primary" @click="addUser">确 定</el-button>
          </div>
        </el-dialog>
      </div>
      <div>
        <el-dialog title="绑定角色" :visible.sync="bindDialog">
          <el-form :model="user">
            <el-row style="margin-bottom: 10px">
              <el-tag style="width: 6vw">用户账号</el-tag>
              <el-input  :disabled="true" v-model="user.account" style="width: 10vw" placeholder="吨"></el-input>
            </el-row>
            <el-row style="margin-bottom: 6px">
              <el-tag style="width: 6vw">要绑定角色</el-tag>
              <template >
                <el-select v-model="user.fkRoleid" placeholder="请选择" style="width: 10vw">
                  <el-option
                    v-for="role in roles"
                    :key="role.roleid"
                    :label="role.rolename"
                    :value="role.roleid">
                  </el-option>
                </el-select>
              </template>
            </el-row>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="bindDialog = false">取 消</el-button>
            <el-button type="primary" @click="toBind">确 定</el-button>
          </div>
        </el-dialog>
      </div>
      <div style="margin-top: 5px;">
        <el-table
          :data="userList"
          border
          style="width: 100%"
          @selection-change="handleSelectionChange">
          <el-table-column
            type="selection"
            width="50">
          </el-table-column>
          <el-table-column
            type="index"
            width="50"
            label="序号">
          </el-table-column>
          <el-table-column
            prop="username"
            label="用户姓名"
            width="80">
          </el-table-column>
          <el-table-column
            width="60"
            label="性别">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.sex===1" type="success">男</el-tag>
              <el-tag v-else-if="scope.row.sex===0" >女</el-tag>
              <el-tag v-else type="warning">未知</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            prop="userAccount"
            width="80"
            label="用户账号">
          </el-table-column>
          <el-table-column
            prop="roleName"
            label="角色"
            width="120">
          </el-table-column>
          <el-table-column
            prop="createTime"
            label="创建时间"
            width="180">
          </el-table-column>
          <el-table-column
            prop="updateTime"
            label="修改时间"
            width="180">
          </el-table-column>
          <el-table-column
            label="操作">
            <template slot-scope="scope">
              <el-button type="primary" size="mini" @click="showDialog(scope.row)" :disabled="true">编辑</el-button>
              <el-button type="primary" size="mini" @click="bindRole(scope.row)" :disabled="true">绑定角色</el-button>
              <el-button type="danger" size="mini" @click="deleteById(scope.row.id)" :disabled="true">删除</el-button>
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
    name: "UserManager",
    data(){
      return{
        loading:true,
        multipleSelection: [],
        ids:"",
        total:0,
        pageSize:10,
        currentPage:1,
        bindDialog:false,
        dialogFormVisible:false,
        dialogTitle:'',
        user:{
          userid:'',
          username:'',
          sex:'',
          account: '',
          phone:'',
          email:'',
          fkRoleid:''
        },
        roles:[],
        userRequest:{},
        userList:[]
      }
    },
    mounted(){
      this.getUserList();
    },
    methods:{
      toBind(){
        this.addUser();
        this.bindDialog=false;
      },
      bindRole(data){
        this.bindDialog=true;
        this.user = data;
        this.getRequest("/pengms/role/getAll").then(res=>{
          if (res){
            this.roles=res.data;
          }
        })
      },
      resetSearch(){
        this.userRequest={
          username:'',
          userAccount:'',
          roleId:'',
          sex:''
        }
      },
      initUser(){
        this.user={
          username:'',
          sex:null,
          account: '',
          phone:'',
          email:'',
        }
      },

      doSearch() {
        this.getUserList();
      },
      addUser(){
        if (this.user.userid) {
          this.putRequest('/pengms/user/edit',this.user).then(res=>{
            if (res){
              this.dialogFormVisible = false;
              this.initUser();
              this.loadUsers();
            }
          });
          return;
        }
        this.postRequest("/pengms/user/add",this.user).then(res=>{
          if (res){
            this.dialogFormVisible = false;
            this.initUser();
            this.loadUsers();
          }
        })
      },
      selectSearchChanges(){
        this.getRequest("/pengms/role/getAll").then(res=>{
          if (res){
            this.roles=res.data;
          }
        })
      },
      getUserList() {
        const request = this.userRequest;
        request.pageNum = this.currentPage;
        request.pageSize = this.pageSize;
        this.postRequest("/pengms/user/listUsers",request).then(result => {
          if (result.code === "0000") {
            this.loading = false;
            this.total = result.data.total;
            this.userList = result.data.list;
          }
        })
      },
      handleSizeChange(size){
        this.pageSize = size;
        this.loadUsers();
      },
      handleCurrentChange(page){
        this.currentPage = page;
        this.loadUsers();
      },
      handleSelectionChange(val) {
        this.multipleSelection=val;

      },
      deleteById(id){
        let ids = id;
        this.deleteByIds(ids);
        this.loadUsers();
      },
      multiDelete(){
        let ids = '';
        this.multipleSelection.forEach(data=>{
          ids  += data.userid+',';
        });
        this.deleteByIds(ids);
        this.getUserList();
      },

      deleteByIds(data){
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.deleteRequest("/pengms/user/delete/"+data).then(res=>{
              if (res){
                this.getUserList();
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
          this.dialogTitle = '添加用户';
          return;
        }
        this.dialogTitle='编辑';
        this.user = data;
      },
    }
  }
</script>

<style scoped>

</style>

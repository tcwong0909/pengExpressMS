<template>
  <div v-loading="loading" element-loading-text="拼命加载中" >
    <div>
      <el-card  body-style="padding:10px" shadow="never" style="display: flex;align-items: center">
        <el-form>
          <tr>
            <td>
              <el-input placeholder="请输入内容" suffix-icon="el-icon-search" v-model="pageRequest.name">
                <template slot="prepend">司机姓名</template>
              </el-input>
            </td>
            <td style="padding-left: 10px">
              <el-tag>工作状态</el-tag>
              <template>
                <el-select v-model="pageRequest.state" placeholder="请选择">
                  <el-option label="全部" :value="''"></el-option>
                  <el-option label="承运中" :value="1"></el-option>
                  <el-option label="空闲" :value="2"></el-option>
                </el-select>
              </template>
            </td>
            <td style="padding-left: 10px">
              <el-button type="danger" icon="el-icon-full-screen" @click="resetSearch">重置</el-button>
            </td>
            <td style="padding-left: 10px">
              <el-button type="primary" icon="el-icon-search" @click="pageDriver">搜索</el-button>
            </td>
          </tr>
        </el-form>
      </el-card>
    </div>
    <el-card shadow="never" body-style="padding:0;padding-top:1px">
    <div >
      <el-button type="primary" icon="el-icon-plus" size="small" @click="showDialog('add')">添加驾驶员</el-button>
      <el-button type="danger" icon="el-icon-minus" size="small" @click="multiDelete" :disabled="multipleSelection.length===0">批量删除</el-button>
    </div>
    <div>
      <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible">
        <el-form :model="driver">
          <el-row style="margin-bottom: 5px">
            <el-col :span="12">
              <el-tag style="width:80px">驾驶员姓名</el-tag>
              <el-input v-model="driver.name" style="width: 10vw"></el-input>
            </el-col>
            <el-col :span="12">
              <el-tag style="width:70px">性别</el-tag>
              <el-radio v-model="driver.sex" :label= 1>男</el-radio>
              <el-radio v-model="driver.sex" :label= 2>女</el-radio>
            </el-col>
          </el-row>
          <el-row style="margin-bottom: 5px">
            <el-col :span="12">
            <el-tag style="width:80px" >出生日期</el-tag>
            <el-date-picker
              style="width: 10vw"
              v-model="driver.birthTime"
              type="date"
              placeholder="选择日期"
              value-format="yyyy-MM-dd HH:mm:ss">
            </el-date-picker>
            </el-col>
            <el-col :span="12">
            <el-tag >联系电话</el-tag>
            <el-input v-model="driver.phoneNo" style="width: 10vw" ></el-input>
            </el-col>
          </el-row>
          <el-row style="margin-bottom: 5px">
            <el-col :span="12">
            <el-tag style="width:80px">身份证号码</el-tag>
            <el-input v-model="driver.identityNo" style="width: 10vw" ></el-input>
            </el-col>
          </el-row>
          <el-row style="margin-bottom: 5px">
            <el-col :span="12">
            <el-tag style="width:80px">工作状态</el-tag>
            <el-radio v-model="driver.state" :label= 1>承运中</el-radio>
            <el-radio v-model="driver.state" :label= 2>空闲</el-radio>
            </el-col>
            <el-col :span="12">
            <el-tag style="width:70px">备注</el-tag>
            <el-input v-model="driver.remark" style="width: 10vw"></el-input>
            </el-col>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="addDriver">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <div style="margin-top: 5px">
      <el-table
        :data="drivers"
        border
        v-loading="loading"
        element-loading-text="拼命加载中"
        style="width: 100%"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="50">
        </el-table-column>
        <el-table-column
          prop="name"
          label="姓名"
          width="80">
        </el-table-column>
        <el-table-column
          width="50"
          label="性别">
          <template slot-scope="scope">
            <span v-if="scope.row.sex ===1">男</span>
            <span v-else-if="scope.row.sex ===2">女</span>
            <span v-else>未知</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="birthTime"
          width="120"
          label="出生日期">
        </el-table-column>
        <el-table-column
          prop="phoneNo"
          width="120"
          label="电话">
        </el-table-column>
        <el-table-column
          prop="identityNo"
          label="身份证号码"
          width="130">
        </el-table-column>
        <el-table-column
          prop="truckteam.teamname"
          label="所属车队名称"
          width="70">
        </el-table-column>
        <el-table-column
          width="80"
          label="工作状态">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.state===1" type="success">承运中</el-tag>
            <el-tag v-else-if="scope.row.state===2" >空闲</el-tag>
            <el-tag v-else type="warning">未知</el-tag>
          </template>
        </el-table-column>
        <el-table-column
          prop="remark"
          width="100"
          label="备注">
        </el-table-column>
        <el-table-column
          prop="checkTime"
          width="200"
          label="加入时间">
        </el-table-column>
        <el-table-column
          prop="updateTime"
          width="200"
          label="修改时间">
        </el-table-column>
        <el-table-column
          label="操作">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="showDialog(scope.row)">编辑</el-button>
            <el-button type="danger" size="mini" @click="deleteDriver(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <div style="display: flex;justify-content: flex-end;padding: 15px">
      <el-pagination
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="pageRequest.pageNum"
        layout="total, sizes, prev, pager, next, jumper"
        :page-sizes="[5, 10, 15, 20]"
        :page-size="pageRequest.pageSize"
        :total="total">
      </el-pagination>
    </div>
    </el-card>
  </div>
</template>

<script>
  export default {
    name: "driver",
    data(){
      return{
        loading:true,
        truckTeams:[],
        multipleSelection: [],
        ids:"",
        total:null,
        dialogFormVisible:false,
        dialogTitle:'',
        searchDriver:{
          name:'',
          state:''
        },
        driver:{
          id:"",
          name:'',
          sex:null,
          birthTime: null,
          phoneNo:'',
          identityNo:'',
          state:null,
          remark:'',
        },
        drivers:[],
        pageRequest:{
          name:"",
          state:"",
          pageNum:1,
          pageSize:10,
        },
        deleteRequest:{
          idList:[]
        },
      }
    },
    watch:{
      searchDriver:{
        handler(){
          this.doSearch();
        },
        deep:true,
        immediate:true
      }
    },
    mounted(){
      this.pageDriver();
    },
    methods:{
      selectChanges(){
        this.loadTruckTeams();
      },
      resetSearch(){
        this.searchDriver={
          name:'',
          fkTeamid:'',
          state:''
        }
      },
      initdriver(){
        this.driver={
          name:'',
          sex:null,
          birthTime: null,
          phoneNo:'',
          identityNo:'',
          state:null,
          remark:'',
        }
      },

      addDriver(){
        if (this.driver.id) {
          this.postRequest('/pengms/driver/edit',this.driver).then(res=>{
            if (res){
              this.dialogFormVisible = false;
              this.initdriver();
              this.pageDriver();
            }
          });
          return;
        }
        this.postRequest("/pengms/driver/add",this.driver).then(res=>{
          if (res){
            this.dialogFormVisible = false;
            this.initdriver();
            this.pageDriver();
          }
        })
      },
      pageDriver(){
        this.postRequest("/pengms/driver/page",this.pageRequest).then(res=>{
          if (res){
            this.loading=false;
            this.drivers=res.data.data;
            this.total = res.data.total;
          }
        })
      },
      doSearch(){
        this.pageDriver();
      },
      handleSizeChange(size){
        this.pageRequest.pageSize = size;
        this.pageDriver();
      },
      handleCurrentChange(page){
        this.pageRequest.pageNum = page;
        this.pageDriver();
      },
      handleSelectionChange(val) {
        this.multipleSelection=val;

      },
      deleteDriver(id){
        let param = {
          idList:[]
        };
        param.idList.push(id);
        this.deleteDrivers(param);
        this.pageDriver();
      },
      multiDelete(){
        let param = {
          idList:[]
        };
        this.multipleSelection.forEach((item,index)=>{
          param.idList.push(item.id);
        });
        this.deleteDrivers(param);
        this.pageDriver();
      },

      deleteDrivers(data){
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.postRequest("/pengms/driver/delete/",data).then(res=>{
              if (res){
                this.pageDriver();
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
          this.initdriver();
          this.dialogTitle = '添加驾驶员';
          return;
        }
        this.dialogTitle='编辑';
          this.driver.id = data.id,
          this.driver.name = data.name,
          this.driver.sex = data.sex,
          this.driver.birthTime = data.birthTime,
          this.driver.phoneNo = data.phoneNo,
          this.driver.identityNo = data.identityNo,
          this.driver.state = data.state,
          this.driver.remark = data.remark

      },
      loadTruckTeams(){
        this.postRequest("/pengms/truckTeam/getAllByPage").then(res=>{
          if (res){
            this.truckTeams=res.data.data;
          }
        })
      },
    }
  }
</script>

<style scoped>

</style>


<template>
  <div  v-loading="loading" element-loading-text="拼命加载中">
    <div>
      <el-card  body-style="padding:10px" shadow="never" style="display: flex;align-items: center">
        <el-form>
          <tr>
            <td>
              <el-input placeholder="请输入车队名称" suffix-icon="el-icon-search" v-model="pageRequest.name">
                <template slot="prepend">车队名称</template>
              </el-input>
            </td>
            <td>
              <el-input placeholder="请输入负责人名称" suffix-icon="el-icon-search" v-model="pageRequest.leader">
                <template slot="prepend">车队负责人</template>
              </el-input>
            </td>
            <td style="padding-left: 10px">
              <el-button type="danger" icon="el-icon-full-screen" @click="resetSearch">重置</el-button>
            </td>
            <td style="padding-left: 10px">
              <el-button type="primary" icon="el-icon-search" @click="pageTruckTeam">搜索</el-button>
            </td>
          </tr>
        </el-form>
      </el-card>
    </div>
    <el-card shadow="never" body-style="padding:0;padding-top:1px">
    <div >
      <el-button type="primary" icon="el-icon-plus" size="small" @click="showAddDialog">添加车辆</el-button>
      <el-button type="danger" icon="el-icon-minus" size="small" @click="multiDelete" :disabled="multipleSelection.length===0">批量删除</el-button>
    </div>
      <!--添加-->
    <div>
      <el-dialog title="添加" :visible.sync="addDialogVisible">
        <el-form :model="truckTeam">
          <el-row :gutter="20" style="margin-bottom: 5px;margin-left: 10px">
            <el-tag style="width: 6vw">车队名称</el-tag>
            <el-input v-model="truckTeam.name" style="width: 10vw;padding-left: 1vw"></el-input>
          </el-row>
          <el-row :gutter="20" style="margin-bottom: 5px;margin-left: 10px">
            <el-tag style="width: 6vw">车队负责人</el-tag>
            <el-input v-model="truckTeam.leader" style="width: 10vw;padding-left: 1vw"></el-input>
          </el-row>
          <el-row :gutter="20" style="margin-bottom: 5px;margin-left: 10px">
            <el-tag style="width: 6vw">备注</el-tag>
            <el-input v-model="truckTeam.remark" style="width: 10vw;padding-left: 1vw"></el-input>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="addTruckTeam">确 定</el-button>
        </div>
      </el-dialog>
    </div>
      <!--编辑-->
      <div>
      <el-dialog title="编辑" :visible.sync="editDialogVisible">
        <el-form :model="editRequest">
          <el-row :gutter="20" style="margin-bottom: 5px;margin-left: 10px">
            <el-tag style="width: 6vw">车队名称</el-tag>
            <el-input v-model="editRequest.name" style="width: 10vw;padding-left: 1vw"></el-input>
          </el-row>
          <el-row :gutter="20" style="margin-bottom: 5px;margin-left: 10px">
            <el-tag style="width: 6vw">车队负责人</el-tag>
            <el-input v-model="editRequest.leader" style="width: 10vw;padding-left: 1vw"></el-input>
          </el-row>
          <el-row :gutter="20" style="margin-bottom: 5px;margin-left: 10px">
            <el-tag style="width: 6vw">备注</el-tag>
            <el-input v-model="editRequest.remark" style="width: 10vw;padding-left: 1vw"></el-input>
          </el-row>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="editDialogVisible = false">取 消</el-button>
          <el-button type="primary" @click="editTruckTeam">确 定</el-button>
        </div>
      </el-dialog>
    </div>
    <div style="margin-top: 5px">
      <el-table
        :data="truckTeams"
        border
        style="width: 100%"
        @selection-change="handleSelectionChange">
        <el-table-column
          type="selection"
          width="50">
        </el-table-column>
        <el-table-column
          prop="id"
          label="编号"
          width="50">
        </el-table-column>
        <el-table-column
          prop="name"
          label="车队名称"
          width="110">
        </el-table-column>
        <el-table-column
          prop="leader"
          width="100"
          label="车队负责人">
        </el-table-column>
        <el-table-column
          prop="remark"
          width="180"
          label="备注">
        </el-table-column>
        <el-table-column
          prop="createTime"
          width="200"
          label="创队时间">
        </el-table-column>
        <!--<el-table-column
          label="数据记录状态"
          width="110">
          <template slot-scope="scope">
            <el-tag v-if="scope.row.isdelete ===1" type="success" > 使用中</el-tag>
            <el-tag v-else-if="scope.row.isdelete ===2" type="danger"> 该记录已删除</el-tag>
            <el-tag v-else>未知</el-tag>
          </template>
        </el-table-column>-->
        <el-table-column
          prop="updateTime"
          label="修改时间"
          width="200">
        </el-table-column>
        <el-table-column
          label="操作">
          <template slot-scope="scope">
            <el-button type="primary" size="mini" @click="showEditDialog(scope.row)">编辑</el-button>
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
    name: "TruckTeam",
    data(){
      return{
        loading:true,
        multipleSelection: [],
        total:null,
        addDialogVisible:false,
        editDialogVisible:false,
        truckTeam:{
          name:'',
          leader:'',
          remark: '',
        },
        editRequest:{
          id:'',
          name:'',
          leader:'',
          remark: '',
        },
        pageRequest:{
          name:'',
          leader:'',
          pageNum: 1,
          pageSize: 10,
        },
        truckTeams:[]
      }
    },
    mounted(){
      this.pageTruckTeam();
    },
    methods:{
      resetSearch(){
        this.pageRequest.name = '';
        this.pageRequest.leader = '';
      },
      initTruckTeam(){
        this.truckTeam={
          name:'',
          leader:'',
          remark: '',
        }
      },

      addTruckTeam(){
        this.postRequest("/pengms/truckTeam/add",this.truckTeam).then(res=>{
          if (res){
            this.addDialogVisible = false;
            this.pageTruckTeam();
          }
        })
      },
      editTruckTeam(){
          this.postRequest('/pengms/truckTeam/edit',this.editRequest).then(res=>{
            if (res){
              this.editDialogVisible = false;
              this.pageTruckTeam();
            }
          });
      },
      pageTruckTeam(){
        this.postRequest("/pengms/truckTeam/page",this.pageRequest).then(res=>{
          if (res){
            this.loading=false;
            this.truckTeams=res.data.data;
            this.total = res.data.total;
          }
        })
      },
      handleSizeChange(size){
        this.pageRequest.pageSize = size;
        this.pageTruckTeam();
      },
      handleCurrentChange(page){
        this.pageRequest.pageNum = page;
        this.pageTruckTeam();
      },
      handleSelectionChange(val) {
        this.multipleSelection=val;
      },
      deleteById(id){
        console.log(id);
        let param = {
          idList: []
        };
        param.idList.push(id);
        this.deleteTruckTeams(param);
        this.pageTruckTeam();
      },
      multiDelete(){
        let param = {
          idList: []
        };
        this.multipleSelection.forEach((item, index) => {
          param.idList.push(item.id);
        });
        this.deleteTruckTeams(param);
        this.pageTruckTeam();
      },

      deleteTruckTeams(data){
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.postRequest("/pengms/truckTeam/delete",data).then(res=>{
              if (res){
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                });
                this.pageTruckTeam();
              }
            }
          );

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });

      },
      showAddDialog(){
        this.addDialogVisible=true;
          this.initTruckTeam();
      },
      showEditDialog(data){
        this.editDialogVisible=true;
        this.editRequest.id = data.id;
        this.editRequest.name = data.name;
        this.editRequest.leader = data.leader;
        this.editRequest.remark = data.remark;
      }
    }
  }
</script>

<style scoped>

</style>

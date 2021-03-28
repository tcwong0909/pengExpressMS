<template>
  <div v-loading="loading" element-loading-text="拼命加载中">
    <div>
      <el-card body-style="padding:10px" shadow="never" style="display: flex;align-items: center">
        <el-form>
          <el-row>
            <td>
              <el-input placeholder="请输入车牌号码" suffix-icon="el-icon-search" v-model="pageRequest.plateNo">
                <template slot="prepend">车牌号码</template>
              </el-input>
            </td>
            <td style="padding-left: 10px">
              <el-tag>车辆类型</el-tag>
              <template>
                <el-select v-model="pageRequest.type" placeholder="请选择">
                  <el-option label="全部" :value="''"></el-option>
                  <el-option
                    v-for="truckItem in truckTypeList"
                    :key="truckItem.value"
                    :label="truckItem.label"
                    :value="truckItem.value">
                  </el-option>
                </el-select>
              </template>
            </td>
            <!--              <td style="padding-left: 10px">-->
            <!--                <el-tag>所属车队</el-tag>-->
            <!--                <template >-->
            <!--                  <el-select-->
            <!--                    v-model="searchTruck.fkTeamid"-->
            <!--                    @visible-change="selectSearchChanges"-->
            <!--                    placeholder="请选择">-->
            <!--                    <el-option label="全部" :value="''"></el-option>-->
            <!--                    <el-option-->
            <!--                      v-for="truckTeam in truckTeams"-->
            <!--                      :key="truckTeam.teamid"-->
            <!--                      :label="truckTeam.teamname"-->
            <!--                      :value="truckTeam.teamid">-->
            <!--                    </el-option>-->
            <!--                  </el-select>-->
            <!--                </template>-->
            <!--              </td>-->
            <td style="padding-left: 10px">
              <el-button type="danger" icon="el-icon-full-screen" @click="resetSearch">重置</el-button>
            </td>
            <td style="padding-left: 10px">
              <el-button type="primary" icon="el-icon-search" @click="pageTruck">搜索</el-button>
            </td>
          </el-row>
        </el-form>
      </el-card>
    </div>
    <el-card shadow="never" body-style="padding:0;padding-top:1px">
      <div>
        <el-button type="primary" icon="el-icon-plus" size="small" @click="showAddDialog()">添加车辆</el-button>
        <el-button type="danger" icon="el-icon-minus" size="small" @click="multiDelete"
                   :disabled="multipleSelection.length===0">批量删除
        </el-button>
      </div>
      <!--添加-->
      <div>
        <el-dialog title="添加" :visible.sync="addDialogVisible">
          <el-form :model="truck">
            <el-row :gutter="20" style="margin-bottom: 5px">
              <el-col :span="12">
                <el-tag>车牌号码</el-tag>
                <el-input v-model="truck.plateNo" style="width: 10vw"></el-input>
              </el-col>
              <el-col :span="12">
                <el-tag style="margin-left: 28px">购车日期</el-tag>
                <el-date-picker
                  v-model="truck.buyTime"
                  style="width: 160px;margin-left: 8px"
                  type="date"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  placeholder="选择日期">
                </el-date-picker>
              </el-col>
            </el-row>
            <el-row :gutter="20" style="margin-bottom: 5px">
              <el-col :span="12">
                <el-tag>车辆类型</el-tag>
                <el-select v-model="truck.type" style="width: 10vw">
                  <el-option label="小型货车" value="10"></el-option>
                  <el-option label="中型货车" value="20"></el-option>
                  <el-option label="大型货车" value="30"></el-option>
                  <el-option label="其他" value="40"></el-option>
                </el-select>
              </el-col>
              <el-col :span="12">
                <el-tag style="margin-left: 28px;width: 5vw">吨位</el-tag>
                <el-input v-model="truck.deadWeight" style="width: 10vw" placeholder="吨"></el-input>
              </el-col>
            </el-row>
            <el-row :gutter="80" style="margin-bottom: 5px">
              <el-col :span="12">
                <el-tag>工作状态</el-tag>
                <el-radio v-model="truck.state" :label=1>承运中</el-radio>
                <el-radio v-model="truck.state" :label=2>空闲</el-radio>
              </el-col>
              <el-col :span="12">
                <el-tag style="width: 5vw">备注</el-tag>
                <el-input v-model="truck.remark" style="width: 10vw"></el-input>
              </el-col>
            </el-row>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="addDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="addTruck">确 定</el-button>
          </div>
        </el-dialog>
      </div>
      <!--编辑-->
      <div>
        <el-dialog title="编辑" :visible.sync="editDialogVisible">
          <el-form :model="editRequest">
            <el-row :gutter="20" style="margin-bottom: 5px">
              <el-col :span="12">
                <el-tag>车牌号码</el-tag>
                <el-input v-model="editRequest.plateNo" style="width: 10vw"></el-input>
              </el-col>
              <el-col :span="12">
                <el-tag style="margin-left: 28px">购车日期</el-tag>
                <el-date-picker
                  v-model="editRequest.buyTime"
                  style="width: 160px;margin-left: 8px"
                  type="date"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  placeholder="选择日期">
                </el-date-picker>
              </el-col>
            </el-row>
            <el-row :gutter="20" style="margin-bottom: 5px">
              <el-col :span="12">
                <el-tag>车辆类型</el-tag>
                <el-select v-model="editRequest.type" style="width: 10vw">
                  <el-option label="小型货车" value="10"></el-option>
                  <el-option label="中型货车" value="20"></el-option>
                  <el-option label="大型货车" value="30"></el-option>
                  <el-option label="其他" value="40"></el-option>
                </el-select>
              </el-col>
              <el-col :span="12">
                <el-tag style="margin-left: 28px;width: 5vw">吨位</el-tag>
                <el-input v-model="editRequest.deadWeight" style="width: 10vw" placeholder="吨"></el-input>
              </el-col>
            </el-row>
            <el-row :gutter="80" style="margin-bottom: 5px">
              <el-col :span="12">
                <el-tag>工作状态</el-tag>
                <el-radio v-model="editRequest.state" :label=1>承运中</el-radio>
                <el-radio v-model="editRequest.state" :label=2>空闲</el-radio>
              </el-col>
              <el-col :span="12">
                <el-tag style="width: 5vw">备注</el-tag>
                <el-input v-model="editRequest.remark" style="width: 10vw"></el-input>
              </el-col>
            </el-row>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="editDialogVisible = false">取 消</el-button>
            <el-button type="primary" @click="editTruck">确 定</el-button>
          </div>
        </el-dialog>
      </div>
      <div style="margin-top: 5px;">
        <el-table
          :data="trucks"
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
            prop="plateNo"
            label="车牌号码"
            width="110">
          </el-table-column>
          <el-table-column
            prop="buyTime"
            width="120"
            label="购车日期">
          </el-table-column>
          <el-table-column
            prop="type"
            width="80"
            label="车辆类型">
          </el-table-column>
          <!--            <el-table-column-->
          <!--              prop="truckteam.checkintime"-->
          <!--              width="100"-->
          <!--              label="创队时间">-->
          <!--            </el-table-column>-->
          <el-table-column
            prop="deadWeight"
            label="吨位"
            width="50">
          </el-table-column>
          <!--            <el-table-column-->
          <!--              prop="truckteam.teamname"-->
          <!--              label="所属车队名称"-->
          <!--              width="110">-->
          <!--            </el-table-column>-->
          <el-table-column
            width="80"
            label="工作状态">
            <template slot-scope="scope">
              <el-tag v-if="scope.row.state===1" type="success">承运中</el-tag>
              <el-tag v-else-if="scope.row.state===2">空闲</el-tag>
              <el-tag v-else type="warning">未知</el-tag>
            </template>
          </el-table-column>
          <el-table-column
            prop="remark"
            width="100"
            label="备注">
          </el-table-column>
          <el-table-column
            prop="createTime"
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
              <el-button type="primary" size="mini" @click="showEditDialog(scope.row)">编辑</el-button>
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
    name: "Truck",
    data() {
      return {
        loading: true,
        truckTypeList: [
          {
            label: "小型货车",
            value: 10
          },
          {
            label: "中型货车",
            value: 20
          },
          {
            label: "大型货车",
            value: 30
          },
          {
            label: "其他",
            value: 40
          },
        ],
        truckTeams: [],
        multipleSelection: [],
        ids: "",
        total: null,
        addDialogVisible: false,
        editDialogVisible: false,
        dialogTitle: '',
        truck: {
          plateNo: '',
          type: null,
          deadWeight: '',
          weightUnit: '',
          state: 2,
          buyTime: '',
          remark: '',
        },
        editRequest: {
          id: "",
          plateNo: '',
          type: null,
          deadWeight: '',
          weightUnit: '',
          state: 2,
          buyTime: '',
          remark: '',
        },
        trucks: [],
        pageRequest: {
          plateNo: "",
          type: "",
          pageNum: 1,
          pageSize: 10,
        }
      }
    },
    // watch:{
    //   searchTruck:{
    //     handler(){
    //       this.doSearch();
    //     },
    //     immediate:true,
    //     deep:true
    //   }
    // },
    mounted() {
      this.pageTruck();
    },
    methods: {
      resetSearch() {
        this.pageRequest = {
          plateNo: '',
          type: '',
        }
      },
      selectSearchChanges() {
        this.loadtruckTeams();
      },
      initTruck() {
        this.truck = {
          plateNo: '',
          type: '10',
          deadWeight: '',
          weightUnit: '',
          state: 2,
          buyTime: '',
          remark: '',
        }
      },
      addTruck() {
        this.postRequest("/pengms/truck/add", this.truck).then(res => {
          if (res) {
            this.addDialogVisible = false;
            this.pageTruck();
          }
        })
      },
      editTruck() {
        this.postRequest('/pengms/truck/edit', this.editRequest).then(res => {
          if (res) {
            this.editDialogVisible = false;
            this.pageTruck();
          }
        });
        return;
      },
      pageTruck() {
        this.postRequest("/pengms/truck/page", this.pageRequest).then(res => {
          this.loading = false;
          if (res) {
            this.trucks = res.data.data;
            this.total = res.data.total;
          }
        })
      },
      // doSearch(){
      //   let number = encodeURI(encodeURI(this.searchTruck.number));
      //   let type = encodeURI(encodeURI(this.searchTruck.type));
      //   let fkTeamid = encodeURI(encodeURI(this.searchTruck.fkTeamid));
      //   let page = 1;
      //   let size = 10;
      //   this.getRequest("/pengms/truck/search?page="+page+"&size="+size+"&number="+number+"&type="+type+"&fkTeamid="+fkTeamid).then(res=>{
      //     this.loading=false;
      //     if (res){
      //       this.trucks=res.data.data;
      //       this.total = res.data.total;
      //     }
      //   })
      // },
      handleSizeChange(size) {
        this.pageRequest.pageSize = size;
        this.pageTruck();
      },
      handleCurrentChange(page) {
        this.pageRequest.pageNum = page;
        this.pageTruck();
      },
      handleSelectionChange(val) {
        this.multipleSelection = val;

      },
      deleteDriver(id) {
        let param = {
          idList: []
        };
        param.idList.push(id);
        this.deleteDrivers(param);
        this.pageTruck();
      },
      multiDelete() {
        let param = {
          idList: []
        };
        this.multipleSelection.forEach((item, index) => {
            param.idList.push(item.id);
          });
        this.deleteDrivers(param);
        this.pageTruck();
      },
      deleteDrivers(data) {
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.postRequest("/pengms/truck/delete", data).then(res => {
              if (res) {
                this.$message({
                  type: 'success',
                  message: '删除成功!'
                });
                this.pageTruck();
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
      showEditDialog(data) {
        this.editDialogVisible = true;
        this.editRequest.id = data.id;
        this.editRequest.plateNo = data.plateNo;
        this.editRequest.type = data.type;
        this.editRequest.deadWeight = data.deadWeight;
        this.editRequest.weightUnit = data.weightUnit;
        this.editRequest.state = data.state;
        this.editRequest.buyTime = date.buyTime;
        this.editRequest.remark = data.remark;
      },
       showAddDialog() {
              this.addDialogVisible = true;
       },
      loadtruckTeams() {
        this.postRequest("/pengms/truckTeam/getAllByPage").then(res => {
          if (res) {
            this.truckTeams = res.data.data;
          }
        })
      },
    }
  }
</script>

<style scoped>

</style>

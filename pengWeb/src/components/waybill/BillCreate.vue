<template>
  <div v-loading="loading" element-loading-text="拼命加载中">
    <div>
      <el-card  body-style="padding:10px" shadow="never" style="display: flex;align-items: center">
        <el-form>
          <tr>
            <td>
              <el-input placeholder="请输入内容" suffix-icon="el-icon-search" v-model="searchCarrierss.sendcompany">
                <template slot="prepend">发货单位</template>
              </el-input>
            </td>
            <td>
              <el-input placeholder="请输入内容" suffix-icon="el-icon-search" v-model="searchCarrierss.receivecompany">
                <template slot="prepend">收货单位</template>
              </el-input>
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
        <el-button type="primary" icon="el-icon-plus" size="small" @click="showDialog('add')">添加承运单</el-button>
        <el-button type="danger" icon="el-icon-minus" size="small" @click="multiDelete" :disabled="multipleSelection.length===0">批量删除</el-button>
      </div>
      <div >
        <el-dialog :title="dialogTitle" :visible.sync="dialogFormVisible">
          <el-steps :active="active" finish-status="success">
            <el-step title="填写承运单"></el-step>
            <el-step title="填写货物单"></el-step>
          </el-steps>
          <div v-show="active ===0" style="padding-top: 2vh">
            <el-form :model="addBillRequest">
              <el-row style="margin-bottom: 5px">
                <el-col :span="12">
                  <el-tag style="width: 7vw">发货单位</el-tag>
                  <el-input v-model="addBillRequest.deliverAddressAdd.companyName" style="width: 10vw"></el-input>
                </el-col>
                <el-col :span="12">
                <el-tag style="width: 9vw">发货单位地址</el-tag>
                <el-input v-model="addBillRequest.deliverAddressAdd.address" style="width: 10vw"></el-input>
                </el-col>
              </el-row>
              <el-row style="margin-bottom: 5px">
                <el-col :span="12">
                <el-tag style="width: 7vw">发货人</el-tag>
                <el-input v-model="addBillRequest.deliverAddressAdd.person" style="width: 10vw"></el-input>
                </el-col>
                <el-col :span="12">
                <el-tag style="width: 9vw">发货人电话</el-tag>
                <el-input v-model="addBillRequest.deliverAddressAdd.phone" style="width: 10vw"></el-input>
                </el-col>
              </el-row>
              <el-row style="margin-bottom: 5px">
                <el-col :span="12">
                <el-tag style="width: 7vw">收货单位</el-tag>
                <el-input v-model="addBillRequest.receiverAddressAdd.companyName" style="width: 10vw"></el-input>
                </el-col>
                <el-col :span="12">
                <el-tag style="width: 9vw">收货单位地址</el-tag>
                <el-input v-model="addBillRequest.receiverAddressAdd.address" style="width: 10vw"></el-input>
                </el-col>
              </el-row>
              <el-row style="margin-bottom: 5px">
                <el-col :span="12">
                <el-tag style="width: 7vw">收货人/联系人</el-tag>
                <el-input v-model="addBillRequest.receiverAddressAdd.person" style="width: 10vw"></el-input>
                </el-col>
                <el-col :span="12">
                <el-tag style="width: 9vw">收货人电话/联系人电话</el-tag>
                <el-input v-model="addBillRequest.receiverAddressAdd.phone" style="width: 10vw"></el-input>
                </el-col>
              </el-row>
              <el-row style="margin-bottom: 5px">
                <el-col :span="12">
                <el-tag style="width: 7vw">承运日期</el-tag>
                <el-date-picker
                  style="width: 10vw;"
                  v-model="addBillRequest.startTime"
                  type="date"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  placeholder="请选择承运日期">
                </el-date-picker>
                </el-col>
                <el-col :span="12">
                <el-tag style="width: 9vw">收货时间</el-tag>
                <el-date-picker
                  v-model="addBillRequest.endTime"
                  type="date"
                  value-format="yyyy-MM-dd HH:mm:ss"
                  style="width: 10vw;"
                  placeholder="请选择收货日期">
                </el-date-picker>
                </el-col>
              </el-row>
<!--              <el-row style="margin-bottom: 5px">-->
<!--                <el-col :span="24">-->
<!--                <el-tag style="width: 7vw">完成情况</el-tag>-->
<!--                <el-radio v-model="addBillRequest.finishedstate" :label= 0>待调度</el-radio>-->
<!--                <el-radio v-model="addBillRequest.finishedstate" :label= 1>已调度</el-radio>-->
<!--                <el-radio v-model="addBillRequest.finishedstate" :label= 2>已签收</el-radio>-->
<!--                <el-radio v-model="addBillRequest.finishedstate" :label= 3>已结算</el-radio>-->
<!--                </el-col>-->
<!--              </el-row>-->
              <el-row style="margin-bottom: 5px">
                <el-col :span="12">
                <el-tag style="width: 7vw">保险费</el-tag>
                <el-input v-model="addBillRequest.costAdd.insurance" style="width: 10vw"></el-input>
                </el-col>
                <el-col :span="12">
                <el-tag style="width: 9vw">运费</el-tag>
                <el-input v-model="addBillRequest.costAdd.transport" style="width: 10vw"></el-input>
                </el-col>
              </el-row>
              <el-row style="margin-bottom: 5px">
                <el-col :span="12">
                  <el-tag style="width: 7vw">其他费用</el-tag>
                  <el-input v-model="addBillRequest.costAdd.extra" style="width: 10vw"></el-input>
                </el-col>
<!--                <el-col :span="12">-->
<!--                <el-tag style="width: 9vw">合计费用</el-tag>-->
<!--                <el-input v-model="totalcost" style="width: 10vw"></el-input>-->
<!--                </el-col>-->
              </el-row>
              <el-row style="margin-bottom: 5px">
                <el-col :span="12">
                <el-tag style="width: 7vw">备注</el-tag>
                <el-input v-model="addBillRequest.remark" style="width: 10vw"></el-input>
                </el-col>
              </el-row>

            </el-form>
            <div slot="footer" class="dialog-footer" style="display: flex;justify-content: flex-end">
              <el-button @click="dialogFormVisible = false" style="margin-top: 12px;" >取 消</el-button>
              <el-button style="margin-top: 12px;" @click="next">下一步</el-button>
            </div>
          </div>
          <div v-show="active ===1" style="padding-top: 2vh">
            <el-form :model="addBillRequest.goodsAdd">
              <el-row style="margin-bottom: 5px">
                <el-tag>货物名称</el-tag>
                <el-input v-model="addBillRequest.goodsAdd.name" style="width: 10vw"></el-input>
                </el-row>
              <el-row style="margin-bottom: 5px">
                <el-tag >货物数量</el-tag>
                <el-input v-model="addBillRequest.goodsAdd.amount" style="width: 10vw"></el-input>
              </el-row>
              <el-row style="margin-bottom: 5px">
                <el-tag>货物重量</el-tag>
                <el-input v-model="addBillRequest.goodsAdd.weight" style="width: 10vw"></el-input>
              </el-row>
              <el-row style="margin-bottom: 5px">
                <el-tag >货物体积</el-tag>
                <el-input v-model="addBillRequest.goodsAdd.volume" style="width: 10vw"></el-input>
              </el-row>

            </el-form>

            <el-button style="margin-top: 12px;" @click="prev">上一步</el-button>

            <div slot="footer" class="dialog-footer" style="display: flex;justify-content: flex-end">
              <el-button @click="dialogFormVisible = false" style="margin-top: 12px;">取 消</el-button>
              <el-button type="primary" @click="addRoadBill" style="margin-top: 12px;">保存</el-button>
            </div>

          </div>
        </el-dialog>
      </div>
      <div style="margin-top: 5px;">
        <el-table
          :data="roadBillList"
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
            prop="deliverAddress.address"
            width="100"
            label="收货地址">
          </el-table-column>
          <el-table-column
          prop="receiverAddress.address"
          width="100"
          label="收货地址">
        </el-table-column>
          <el-table-column
            prop="startTime"
            width="100"
            label="承运日期">
          </el-table-column>
          <el-table-column
            prop="endTime"
            width="100"
            label="收货时间">
          </el-table-column>
          <el-table-column
            prop="stateName"
            width="100"
            label="完成情况">
          </el-table-column>
          <el-table-column
            prop="remark"
            width="100"
            label="费用信息">
            <template slot-scope="scope">
              <el-popover
                placement="top-start"
                title="费用信息"
                width="200"
                trigger="hover">
                <div>
                  保险费:{{scope.row.cost.insurance}}
                </div>
                <div>
                  运费:{{scope.row.cost.transport}}
                </div>
                <div>
                  其他费用:{{scope.row.cost.extra}}
                </div>
                <el-link type="primary" slot="reference">费用详情</el-link>
              </el-popover>
            </template>
          </el-table-column>
          <el-table-column
            prop="cost.total"
            width="80"
            label="合计费用">
          </el-table-column>
          <el-table-column
            prop="createTime"
            width="100"
            label="录入时间">
          </el-table-column>
            <el-table-column
            prop="createBy"
            width="70"
            label="业务员">
            </el-table-column>
          <el-table-column
            prop="remark"
            width="80"
            label="备注">
          </el-table-column>
          <el-table-column
            prop="updateTime"
            width="100"
            label="修改时间">
          </el-table-column>
          <el-table-column
            label="操作">
            <template slot-scope="scope">
              <el-button type="primary" size="mini" @click="showDialog(scope.row)">编辑</el-button>
              <el-button type="danger" size="mini" @click="deleteById(scope.row.carriersid)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div style="display: flex;justify-content: flex-end;padding: 15px">
        <el-pagination
          background
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="pageBillRequest.pageNum"
          layout="total, sizes, prev, pager, next, jumper"
          :page-sizes="[5, 10, 15, 20]"
          :page-size="pageBillRequest.pageSize"
          :total="total">
        </el-pagination>
      </div>
    </el-card>

  </div>
</template>

<script>
  export default {
    name: "Carriers",
    data(){
      return{
        loading:true,
        active: 0,
        multipleSelection: [],
        ids:"",
        total:null,
        dialogFormVisible:false,
        dialogTitle:'',
        searchCarrierss:{
          sendcompany:'',
          receivecompany:'',
        },
        pageBillRequest:{
          state:null,
          pageNum: 1,
          pageSize: 10,
        },
        addBillRequest:{
          remark:"",
          startTime:"",
          endTime:"",
          deliverAddressAdd:{
            companyName:"",
            address:"",
            person:"",
            phone:""
          },
          receiverAddressAdd:{
            companyName:"",
            address:"",
            person:"",
            phone:""
          },
          costAdd:{
            insurance:"",
            transport:"",
            extra:"",
          },
          goodsAdd:{
            name:"",
            amount:"",
            weight:"",
            volume:"",
          },
        },
        roadBillList:[],
      }
    },
    computed:{
      totalcost(){
        this.addBillRequest.totalcost = parseFloat(this.addBillRequest.insurancecost)+parseFloat(this.addBillRequest.transportcost)+parseFloat(this.addBillRequest.othercost);
    return this.addBillRequest.totalcost;
      }
    },
    mounted(){
      this.pageRoadBill();
    },
    methods:{
      next() {
        this.active++;
      },
      prev(){
        this.active--;
      },
      resetSearch(){
        this.searchCarrierss={
          sendcompany:'',
          receivecompany:'',
        }
      },
      initBill(){
        this.addBillRequest={
          remark:"",
          startTime:"",
          endTime:"",
          deliverAddressAdd:{
            companyName:"",
            address:"",
            person:"",
            phone:""
          },
          receiverAddressAdd:{
            companyName:"",
            address:"",
            person:"",
            phone:""
          },
          costAdd:{
            insurance:"",
            transport:"",
            extra:"",
          },
          goodsAdd:{
            name:"",
            amount:"",
            weight:"",
            volume:"",
          }
        }
      },
      addRoadBill(){
        if (this.addBillRequest.carriersid) {
          console.log(this.addBillRequest);
          this.putRequest('/pengms/carriers/put',this.addBillRequest).then(res=>{
            if (res){
              this.dialogFormVisible = false;
              this.initBill();
              this.pageRoadBill();
            }
          });
          return;
        }
        this.postRequest("/pengms/bill/add",this.addBillRequest).then(res=>{
          if (res){
            this.dialogFormVisible = false;
            this.initBill();
            this.pageRoadBill();
          }
        })
      },
      pageRoadBill(){
        this.postRequest("/pengms/bill/page",this.pageBillRequest).then(res => {
          if (res) {
            this.loading=false;
            this.roadBillList = res.data.data;
            this.total = res.data.total;
          }
        })
      },
      doSearch(){
        this.pageRoadBill();
      },
      handleSizeChange(size){
        this.pageBillRequest.pageSize = size;
        this.pageRoadBill();
      },
      handleCurrentChange(page){
        this.pageRequest.pageNum = page;
        this.pageRoadBill();
      },
      handleSelectionChange(val) {
        this.multipleSelection=val;

      },
      deleteById(id){
        let ids = id;
        this.deleteByIds(ids);
        this.pageRoadBill();
      },
      multiDelete(){
        let ids = '';
        this.multipleSelection.forEach(data=>{
          ids  += data.carriersid+',';
        });
        this.deleteByIds(ids);
        this.pageRoadBill();
      },

      deleteByIds(data){
        this.$confirm('此操作将永久删除该文件, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.deleteRequest("/pengms/carriers/delete/"+data).then(res=>{
              if (res){
                this.pageRoadBill();
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
          this.initBill();
          this.dialogTitle = '添加承运单';
          return;
        }
        this.dialogTitle='编辑';
        this.addBillRequest = data;

      },
    }
  }
</script>

<style scoped>

</style>


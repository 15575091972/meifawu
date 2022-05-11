<template>
  <div>

    <el-card>
      <el-row :gutter="19">
        <el-col :span="10">
          <el-input placeholder="请输入微信名" v-model="input_orders" class="input-with-select" clearable
            @clear="getOrderList">
            <el-button slot="append" icon="el-icon-search" @click="getOrderByNickNameMoHu()"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addOrderVisible=true">添加订单</el-button>
        </el-col>
      </el-row>
    </el-card>

    <!-- 渲染数据表格 -->
    <el-table :data="OrderList" border style="width: 100%">
      <el-table-column fixed prop="orderId" label="订单号" width="50">
      </el-table-column>
      <el-table-column prop="nickName" label="微信名" width="80">
      </el-table-column>
      <el-table-column prop="userName" label="用户名" width="80">
      </el-table-column>
      <el-table-column prop="phone" label="电话号码" width="130">
      </el-table-column>
      <el-table-column prop="barberName" label="理发师姓名" width="100">
      </el-table-column>
      <el-table-column prop="appointmentDate" label="预约时间" width="120">
      </el-table-column>
      <el-table-column prop="timeslot" label="预约时段" width="120">
      </el-table-column>
      <el-table-column prop="orderPrice" label="订单价格" width="80">
      </el-table-column>
      <el-table-column prop="orderDetail" label="服务类型" width="80">
      </el-table-column>
      <el-table-column prop="otherDemand" label="其他需求" width="120">
      </el-table-column>
      <el-table-column prop="verifyState" label="是否接受预约" width="80">
      </el-table-column>
      <el-table-column prop="orderState" label="状态" width="80">
      </el-table-column>
      <el-table-column prop="orderEvaluate" label="评价" width="80">
      </el-table-column>

      <el-table-column label="编辑">
        <template slot-scope="scope">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click='ediOrder(scope.row)'></el-button>
        </template>
      </el-table-column>

      <el-table-column label="删除">
        <template slot-scope="scope">
          <el-button type="danger" icon="el-icon-delete" size="mini" @click='removeOrder(scope.row)'></el-button>
        </template>
      </el-table-column>
    </el-table>

    <!-- 新增订单 -->
    <el-dialog title="新增订单" :visible.sync="addOrderVisible" width="50%">
      <!-- 内容区域 -->
      <el-form :model="addOrderForm" :rules="addOrderFormRul" ref="addOrderFormRef" label-width="100px"
        class="demo-ruleForm">
        <el-form-item label="订单号" prop="orderId">
          <el-input v-model="addOrderForm.orderId" disabled></el-input>
        </el-form-item>
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="addOrderForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="电话号码" prop="phone">
          <el-input v-model="addOrderForm.phone"></el-input>
        </el-form-item>
        <el-form-item label="理发师姓名" prop="baberName">
          <el-input v-model="addOrderForm.baberName"></el-input>
        </el-form-item>
        <el-form-item label="预约时间" prop="appointmentDate">
          <el-input v-model="addOrderForm.appointmentDate"></el-input>
        </el-form-item>
        <el-form-item label="预约时段" prop="timeslot">
          <el-input v-model="addOrderForm.timeslot"></el-input>
        </el-form-item>
        <el-form-item label="订单价格" prop="orderPrice">
          <el-input v-model="addOrderForm.orderPrice"></el-input>
        </el-form-item>
        <el-form-item label="服务类型" prop="orderDetail">
          <el-input v-model="addOrderForm.orderDetail"></el-input>
        </el-form-item>
        <el-form-item label="其他需求" prop="otherDemand">
          <el-input v-model="addOrderForm.otherDemand"></el-input>
        </el-form-item>
        <el-form-item label="是否接受预约" prop="verifyState">
          <el-input v-model="addOrderForm.verifyState"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="orderState">
          <el-input v-model="addOrderForm.orderState"></el-input>
        </el-form-item>
        <el-form-item label="评价" prop="orderEvaluate">
          <el-input v-model="addOrderForm.orderEvaluate"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addOrderVisible = false">取 消</el-button>
        <el-button type="primary" @click="addconfirm">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑订单-->
    <el-dialog title="编辑订单" :visible.sync="ediOrderVisible" width="50%" @close="ediOrderDialog">
      <!-- 内容区域 -->
      <el-form :model="ediOrderForm" :rules="ediOrderFormRul" ref="ediOrderFormRef" label-width="100px"
        class="demo-ruleForm">
        <el-form-item label="订单号" prop="orderId">
          <el-input v-model="ediOrderForm.orderId" disabled></el-input>
        </el-form-item>
        <el-form-item label="微信名" prop="nickName"  >
          <el-input v-model="ediOrderForm.nickName" disabled></el-input>
        </el-form-item>
        <el-form-item label="用户名" prop="userName">
          <el-input v-model="ediOrderForm.userName"  disabled></el-input>
        </el-form-item>
        <el-form-item label="电话号码" prop="phone">
          <el-input v-model="ediOrderForm.phone" disabled></el-input>
        </el-form-item>
        <el-form-item label="理发师姓名" prop="baberName" >
          <el-input v-model="ediOrderForm.baberName"disabled></el-input>
        </el-form-item>
        <el-form-item label="预约时间" prop="appointmentDate" >
          <el-input v-model="ediOrderForm.appointmentDate" disabled></el-input>
        </el-form-item>
        <el-form-item label="预约时段" prop="timeslot">
          <el-input v-model="ediOrderForm.timeslot"disabled></el-input>
        </el-form-item>
        <el-form-item label="订单价格" prop="orderPrice">
          <el-input v-model="ediOrderForm.orderPrice" disabled></el-input>
        </el-form-item>
        <el-form-item label="服务类型" prop="orderDetail">
          <el-input v-model="ediOrderForm.orderDetail"disabled></el-input>
        </el-form-item>
        <el-form-item label="其他需求" prop="otherDemand">
          <el-input v-model="ediOrderForm.otherDemand"disabled></el-input>
        </el-form-item>
        <el-form-item label="是否接受预约" prop="verifyState">
          <el-input v-model="ediOrderForm.verifyState"></el-input>
        </el-form-item>
        <el-form-item label="状态" prop="orderState">
          <el-input v-model="ediOrderForm.orderState"></el-input>
        </el-form-item>
        <el-form-item label="评价" prop="orderEvaluate">
          <el-input v-model="ediOrderForm.orderEvaluate"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="ediOrderVisible = false">取 消</el-button>
        <el-button type="primary" @click="ediOrderconfirm">确 定</el-button>
      </span>
    </el-dialog>


    <!-- 分页功能 -->
    <!-- 分页 -->
    <el-pagination @size-change="SizeChange" @current-change="CurrentChange" :current-page="queryInfo.currentPage"
      :page-sizes="[4,6,8,10]" :page-size="queryInfo.pageSize" layout="total, sizes, prev, pager, next, jumper"
      :total="totalsum">
    </el-pagination>



  </div>
</template>

<script>
  export default {
    data() {
      return {
        input_orders: '',

        //数据总数
        totalsum: 0,

        //参数列表
        queryInfo: {
          //每页条数
          pageSize: 6,
          //页码
          currentPage: 1
        },

        OrderList: [],
        //新增Order显示/隐藏
        addOrderVisible: false,

        //新增Order参数
        addOrderForm: {
          orderId: '',
          userName: '',
          phone: '',
          baberName: '',
          appointmentDate: '',
          timeslot: '',
          orderPrice: '',
          orderDetail: '',
          otherDemand: '',
          verifyState: '',
          orderState: '',
          orderEvaluate: '',
        },
        //验证规则
        addOrderFormRul: {

        },

        //编辑Order参数
        ediOrderForm: {
          orderId: '',
          userName: '',
          phone: '',
          baberName: '',
          appointmentDate: '',
          timeslot: '',
          orderPrice: '',
          orderDetail: '',
          otherDemand: '',
          verifyState: '',
          orderState: '',
          orderEvaluate: '',
        },

        //编辑对话框验证规则
        ediOrderFormRul: {

          userName: [{
              required: true,
              message: '请输入名字',
              trigger: 'blur'
            },
            {
              min: 1,
              max: 10,
              message: '',
              trigger: 'blur'
            }
          ],

          phone: [{
              required: true,
              message: '请输入电话号码',
              trigger: 'blur'
            },
            {
              min: 5,
              max: 11,
              message: '请输入合法数据',
              trigger: 'blur'
            }
          ],
        },
        //编辑Order显示/隐藏
        ediOrderVisible: false,
      }



    },

    methods: {
      handleClick(row) {
        console.log(row);
      },

      //每页数据条数改变的时候触发
      SizeChange(newSize) {
        this.queryInfo.pageSize = newSize;
        this.getOrderList();

      },
      //页码发生改变时触发
      CurrentChange(newCurrentPage) {
        this.queryInfo.currentPage = newCurrentPage;
        this.getOrderList();
      },
      //获取信息
      getOrderList() {
        var that = this
        this.$axios.get(`http://localhost:8086/getOrderByPage/${that.queryInfo.pageSize}/${that.queryInfo.currentPage}`)
          .then(response => {
            if (response.data.code == 0) {
              this.OrderList = response.data.data.records;
              this.totalsum = response.data.data.total;
              //console.log("请求订单数据成功");
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      },
      //编辑提交
      ediOrderconfirm() {
        this.$refs.ediOrderFormRef.validate(valid => {
          if (!valid) return alert('输入正确的信息');
          this.$axios.post('http://localhost:8086/updateOrderById', this.ediOrderForm)
            .then(response => {
              console.log(response);
              if (response.data.code == 0) {
                this.$message({
                  type: 'info',
                  message: '修改成功'
                });
                //关闭对话框
                this.ediOrderVisible = !this.ediOrderVisible;
                //刷新
                this.getOrderList();
              } else {
                this.$message({
                  type: 'info',
                  message: '已取消修改'
                });
                this.ediOrderVisible = !this.ediOrderVisible;
              }
            })
        })
      },

      //根据名字模糊查询
      getOrderByNickNameMoHu() {
        var that = this
        this.$axios.get(`http://localhost:8086/getOrderByNickNameMoHu/${that.input_orders}`)
          .then(response => {
            this.OrderList = response.data.data;
            this.totalsum = 1;
            console.log(response.data.data);
            //console.log( this.totalsum);

          })
          .catch(function(error) {
            console.log(error);
          });
      },
      //删除
      removeOrder(row) {
        this.$confirm('此操作将永久删除该订单, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post('http://localhost:8086/deleteOrderById/' + row.orderId)
            .then(res => {
              this.getOrderList();
              this.$message({
                type: 'info',
                message: '删除成功'
              });
            })

        }).catch(() => {
          this.$message({
            type: 'info',
            message: '已取消删除'
          });
        });
      },
      //编辑
      ediOrder(row) {
        // console.log(row.orderId)
        // console.log(88888);

        this.ediOrderForm = {
            orderId: row.orderId,
            userName: row.userName,
            phone: row.phone,
            baberName: row.baberName,
            appointmentDate: row.appointmentDate,
            timeslot: row.timeslot,
            orderPrice: row.orderPrice,
            orderDetail: row.orderDetail,
            otherDemand: row.otherDemand,
            verifyState: row.verifyState,
            orderState: row.orderState,
            orderEvaluate: row.orderEvaluate,
          },

          this.ediOrderVisible = true;
      },


      addconfirm() {

      },




//关闭dialog
      ediOrderDialog() {
        this.$refs.ediOrderFormRef.resetFields();
      },


    },

    created() {
      this.getOrderList();
    }
  }
</script>

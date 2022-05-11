<template>
  <div>
    <!-- 新增 -->
    <el-card>
      <el-row :gutter="19">
        <el-col :span="4">
          <el-button type="primary" @click="addpriceListVisible=true">添加价目</el-button>
        </el-col>
      </el-row>
    </el-card>

    <!-- 添加价目表 -->
    <el-dialog title="添加价目表" :visible.sync="addpriceListVisible" width="50%">
      <!-- 内容区域 -->
      <el-form :model="addPriceListForm" :rules="addPriceListFormRul" ref="addPriceListFormRef" label-width="100px"
        class="demo-ruleForm">
        <el-form-item label="编号" prop="pricelistId">
          <el-input v-model="addPriceListForm.pricelistId"></el-input>
        </el-form-item>
        <el-form-item label="服务类别" prop="pricelistWay">
          <el-input v-model="addPriceListForm.pricelistWay"></el-input>
        </el-form-item>
        <el-form-item label="理发师等级" prop="pricelistLevel">
          <el-input v-model="addPriceListForm.pricelistLevel"></el-input>
        </el-form-item>
        <el-form-item label="价格" prop="pricelistPrice">
          <el-input v-model="addPriceListForm.pricelistPrice"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addpriceListVisible = false">取 消</el-button>
        <el-button type="primary" @click="addPriceList">确 定</el-button>
      </span>
    </el-dialog>


    <el-table :data="tableData" border style="width: 100%">
      <el-table-column fixed prop="pricelistId" label="编号" width="150">
      </el-table-column>
      <el-table-column prop="pricelistWay" label="服务类别" width="150">
      </el-table-column>
      <el-table-column prop="pricelistLevel" label="理发师等级" width="150">
      </el-table-column>
      <el-table-column prop="pricelistPrice" label="价格" width="150">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="编辑" placement="top">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click='edimenu(scope.row)'></el-button>
          </el-tooltip>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="删除" placement="top">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click='removepricelist(scope.row)'></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
    </el-table>



    <!-- 分页功能 -->
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
        tableData: [],
        //添加dialog显示/隐藏
        addpriceListVisible: false,
        //添加priceList参数
        addPriceListForm: {
          pricelistId: '',
          pricelistWay: '',
          pricelistLevel: '',
          pricelistPrice: '',
        },
        //添加price用户规则
        addPriceListFormRul() {

        },
        //数据总数
        totalsum: 0,

        //参数列表
        queryInfo: {
          //每页条数
          pageSize: 6,
          //页码
          currentPage: 1
        }

      }
    },


    methods: {
      handleClick(row) {
        console.log(row)
      },
      //每页数据条数改变的时候触发
      SizeChange(newSize) {
        this.queryInfo.pageSize = newSize;
        this.getPublistList();

      },
      //页码发生改变时触发
      CurrentChange(newCurrentPage) {
        this.queryInfo.currentPage = newCurrentPage;
        this.getPublistList();

      },



      //获取分页数据
      getPublistList() {
        var that = this
        this.$axios.get(
            `http://localhost:8086/getPricelistByPage/${that.queryInfo.pageSize}/${that.queryInfo.currentPage}`)
          .then(response => {
            this.tableData = response.data.data.records;
            this.totalsum = response.data.data.total;

          })
          .catch(function(error) {
            console.log(error);
          });
      },
      //添加picelist
      addPriceList() {
        //校验规则
        this.$refs.addPriceListFormRef.validate(valid => {
          if (!valid) return alert('输入正确的信息');
          this.$axios.post('http://localhost:8086/addPricelist', this.addPriceListForm)
            .then(response => {
              console.log(response);
              if (response.data.code == 0) {
                alert('添加成功');
                //关闭对话框


                this.addpriceListVisible = !this.addpriceListVisible;
                //刷新
                this.getPublistList();
              } else {
                alert('添加失败');
                this.addpriceListVisible = !this.addpriceListVisible;
              }

            })


        })
      },
      //删除数据
      removepricelist(row) {
        this.$confirm('此操作将永久删除该理发师 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post('http://localhost:8086/deletePricelistById/' + row.pricelistId)
            .then(res => {
              this.getPublistList();
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
    },
    created() {
      this.getPublistList();
    }
  }
</script>

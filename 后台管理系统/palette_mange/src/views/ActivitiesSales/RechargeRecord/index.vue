<template>
  <div>

    <el-card>
      <el-row :gutter="19">
        <el-col :span="10">
          <el-input placeholder="请输入昵称查找充值记录" v-model="input_nickname" class="input-with-select" clearable
            @clear="getPublicityList">
            <el-button slot="append" icon="el-icon-search" @click="getbarbersListbyname()"></el-button>
          </el-input>
          </el-col>
      </el-row>
    </el-card>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column fixed prop="investId" label="充值单号" width="150">
          </el-table-column>
          <el-table-column prop="nickName" label="昵称" width="150">
          </el-table-column>
          <el-table-column prop="investDate" label="时间"  width="200" >
          </el-table-column>
          <el-table-column prop="investMoney" label="充值金额"  width="200" >
          </el-table-column>
    </el-table>


<!-- 分页功能 -->
   <el-pagination
          @size-change="SizeChange"
          @current-change="CurrentChange"
          :current-page="queryInfo.currentPage"
          :page-sizes="[4,6,8,10]"
          :page-size="queryInfo.pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="totalsum">
        </el-pagination>


  </div>
</template>

<script>
  export default {

    data() {
      return {
        tableData: [],
        input_nickname:'',
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
        this.getPublicityList();

      },
      //页码发生改变时触发
      CurrentChange(newCurrentPage) {
        this.queryInfo.currentPage = newCurrentPage;
        this.getPublicityList();

      },

      //获取数据
      getPublicityList() {
        var that = this
        this.$axios.get(
            `http://localhost:8086/getInvestrecordByPage/${that.queryInfo.pageSize}/${that.queryInfo.currentPage}`)
          .then(response => {
            this.tableData = response.data.data.records;
            this.totalsum = response.data.data.total;

          })
          .catch(function(error) {
            console.log(error);
          });
      },
      //根据昵称查找充值记录
      getbarbersListbyname(){
        var that = this
        this.$axios.get(`http://localhost:8086/getInvestrecordByNameMoHu/${that.input_nickname}`)
          .then(response => {
            this.tableData = response.data.data;
            this.totalsum = response.data.total;
            //console.log(response);
            //console.log( this.totalsum);

          })
          .catch(function(error) {
            console.log(error);
          });
      },

    },
    created() {
      this.getPublicityList();
    }
  }
</script>

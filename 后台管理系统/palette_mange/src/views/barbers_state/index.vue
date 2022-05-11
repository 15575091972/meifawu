<template>
  <div>

    <el-card>
        <el-col :span="10">
          <el-input placeholder="请输入理发师名字" v-model="inputBarberName" class="input-with-select" clearable @clear="getbarbers_stateList">
            <el-button slot="append" icon="el-icon-search" @click="getmbarbersStateByName()"></el-button>
          </el-input>
        </el-col>
    </el-card>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="stateId" label="编号" width="70" />
      <el-table-column prop="theDate" label="日期" width="100" />
      <el-table-column prop="barberName" label="理发师名字" width="100" />
      <el-table-column prop="t1State" label="8:00-10:00" width="150" />
      <el-table-column prop="t2State" label="10:00-12:00" width="150" />
      <el-table-column prop="t3State" label="14:00-16:00" width="150" />
      <el-table-column prop="t4State" label="16:00-18:00" width="150" />
      <el-table-column prop="t5State" label="20:00-22:00" width="150" />

 <!--     <el-table-column label="操作">
        <template slot-scope="scope">

          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="编辑" placement="top">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click='edibaaber(scope.row)'></el-button>
          </el-tooltip>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="删除" placement="top">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click='removebarber(scope.row)'></el-button>
          </el-tooltip>
        </template>
      </el-table-column> -->
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
        //数据总数
        totalsum:0,
        inputBarberName:'',
        //参数列表
        queryInfo:{
          //每页条数
          pageSize:8,
          //页码
          currentPage:1
        },
      }
    },


    methods: {
      handleClick(row) {
        console.log(row)
      },

      //获取数据
      getbarbers_stateList() {
        var that = this
        this.$axios.get(`http://localhost:8086/getBarberStateByPage/${that.queryInfo.pageSize}/${that.queryInfo.currentPage}`)
          .then(response => {
            this.tableData = response.data.data.records;
            this.totalsum=response.data.data.total;
          })
          .catch(function(error) {
            console.log(error);
          });
      },
      //根据名字查询理发师状态
      getmbarbersStateByName(){
        var that = this
        this.$axios.get(`http://localhost:8086/getBarberStateByNameMoHu/${that.inputBarberName}`)
          .then(response => {
            this.tableData = response.data.data;
            this.totalsum=1;
            console.log(  response.data.data);
          })
          .catch(function(error) {
            console.log(error);
          });


      },
      //每页数据条数改变的时候触发
      SizeChange(newSize){
        this.queryInfo.pageSize=newSize;
        this.getbarbers_stateList();

      },
      //页码发生改变时触发
      CurrentChange(newCurrentPage){
        this.queryInfo.currentPage=newCurrentPage;
        this.getbarbers_stateList();

      },
      // //删除数据
      // removebarber(row) {
      //   this.$confirm('此操作将永久删除该理发师 是否继续?', '提示', {
      //     confirmButtonText: '确定',
      //     cancelButtonText: '取消',
      //     type: 'warning'
      //   }).then(() => {
      //     this.$axios.post('http://localhost:8086/deleteBaberById/' + row.theDate)
      //       .then(res => {
      //         this.getbarbersList();
      //         this.$message({
      //           type: 'info',
      //           message: '删除成功'
      //         });
      //       })

      //   }).catch(() => {
      //     this.$message({
      //       type: 'info',
      //       message: '已取消删除'
      //     });
      //   });
      // },
    },
    created() {
      this.getbarbers_stateList();
    }
  }
</script>

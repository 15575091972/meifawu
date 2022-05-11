<template>
  <div>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="theDate" label="日期" width="150" />
      <el-table-column prop="timeSlot1Count" label="8:00-10:00" width="150" />
      <el-table-column prop="timeSlot2Count" label="10:00-12:00" width="150" />
      <el-table-column prop="timeSlot3Count" label="14:00-16:00" width="150" />
      <el-table-column prop="timeSlot4Count" label="16:00-18:00" width="150" />
      <el-table-column prop="timeSlot5Count" label="20:00-22:00" width="150" />
      <el-table-column prop="maxCount" label="最大预约人数" width="150" />

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




  </div>
</template>

<script>
  export default {

    data() {
      return {
        tableData: [],
      }
    },


    methods: {
      handleClick(row) {
        console.log(row)
      },

      //获取数据
      getbarbersList() {
        var that = this
        this.$axios.get('http://localhost:8086/getAllTimeslotcount')
          .then(response => {
            this.tableData = response.data.data;
            console.log(response.data.data);
          })
          .catch(function(error) {
            console.log(error);
          });
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
      this.getbarbersList();
    }
  }
</script>

<template>
  <el-table :data="tableData" border style="width: 100%">
    <el-table-column fixed prop="adviceId" label="序号" width="120">
    </el-table-column>
    <el-table-column prop="nickName" label="用户名" width="120">
    </el-table-column>
    <el-table-column prop="phone" label="电话号码" width="120">
    </el-table-column>
    <el-table-column prop="adviceDetail" label="建议"  width="500" >
    </el-table-column>

    <el-table-column label="操作">
      <template slot-scope="scope">

        <el-tooltip class="item" effect="dark" content="删除" placement="top">
          <el-button type="danger" icon="el-icon-delete" size="mini" @click='removeAdvice(scope.row)'></el-button>
        </el-tooltip>
      </template>
    </el-table-column>


  </el-table>
</template>

<script>
  export default {
    methods: {
      handleClick(row) {
        console.log(row);
      },
      removeAdvice(row) {
        this.$confirm('此操作将永久删除该条反馈, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post('http://localhost:8086/deleteAdviceById/' + row.serverId)
            .then(res => {
              this.getWaitersList();
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
      }


    },

    data() {
      return {
        tableData: [],
      }
    },
 mounted() {
   var that = this
   this.$axios.get('http://localhost:8086/getAllAdvice')
     .then(response=> {
       this.tableData=response.data.data;
       console.log(response.data.data);
     })
     .catch(function(error) {
       console.log(error);
     });
 }
  }
</script>

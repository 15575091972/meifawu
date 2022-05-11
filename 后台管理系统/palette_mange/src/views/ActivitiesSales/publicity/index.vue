<template>
  <div>
    <el-card>
      <el-row :gutter="19">
        <el-col :span="4">
          <el-button type="primary" @click="addpriceListVisible=true">添加活动</el-button>
        </el-col>
      </el-row>
    </el-card>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column fixed prop="publicityId" label="编号" width="150">
          </el-table-column>
          <el-table-column prop="publicityName" label="活动名称" width="150">
          </el-table-column>
          <el-table-column prop="publicityDate" label="活动时间"  width="150" >
          </el-table-column>
          <el-table-column prop="publicityContent" label="活动内容"  width="400" >
          </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="编辑" placement="top">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click='edimenu(scope.row)'></el-button>
          </el-tooltip>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="删除" placement="top">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click='removePublicity(scope.row)'></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
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
      getPublicityList() {
        var that = this
        this.$axios.get('http://localhost:8086/getAllPublicity')
          .then(response => {
            this.tableData = response.data.data;
            console.log(response.data.data);
          })
          .catch(function(error) {
            console.log(error);
          });
      },
      //删除数据
      removePublicity(row) {
        this.$confirm('此操作将永久删除该活动 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post('http://localhost:8086/deletePublicityById/' + row.publicityId)
            .then(res => {
              this.getPublicityList();
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
      this.getPublicityList();
    }
  }
</script>

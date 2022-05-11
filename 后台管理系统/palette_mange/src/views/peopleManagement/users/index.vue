<template>
<div>
<el-card>
        <el-col :span="10">
          <el-input placeholder="请输入昵称" v-model="input_user" class="input-with-select" clearable @clear="getuserList">
            <el-button slot="append" icon="el-icon-search"  @click="getuserListbyname()"></el-button>
          </el-input>
        </el-col>
    </el-card>

 <!-- <div>
      <img: src="https://thirdwx.qlogo.cn/mmopen/vi_32/BXMZCnSI4ibtuRSxbtSsmdXRsTaGNdmPFPxVXwrxGokr595icVrIsHuntbNz1S1Gy6qbzdmCpMFKtpwgpuvWpjVQ/132"></el-avatar>
    </div> -->
  <el-table :data="tableData" border style="width: 100%">
    <el-table-column fixed prop="userId" label="序号" width="80">
    </el-table-column>
    <el-table-column prop="nickName" label="昵称" width="120">
    </el-table-column>
	
	<el-table-column prop="image" label="头像"width="120" >
		<template slot-scope="scope">
			<img :src="scope.row.image" min-width="80" height="80"/>
		</template>
	</el-table-column>
	
	<!-- <el-avatar src=headerUrl"" ></el-avatar> -->
    <el-table-column prop="userCategory" label="会员状态" width="80">
    </el-table-column>
    <el-table-column prop="userBalance" label="用户余额" width="120">
    </el-table-column>
    <el-table-column prop="userScore" label="积分" width="100">
    </el-table-column>
    <el-table-column label="操作">
      <template slot-scope="scope">
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="编辑" placement="top">
          <el-button type="primary" icon="el-icon-edit" size="mini" @click='ediusers(scope.row)'></el-button>
        </el-tooltip>
        </el-tooltip>
        <el-tooltip class="item" effect="dark" content="删除" placement="top">
          <el-button type="danger" icon="el-icon-delete" size="mini" @click='removeusers(scope.row)'></el-button>
        </el-tooltip>
      </template>
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
        input_user:'',
        //数据总数
        totalsum:0,

        //参数列表
        queryInfo:{
          //每页条数
          pageSize:6,
          //页码
          currentPage:1
        }
      }
    },
    methods: {
      handleClick(row) {
        console.log(row);
      },
      //每页数据条数改变的时候触发
      SizeChange(newSize){
        this.queryInfo.pageSize=newSize;
        this.getuserList();

      },
      //页码发生改变时触发
      CurrentChange(newCurrentPage){
        this.queryInfo.currentPage=newCurrentPage;
        this.getuserList();

      },

      //获取分页信息
      getuserList() {
        var that = this
        this.$axios.get(`http://localhost:8086/getUserByPage/${that.queryInfo.pageSize}/${that.queryInfo.currentPage}`)
          .then(response => {
            this.tableData = response.data.data.records;
            this.totalsum=response.data.data.total;

          })
          .catch(function(error) {
            console.log(error);
          });
      },

      //根据用户名获取信息
      getuserListbyname(){
        var that = this
        this.$axios.get(`http://localhost:8086/findUserByNickName/${that.input_user}`)
          .then(response => {
            this.tableData = response.data.data;
            this.totalsum=1;
             console.log( response);
            //console.log( this.totalsum);

          })
          .catch(function(error) {
            console.log(error);
          });
      },

      //删除
      removeusers(row) {
        this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post('http://localhost:8086/deleteUserById/' + row.userId)
            .then(res => {
              this.getuserList();
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
      ediusers() {

      },

    },


    created() {
      this.getuserList();
    }
  }
</script>

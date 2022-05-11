<template>
  <div>


    <el-card>
      <el-row :gutter="19">
        <el-col :span="10">
          <el-input placeholder="请输入姓名" v-model="input_menuName" class="input-with-select" clearable @clear="getMenuList">
            <el-button slot="append" icon="el-icon-search" @click="getmenuByName()"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addmenusVisible=true">点餐</el-button>
        </el-col>
      </el-row>
    </el-card>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="menuId" label="单号" width="50" />
      <el-table-column prop="userSex" label="性别" width="120" />
      <el-table-column prop="userName" label="姓名" width="120" />
      <el-table-column prop="menuDetail" label="需求" width="400" />
      <el-table-column prop="menuPrice" label="价格" width="120" />
      <el-table-column label="删除
      ">
        <template slot-scope="scope">
          <el-tooltip class="item" effect="dark" content="删除" placement="top">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click='removemenu(scope.row)'></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
    </el-table>

    <!-- 点餐 -->
    <el-dialog title="点餐" :visible.sync="addmenusVisible" width="50%" @close="addmenusDialog">
      <!-- 内容区域 -->
      <el-form :model="addmenusForm" :rules="addmenusFormRul" ref="addmenusFormRef" label-width="100px"
        class="demo-ruleForm">
        <el-form-item label="单号" prop="menuId">
          <el-input v-model="addmenusForm.menuId"></el-input>
        </el-form-item>
        <el-form-item label="性别" prop="userSex">
          <el-input v-model="addmenusForm.userSex"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="userName">
          <el-input v-model="addmenusForm.userName"></el-input>
        </el-form-item>
        <el-form-item label="需求" prop="menuDetail">
          <el-input v-model="addmenusForm.menuDetail"></el-input>
        </el-form-item>
        <el-form-item label="价格" prop="menuPrice">
          <el-input v-model="addmenusForm.menuPrice"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addmenusVisible = false">取 消</el-button>
        <el-button type="primary" @click="addmenus">确 定</el-button>
      </span>
    </el-dialog>





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
        addmenusVisible:false,
        addmenusFormRul(){

        },
        addmenusForm:{
          menuId: '',
          userSex: '',
          userName: '',
          menuDetail: '',
          menuPrice: '',
        },

        //数据总数
        totalsum:0,

        //参数列表
        queryInfo:{
          //每页条数
          pageSize:8,
          //页码
          currentPage:1
        },


        tableData: [],
        //搜索框
        input_menuName:'',
      }
    },


    methods: {
      handleClick(row) {
        console.log(row)
      },
      //点餐
      addmenus(){
        //校验规则
        this.$refs.addmenusFormRef.validate(valid => {
          if (!valid) return alert('输入正确的信息');
          this.$axios.post('http://localhost:8086/addMenu', this.addmenusForm)
            .then(response => {
              console.log(response);
              if (response.data.code == 0) {
                alert('点餐成功');
                //关闭对话框
                this.addmenusVisible = !this.addmenusVisible;
                //刷新
                this.getMenuList();
              } else {
                alert('点餐失败');
                this.addmenusVisible = !this.addmenusVisible;
              }

            })


        })
      },

      //每页数据条数改变的时候触发
      SizeChange(newSize){
        this.queryInfo.pageSize=newSize;
        this.getMenuList();

      },
      //页码发生改变时触发
      CurrentChange(newCurrentPage){
        this.queryInfo.currentPage=newCurrentPage;
        this.getMenuList();

      },

      //关闭点餐对话框
      addmenusDialog() {
        //重置表单
        this.$refs.addmenusFormRef.resetFields();
      },

      //获取数据
      getMenuList() {
        var that = this
        this.$axios.get(`http://localhost:8086/getMenuByPage/${that.queryInfo.pageSize}/${that.queryInfo.currentPage}`)
          .then(response => {
            if (response.data.code == 0) {
              this.tableData = response.data.data.records;
               this.totalsum=response.data.data.total;
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      },
      //根据名字模糊查询
      getmenuByName(){
        var that = this
        this.$axios.get(`http://localhost:8086/getMenuByNameMoHu/${that.input_menuName}`)
          .then(response => {
            this.tableData = response.data.data;
            this.totalsum=1;
            //console.log(  response.data.data);
          })
          .catch(function(error) {
            console.log(error);
          });

      },
      //删除数据
      removemenu(row) {
        this.$confirm('此操作将永久删除该理发师 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post('http://localhost:8086/deleteMenuById/' + row.menuId)
            .then(res => {
              this.getMenuList();
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
      this.getMenuList();
    }
  }
</script>

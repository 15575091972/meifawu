<template>
  <div>
    <!-- 顶部搜索框 -->
    <el-card>
      <el-row :gutter="19">
        <el-col :span="10">
          <el-input placeholder="请输入名字" v-model="input_barbers" class="input-with-select" clearable
            @clear="getbarbersList">
            <el-button slot="append" icon="el-icon-search" @click="getbarbersListbyname()"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addbarbersVisible=true">添加理发师</el-button>
        </el-col>
      </el-row>
    </el-card>

    <el-table :data="tableData" border style="width: 100%">
      <el-table-column prop="barberId" label="序号" width="50" />
      <el-table-column prop="barberName" label="姓名" width="120" />
      <el-table-column prop="barberAge" label="年龄" width="60" />
      <el-table-column prop="barberPhone" label="电话" width="150" />
      <el-table-column prop="barberIntroduction" label="简介" width="300" />
      <el-table-column prop="barberGoodrate" label="好评率" width="80" />
      <el-table-column prop="barberSalary" label="薪资" width="80" />
      <el-table-column prop="barberLevel" label="等级" width="80" />
      <el-table-column label="操作">
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
      </el-table-column>
    </el-table>

    <!-- 添加barbers -->
    <el-dialog title="添加理发师" :visible.sync="addbarbersVisible" width="50%" @close="addbarbersDialog">
      <!-- 内容区域 -->
      <el-form :model="addbarbersForm" :rules="addbarbersFormRul" ref="addbarbersFormRef" label-width="100px"
        class="demo-ruleForm">
        <el-form-item label="序号" prop="barberId">
          <el-input v-model="addbarbersForm.barberId"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="barberName">
          <el-input v-model="addbarbersForm.barberName"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="barberAge">
          <el-input v-model="addbarbersForm.barberAge"></el-input>
        </el-form-item>
        <el-form-item label="电话号码" prop="barberPhone">
          <el-input v-model="addbarbersForm.barberPhone"></el-input>
        </el-form-item>
        <el-form-item label="简介" prop="barberIntroduction">
          <el-input v-model="addbarbersForm.barberIntroduction"></el-input>
        </el-form-item>
        <el-form-item label="好评率" prop="barberGoodrate">
          <el-input v-model="addbarbersForm.barberGoodrate"></el-input>
        </el-form-item>
        <el-form-item label="薪资" prop="barberSalary">
          <el-input v-model="addbarbersForm.barberSalary"></el-input>
        </el-form-item>
        <el-form-item label="等级" prop="barberLevel">
          <el-input v-model="addbarbersForm.barberLevel"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addbarbersVisible = false">取 消</el-button>
        <el-button type="primary" @click="addbarbers">确 定</el-button>

      </span>
    </el-dialog>

    <!-- 编辑barber -->
    <el-dialog title="编辑理发师" :visible.sync="edibarbersVisible" width="50%" @close="edibarbersDialog">
      <!-- 内容区域 -->
      <el-form :model="edibarbersForm" :rules="edibarbersFormRul" ref="edibarbersFormRef" label-width="100px"
        class="demo-ruleForm">
        <el-form-item label="序号" prop="barberId" >
          <el-input v-model="edibarbersForm.barberId"disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="barberName">
          <el-input v-model="edibarbersForm.barberName"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="barberAge">
          <el-input v-model="edibarbersForm.barberAge"></el-input>
        </el-form-item>
        <el-form-item label="电话号码" prop="barberPhone">
          <el-input v-model="edibarbersForm.barberPhone"></el-input>
        </el-form-item>
        <el-form-item label="简介" prop="barberIntroduction">
          <el-input v-model="edibarbersForm.barberIntroduction"></el-input>
        </el-form-item>
        <el-form-item label="好评率" prop="barberGoodrate">
          <el-input v-model="edibarbersForm.barberGoodrate"></el-input>
        </el-form-item>
        <el-form-item label="薪资" prop="barberSalary">
          <el-input v-model="edibarbersForm.barberSalary"></el-input>
        </el-form-item>
        <el-form-item label="等级" prop="barberLevel">
          <el-input v-model="edibarbersForm.barberLevel"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="edibarbersVisible = false">取 消</el-button>
        <el-button type="primary"  @click="edibarbersconfirm">确 定</el-button>

      </span>
    </el-dialog>

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

        input_barbers: '',

        //数据总数
        totalsum: 0,
        addbarbersFormRul() {
          barberId: [{
              required: true,
              message: '请输入编号',
              trigger: 'blur'
            },
            {
              min: 1,
              max: 10,
              message: '',
              trigger: 'blur'
            }
          ]

        },

        //添加barbers显示/隐藏
        addbarbersVisible: false,
        //添加barbers参数
        addbarbersForm: {
          barberId: '',
          barberName: '',
          barberAge: '',
          barberPhone: '',
          barberIntroduction: '',
          barberGoodrate: '',
          barberSalary: '',
          barberLevel: '',
        },
        //编辑规则
        edibarbersFormRul() {

        },

        //编辑barbers显示/隐藏
        edibarbersVisible: false,
        //编辑barbers参数
        edibarbersForm: {
          barberId: '',
          barberName: '',
          barberAge: '',
          barberPhone: '',
          barberIntroduction: '',
          barberGoodrate: '',
          barberSalary: '',
          barberLevel: '',
        },

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

      edibaaber(row){
        this.edibarbersForm = {
            barberId: row.barberId,
            barberName: row.barberName,
            barberAge: row.barberAge,
            barberPhone: row.barberPhone,
            barberIntroduction: row.barberIntroduction,
            barberGoodrate: row.barberGoodrate,
            barberSalary: row.barberSalary,
            barberLevel: row.barberLevel,
          },
          this.edibarbersVisible = true;
      },

      edibarbersconfirm(){
        this.$refs.edibarbersFormRef.validate(valid => {
          if (!valid) return alert('输入正确的信息');
          this.$axios.post('http://localhost:8086/updateBaber', this.edibarbersForm)
            .then(response => {
              console.log(response);
              if (response.data.code == 0) {
                this.$message({
                  type: 'info',
                  message: '修改成功'
                });
                //关闭对话框
                this.edibarbersVisible = !this.edibarbersVisible;
                //刷新
                this.getbarbersList();
              } else {
                this.$message({
                  type: 'info',
                  message: '已取消修改'
                });
                this.edibarbersVisible = !this.edibarbersVisible;
              }
            })
        })
      },
      //每页数据条数改变的时候触发
      SizeChange(newSize) {
        this.queryInfo.pageSize = newSize;
        this.getbarbersList();

      },
      //页码发生改变时触发
      CurrentChange(newCurrentPage) {
        this.queryInfo.currentPage = newCurrentPage;
        this.getbarbersList();

      },
      //关闭对话框
      addbarbersDialog() {
        //重置表单
        this.$refs.addbarbersFormRef.resetFields();
      },
      edibarbersDialog() {
        //重置表单
        this.$refs.edibarbersFormRef.resetFields();
      },

      //获取分页数据
      getbarbersList() {
        var that = this
        this.$axios.get(
            `http://localhost:8086/getBarberByPage/${that.queryInfo.pageSize}/${that.queryInfo.currentPage}`)
          .then(response => {
            this.tableData = response.data.data.records;
            this.totalsum = response.data.data.total;
            console.log(response);
            console.log(this.totalsum);

          })
          .catch(function(error) {
            console.log(error);
          });
      },
      //根据名字查询
      getbarbersListbyname() {
        var that = this
        this.$axios.get(`http://localhost:8086/getBarberByNameMoHu/${that.input_barbers}`)
          .then(response => {
            this.tableData = response.data.data;
            this.totalsum = 1;
            console.log(response);
            //console.log( this.totalsum);

          })
          .catch(function(error) {
            console.log(error);
          });
      },


      //添加barbers
      addbarbers() {
        //校验规则
        this.$refs.addbarbersFormRef.validate(valid => {
          if (!valid) return alert('输入正确的信息');
          this.$axios.post('http://localhost:8086/addBaber', this.addbarbersForm)
            .then(response => {
              console.log(response);
              if (response.data.code == 0) {
                alert('添加成功');
                //关闭对话框
                this.addbarbersVisible = !this.addbarbersVisible;
                //刷新
                this.getbarbersList();
              } else {
                alert('添加失败');
                this.addbarbersVisible = !this.addbarbersVisible;
              }

            })


        })
      },

      //删除数据
      removebarber(row) {
        this.$confirm('此操作将永久删除该理发师 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post('http://localhost:8086/deleteBaberById/' + row.barberId)
            .then(res => {
              this.getbarbersList();
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
      this.getbarbersList();
    }
  }
</script>

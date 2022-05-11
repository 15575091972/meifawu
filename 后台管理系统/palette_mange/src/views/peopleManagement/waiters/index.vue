<template>
  <div>

    <el-card>
      <el-row :gutter="19">
        <el-col :span="10">
          <el-input placeholder="请输入姓名" v-model="input_waiters" class="input-with-select" clearable @clear="searchval">
            <el-button slot="append" icon="el-icon-search" @click="getwaitersbyname()"></el-button>
          </el-input>
        </el-col>
        <el-col :span="4">
          <el-button type="primary" @click="addWaitersVisible=true">添加服务员</el-button>
        </el-col>
      </el-row>
    </el-card>

    <el-table :data="waiterslist" border style="width: 100%">
      <el-table-column fixed prop="serverId" label="序号" width="180">
      </el-table-column>
      <el-table-column prop="serverName" label="姓名" width="180">
      </el-table-column>
      <el-table-column prop="serverAge" label="年龄" width="180">
      </el-table-column>
      <el-table-column prop="serverPhone" label="电话号码" width="200">
      </el-table-column>
      <el-table-column prop="serverSalary" label="薪资" width="180">
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">

          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="编辑" placement="top">
            <el-button type="primary" icon="el-icon-edit" size="mini" @click='ediwaitwes(scope.row)'></el-button>
          </el-tooltip>
          </el-tooltip>
          <el-tooltip class="item" effect="dark" content="删除" placement="top">
            <el-button type="danger" icon="el-icon-delete" size="mini" @click='removewaitwes(scope.row)'></el-button>
          </el-tooltip>
        </template>
      </el-table-column>
    </el-table>

    <!-- 添加watiers -->
    <el-dialog title="添加服务员" :visible.sync="addWaitersVisible" width="50%" @close="addWaitersDialog">
      <!-- 内容区域 -->
      <el-form :model="addWaitersForm" :rules="addWaitersFormRul" ref="addWaitersFormRef" label-width="100px"
        class="demo-ruleForm">
        <el-form-item label="序号" prop="serverId">
          <el-input v-model="addWaitersForm.serverId"></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="serverName">
          <el-input v-model="addWaitersForm.serverName"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="serverAge">
          <el-input v-model="addWaitersForm.serverAge"></el-input>
        </el-form-item>
        <el-form-item label="电话号码" prop="serverPhone">
          <el-input v-model="addWaitersForm.serverPhone"></el-input>
        </el-form-item>
        <el-form-item label="薪资" prop="serverSalary">
          <el-input v-model="addWaitersForm.serverSalary"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="addWaitersVisible = false">取 消</el-button>
        <el-button type="primary" @click="addWaiters">确 定</el-button>
      </span>
    </el-dialog>

    <!-- 编辑数据 -->
    <el-dialog title="编辑服务员" :visible.sync="ediWaitersVisible" width="50%" @close="ediWaitersDialog">
      <!-- 内容区域 -->
      <el-form :model="ediWaitersForm" :rules="ediWaitersFormRul" ref="ediWaitersFormRef" label-width="100px"
        class="demo-ruleForm">
        <el-form-item label="序号" prop="serverId">
          <el-input v-model="ediWaitersForm.serverId" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名" prop="serverName">
          <el-input v-model="ediWaitersForm.serverName"></el-input>
        </el-form-item>
        <el-form-item label="年龄" prop="serverAge">
          <el-input v-model="ediWaitersForm.serverAge"></el-input>
        </el-form-item>
        <el-form-item label="电话号码" prop="serverPhone">
          <el-input v-model="ediWaitersForm.serverPhone"></el-input>
        </el-form-item>
        <el-form-item label="薪资" prop="serverSalary">
          <el-input v-model="ediWaitersForm.serverSalary"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button @click="ediWaitersVisible = false">取 消</el-button>
        <el-button type="primary" @click="ediconfirm">确 定</el-button>
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


        //数据总数
        totalsum: 0,

        //参数列表
        queryInfo: {
          //每页条数
          pageSize: 4,
          //页码
          currentPage: 1
        },

        //列表数据
        waiterslist: [],
        input_waiters: '',
        //添加waiters显示/隐藏
        addWaitersVisible: false,
        //添加waiters参数
        addWaitersForm: {
          serverId: '',
          serverName: '',
          serverAge: '',
          serverPhone: '',
          serverSalary: '',
        },
        //对话框验证规则
        addWaitersFormRul: {
          serverId: [{
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
          ],
          serverName: [{
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
          serverAge: [{
              required: true,
              message: '请输入年龄',
              trigger: 'blur'
            },
            {
              min: 1,
              max: 10,
              message: '',
              trigger: 'blur'
            }
          ],
          serverPhone: [{
              required: true,
              message: '请输入电话号码',
              trigger: 'blur'
            },
            {
              min: 11,
              max: 11,
              message: '请输入合法数据',
              trigger: 'blur'
            }
          ],
          serverSalary: [{
              required: true,
              message: '请输入薪资',
              trigger: 'blur'
            },
            {
              min: 1,
              max: 10,
              message: '',
              trigger: 'blur'
            }
          ],
        },
        //编辑waiters显示/隐藏
        ediWaitersVisible: false,
        //编辑waiters参数
        ediWaitersForm: {
          serverId: '',
          serverName: '',
          serverAge: '',
          serverPhone: '',
          serverSalary: '',
        },
        //编辑对话框验证规则
        ediWaitersFormRul: {

          serverName: [{
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
          serverAge: [{
              required: true,
              message: '请输入年龄',
              trigger: 'blur'
            },
            {
              min: 1,
              max: 10,
              message: '',
              trigger: 'blur'
            }
          ],
          serverPhone: [{
              required: true,
              message: '请输入电话号码',
              trigger: 'blur'
            },
            {
              min: 11,
              max: 11,
              message: '请输入合法数据',
              trigger: 'blur'
            }
          ],

        },
      }
    },

    methods: {
      handleClick(row) {
        console.log(row);
      },

      //每页数据条数改变的时候触发
      SizeChange(newSize) {
        this.queryInfo.pageSize = newSize;
        this.getWaitersList();

      },
      //页码发生改变时触发
      CurrentChange(newCurrentPage) {
        this.queryInfo.currentPage = newCurrentPage;
        this.getWaitersList();

      },
      searchval() {},

      //获取服务员列表
      getWaitersList() {
        var that = this
        this.$axios.get(
            `http://localhost:8086/getServerByPage/${that.queryInfo.pageSize}/${that.queryInfo.currentPage}`)
          .then(response => {
            if (response.data.code == 0) {
              this.waiterslist = response.data.data.records;
              this.totalsum = response.data.data.total;
              console.log("请求用户列表成功");
              console.log(this.totalsum);
            }
          })
          .catch(function(error) {
            console.log(error);
          });
      },
      //根据名字模糊查询
      getwaitersbyname() {
        var that = this
        this.$axios.get(`http://localhost:8086/getServerByNameMoHu/${that.input_waiters}`)
          .then(response => {
            this.waiterslist = response.data.data;
            this.totalsum = response.data.total;
            console.log(response.data.total);
            //console.log( this.totalsum);

          })
          .catch(function(error) {
            console.log(error);
          });
      },

      //添加witers
      addWaiters() {
        //校验规则
        this.$refs.addWaitersFormRef.validate(valid => {
          if (!valid) return alert('输入正确的信息');
          this.$axios.post('http://localhost:8086/addServer', this.addWaitersForm)
            .then(response => {
              console.log(response);
              if (response.data.code == 0) {
                alert('添加成功');
                //关闭对话框
                this.addWaitersVisible = !this.addWaitersVisible;
                //刷新
                this.getWaitersList();
              } else {
                alert('添加失败');
                this.addWaitersVisible = !this.addWaitersVisible;
              }

            })


        })
      },
      //编辑信息
      ediwaitwes(row) {
        //console.log(row)
        //console.log(88888)
        this.ediWaitersForm = {
            serverId: row.serverId,
            serverName: row.serverName,
            serverAge: row.serverAge,
            serverPhone: row.serverPhone,
            serverSalary: row.serverSalary,
          },
          this.ediWaitersVisible = true;


      },
      ediconfirm() {
        this.$refs.ediWaitersFormRef.validate(valid => {
          if (!valid) return alert('输入正确的信息');
          this.$axios.post('http://localhost:8086/updateServer', this.ediWaitersForm)
            .then(response => {
              console.log(response);
              if (response.data.code == 0) {
                this.$message({
                  type: 'info',
                  message: '修改成功'
                });
                //关闭对话框
                this.ediWaitersVisible = !this.ediWaitersVisible;
                //刷新
                this.getWaitersList();
              } else {
                this.$message({
                  type: 'info',
                  message: '已取消修改'
                });
                this.ediWaitersVisible = !this.ediWaitersVisible;
              }
            })
        })
      },


      //删除
      removewaitwes(row) {
        this.$confirm('此操作将永久删除该用户, 是否继续?', '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          this.$axios.post('http://localhost:8086/deleteServerById/' + row.serverId)
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
      },

      //关闭对话框
      addWaitersDialog() {
        //重置表单
        this.$refs.addWaitersFormRef.resetFields();
      },

      ediWaitersDialog() {
        this.$refs.ediWaitersFormRef.resetFields();
      }
    },

    created() {
      this.getWaitersList();
    }
  }
</script>

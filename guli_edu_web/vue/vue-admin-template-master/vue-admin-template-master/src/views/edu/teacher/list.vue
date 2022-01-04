<template>
  <div class="app-container">
    <!--查询表单-->
    <template>
      <el-form
        :inline="true"
        :model="teacherQuery"
        class="demo-form-inline"
        style="text-align: center"
      >
        <el-form-item label="讲师名">
          <el-input v-model="teacherQuery.name" placeholder="讲师名"></el-input>
        </el-form-item>
        <el-form-item label="讲师头衔">
          <el-select v-model="teacherQuery.level" placeholder="讲师头衔">
            <el-option label="高级讲师" value="1"></el-option>
            <el-option label="首席讲师" value="2"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="添加时间">
          <el-date-picker
            v-model="teacherQuery.begin"
            type="datetime"
            placeholder="选择开始时间"
            value-format="yyyy-MM-dd HH:mm:ss"
            default-time="00:00:00"
          />
        </el-form-item>
        <el-form-item size="normal">
          <el-date-picker
            v-model="teacherQuery.end"
            type="datetime"
            placeholder="选择截至时间"
            value-format="yyyy-MM-dd HH:mm:ss"
            default-time="00:00:00"
          />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="getTeacherListPage()"
            >查询</el-button
          >
          <el-button type="default" @click="resetData()">清空</el-button>
        </el-form-item>
      </el-form>
    </template>
    <!-- 表格 -->
    <el-table
      :data="list"
      v-loading="listLoading"
      element-loading-text="数据加载中"
      border
      fit
      highlight-current-row
    >
      <el-table-column label="序号" width="70" align="center">
        <template slot-scope="scope">
          {{ (page - 1) * size + scope.$index + 1 }}
        </template>
      </el-table-column>
      <el-table-column width="80" prop="name" label="名称" />
      <el-table-column label="头衔" width="80">
        <template slot-scope="scope">
          {{ scope.row.level === 1 ? "高级讲师" : "首席讲师" }}
        </template>
      </el-table-column>
      <el-table-column prop="intro" label="资历" />
      <el-table-column prop="gmtCreate" label="添加时间" width="160" />
      <el-table-column prop="sort" label="排序" width="60" />
      <el-table-column label="操作" width="200" align="center">
        <template slot-scope="scope">
          <router-link :to="'/edu/teacher/edit/' + scope.row.id">
            <el-button type="primary" size="mini" icon="el-icon-edit"
              >修改</el-button
            >
          </router-link>
          <el-button
            type="danger"
            size="mini"
            icon="el-icon-delete"
            @click="removeTeacherById(scope.row.id)"
            >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
    <!-- 分页功能 -->
    <template>
      <!-- total记录总数，page-size每页记录数,pager-count 设置最大页码按钮在第几个位置,current-change 当前页改变时触发 -->
      <el-pagination
        :page-size="size"
        :pager-count="5"
        :current-page="page"
        :total="total"
        layout="total, prev, pager, next, jumper"
        style="padding: 30px 0; text-align: center"
        @current-change="getTeacherListPage"
      >
      </el-pagination>
    </template>
  </div>
</template>
<script>
//引入teacher.js
import teacher from "@/api/edu/teacher";

export default {
  //定义变量和初始值
  data() {
    return {
      listLoading: true, // 是否显示loading信息
      list: null, //查询之后接口返回集合
      page: 1, //当前页
      size: 10, //每页记录数
      total: 0, //总记录数
      teacherQuery: {}, //条件封装对象
    };
  },
  //页面渲染之前执行，一般调用methods定义的方法
  created() {
    this.getTeacherListPage();
  },
  // 创建具体的方法，调用teacher.js定义的方法
  methods: {
    //讲师列表
    getTeacherListPage(page = 1) {
      //不传入page默认为1
      this.page = page; //当page发现改变时，传入当前page
      this.listLoading = true;
      teacher
        .getTeacherListPage(this.page, this.size, this.teacherQuery)
        .then((result) => {
          //请求成功
          this.list = result.data.rows;
          this.total = result.data.total;
          console.log(this.list);
          this.listLoading = false;
        })
        .catch((err) => {
          //请求失败
          console.log(err);
        });
    },
    //清空查询条件
    resetData() {
      this.teacherQuery = {};
      this.getTeacherListPage();
    },
    //删除讲师
    removeTeacherById(id) {
      //1.删除前提示是否继续删除
      this.$confirm("此操作将永久删除讲师记录,是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
      }).then(() => { //2.选择继续删除
        //3.调用删除记录的接口
        teacher
          .removeTeacherById(id)
          .then((result) => {
            //4.提示删除成功
            this.$message({
              type: "success",
              message: "删除成功!",
            });
          })
        //5.刷新页面
        this.getTeacherListPage();
      });
    },
  },
};
</script>
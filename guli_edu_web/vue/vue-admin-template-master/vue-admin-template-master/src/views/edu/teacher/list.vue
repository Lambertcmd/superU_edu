<template>
  <div class="app-container">
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
            @click="removeDataById(scope.row.id)"
            >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>
<script>
//引入teacher.jswe
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
    //调用讲师列表
    getTeacherListPage() {
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
  },
};
</script>
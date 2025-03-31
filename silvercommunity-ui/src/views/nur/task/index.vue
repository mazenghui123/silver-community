<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="护理员id" prop="nursingId">
        <el-input v-model="queryParams.nursingId" placeholder="请输入护理员id" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="项目id" prop="projectId">
        <el-input v-model="queryParams.projectId" placeholder="请输入项目id" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="老人id" prop="elderId">
        <el-input v-model="queryParams.elderId" placeholder="请输入老人id" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="床位编号" prop="bedNumber">
        <el-input v-model="queryParams.bedNumber" placeholder="请输入床位编号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="预计服务时间">
        <el-date-picker v-model="daterangeEstimatedServerTime" style="width: 240px" value-format="yyyy-MM-dd"
          type="daterange" range-separator="-" start-placeholder="开始日期" end-placeholder="结束日期"></el-date-picker>
      </el-form-item>
      <el-form-item label="状态" prop="status">
        <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
          <el-option v-for="dict in dict.type.nursing_task_status" :key="dict.value" :label="dict.label"
            :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="订单号" prop="relNo">
        <el-input v-model="queryParams.relNo" placeholder="请输入订单号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['nur:task:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['nur:task:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['nur:task:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['nur:task:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="taskList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="老人id" align="center" prop="elderId" />
      <el-table-column label="床位编号" align="center" prop="bedNumber" />
      <el-table-column label="任务类型" align="center" prop="taskType" />
      <el-table-column label="预计服务时间" align="center" prop="estimatedServerTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.estimatedServerTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="护理员id" align="center" prop="nursingId" />
      <el-table-column label="实际服务时间" align="center" prop="realServerTime" width="180">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.realServerTime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="状态" align="center" prop="status">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.nursing_task_status" :value="scope.row.status" />
        </template>
      </el-table-column>
      <el-table-column label="订单号" align="center" prop="relNo" />
      <el-table-column label="执行图片" align="center" prop="taskImage" width="100">
        <template slot-scope="scope">
          <image-preview :src="scope.row.taskImage" :width="50" :height="50" />
        </template>
      </el-table-column>
      <el-table-column label="备注" align="center" prop="remark" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['nur:task:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['nur:task:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改护理任务对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="护理员id" prop="nursingId">
          <el-input v-model="form.nursingId" placeholder="请输入护理员id" />
        </el-form-item>
        <el-form-item label="项目id" prop="projectId">
          <el-input v-model="form.projectId" placeholder="请输入项目id" />
        </el-form-item>
        <el-form-item label="老人id" prop="elderId">
          <el-input v-model="form.elderId" placeholder="请输入老人id" />
        </el-form-item>
        <el-form-item label="床位编号" prop="bedNumber">
          <el-input v-model="form.bedNumber" placeholder="请输入床位编号" />
        </el-form-item>
        <el-form-item label="预计服务时间" prop="estimatedServerTime">
          <el-date-picker clearable v-model="form.estimatedServerTime" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择预计服务时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="实际服务时间" prop="realServerTime">
          <el-date-picker clearable v-model="form.realServerTime" type="date" value-format="yyyy-MM-dd"
            placeholder="请选择实际服务时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="执行记录" prop="mark">
          <el-input v-model="form.mark" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="取消原因" prop="cancelReason">
          <el-input v-model="form.cancelReason" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="状态  1待执行 2已执行 3已关闭 " prop="status">
          <el-radio-group v-model="form.status">
            <el-radio v-for="dict in dict.type.nursing_task_status" :key="dict.value"
              :label="parseInt(dict.value)">{{ dict.label }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="订单号" prop="relNo">
          <el-input v-model="form.relNo" placeholder="请输入订单号" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listTask, getTask, delTask, addTask, updateTask } from "@/api/nur/task";

export default {
  name: "Task",
  dicts: ['nursing_task_status'],
  data() {
    return {
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 护理任务表格数据
      taskList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 订单号时间范围
      daterangeEstimatedServerTime: [],
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        nursingId: null,
        projectId: null,
        elderId: null,
        bedNumber: null,
        taskType: null,
        estimatedServerTime: null,
        status: null,
        relNo: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询护理任务列表 */
    getList() {
      this.loading = true;
      this.queryParams.params = {};
      if (null != this.daterangeEstimatedServerTime && '' != this.daterangeEstimatedServerTime) {
        this.queryParams.params["beginEstimatedServerTime"] = this.daterangeEstimatedServerTime[0];
        this.queryParams.params["endEstimatedServerTime"] = this.daterangeEstimatedServerTime[1];
      }
      listTask(this.queryParams).then(response => {
        this.taskList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        id: null,
        nursingId: null,
        projectId: null,
        elderId: null,
        bedNumber: null,
        taskType: null,
        estimatedServerTime: null,
        realServerTime: null,
        mark: null,
        cancelReason: null,
        status: null,
        relNo: null,
        taskImage: null,
        createTime: null,
        updateTime: null,
        createBy: null,
        updateBy: null,
        remark: null
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.daterangeEstimatedServerTime = [];
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.id)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加护理任务";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getTask(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改护理任务";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateTask(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addTask(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除护理任务编号为"' + ids + '"的数据项？').then(function () {
        return delTask(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('nur/task/export', {
        ...this.queryParams
      }, `task_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

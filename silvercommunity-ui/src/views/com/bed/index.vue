<template>
  <div class="app-container">
    <!-- <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="床位编号" prop="bedNumber">
        <el-input v-model="queryParams.bedNumber" placeholder="请输入床位编号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="状态" prop="bedStatus">
        <el-select v-model="queryParams.bedStatus" placeholder="请选择状态" clearable>
          <el-option v-for="dict in dict.type.bed_status" :key="dict.value" :label="dict.label" :value="dict.value" />
        </el-select>
      </el-form-item>
      <el-form-item label="床位号" prop="sort">
        <el-input v-model="queryParams.sort" placeholder="请输入床位号" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="楼层ID" prop="floorId">
        <el-input v-model="queryParams.floorId" placeholder="请输入楼层ID" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item label="房间ID" prop="roomId">
        <el-input v-model="queryParams.roomId" placeholder="请输入房间ID" clearable @keyup.enter.native="handleQuery" />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form> -->

    <div class="card-container" >
      <el-card style="max-width: 620px" v-for="bed in bedList" :key="bed" class="textItem" @selection-change="handleSelectionChange">
        <span>{{ bed.bedNumber }}</span>
        <span class="icon-update" ><i class="el-icon-edit"></i></span>
        <span class="icon-delete"><i class="el-icon-delete"></i></span>
        <span class="icon-add" @click="handleAdd">
          <i class="el-icon-circle-plus-outline"></i>
        </span>
        <div class="bed-list">
          <el-card style="width: 180px" shadow="hover" class="textItem2">
            
          </el-card>
        </div>
      </el-card>
    </div>

    <!-- <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
          v-hasPermi="['com:bed:add']">新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
          v-hasPermi="['com:bed:edit']">修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple" @click="handleDelete"
          v-hasPermi="['com:bed:remove']">删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
          v-hasPermi="['com:bed:export']">导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row> -->

    <el-table v-loading="loading" :data="bedList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="床位编号" align="center" prop="bedNumber" />
      <el-table-column label="状态" align="center" prop="bedStatus">
        <template slot-scope="scope">
          <dict-tag :options="dict.type.bed_status" :value="scope.row.bedStatus" />
        </template>
      </el-table-column>
      <el-table-column label="床位号" align="center" prop="sort" />
      <el-table-column label="楼层ID" align="center" prop="floorId" />
      <el-table-column label="房间ID" align="center" prop="roomId" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
            v-hasPermi="['com:bed:edit']">修改</el-button>
          <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
            v-hasPermi="['com:bed:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum" :limit.sync="queryParams.pageSize"
      @pagination="getList" />

    <!-- 添加或修改床位对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="床位编号" prop="bedNumber">
          <el-input v-model="form.bedNumber" placeholder="请输入床位编号" />
        </el-form-item>
        <el-form-item label="状态" prop="bedStatus">
          <el-radio-group v-model="form.bedStatus">
            <el-radio v-for="dict in dict.type.bed_status" :key="dict.value" :label="parseInt(dict.value)">{{ dict.label
            }}</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item label="床位号" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入床位号" />
        </el-form-item>
        <el-form-item label="楼层ID" prop="floorId">
          <el-input v-model="form.floorId" placeholder="请输入楼层ID" />
        </el-form-item>
        <el-form-item label="房间ID" prop="roomId">
          <el-input v-model="form.roomId" placeholder="请输入房间ID" />
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
import { listBed, getBed, delBed, addBed, updateBed } from "@/api/com/bed";

export default {
  name: "Bed",
  dicts: ['bed_status'],
  data() {
    return {
      floor: [1, 2, 3, 4],
      roomList: [{
        id: "101",
        bedList: [
          {
            bedId: "101-1"
          },
          {
            bedId: "101-2"
          }
        ]
      },
      {
        id: "102",
        bedList: [
          {
            bedId: "102-1"
          },
          {
            bedId: "102-2"
          },
          {
            bedId: "102-3"
          }
        ]
      },
      {
        id: "103"
      }],
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
      // 床位表格数据
      bedList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        bedNumber: null,
        bedStatus: null,
        sort: null,
        floorId: null,
        roomId: null,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        bedNumber: [
          { required: true, message: "床位编号不能为空", trigger: "blur" }
        ],
        bedStatus: [
          { required: true, message: "状态不能为空", trigger: "change" }
        ],
        sort: [
          { required: true, message: "床位号不能为空", trigger: "blur" }
        ],
        floorId: [
          { required: true, message: "楼层ID不能为空", trigger: "blur" }
        ],
        roomId: [
          { required: true, message: "房间ID不能为空", trigger: "blur" }
        ],
      }
    };
  },
  created() {
    this.getList();
  },
  methods: {
    /** 查询床位列表 */
    getList() {
      this.loading = true;
      listBed(this.queryParams).then(response => {
        this.bedList = response.rows;
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
        bedNumber: null,
        bedStatus: null,
        sort: null,
        floorId: null,
        roomId: null,
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
      this.title = "添加床位";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const id = row.id || this.ids
      getBed(id).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改床位";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.id != null) {
            updateBed(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addBed(this.form).then(response => {
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
      this.$modal.confirm('是否确认删除床位编号为"' + ids + '"的数据项？').then(function () {
        return delBed(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('com/bed/export', {
        ...this.queryParams
      }, `bed_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>

<style>
.card-container {
  width: 1250px;
  display: flex;
  flex-wrap: wrap;
  /* 允许换行 */
  justify-content: space-between;
  /* 卡片
  之间均匀分布 */
}

.textItem {
  margin-bottom: 18px;
  margin-left: 10px;
  margin-right: 50px;
  flex: 0 0 45%;
}

.bed-list {
  display: flex;
  flex-wrap: wrap;
  gap: 6px;
  /* 可选：设置床列表之间的间距 */
}

.textItem2 {
  width: 230px;
  background: rgb(244, 243, 243);
  margin-bottom: 5px;
  margin-left: 5px;
  margin-right: 5px;
  flex: 0 0 46%;
}

.icon-add {
  top: 10px;
  /* 距离顶部的距离 */
  float: right;
  color: green;
  /* 设置图标颜色为绿色 */
  cursor: pointer;
  /* 设置鼠标悬停时的光标样式 */
}

.icon-update {
  margin-left: 10px;
  cursor: pointer;
  /* 设置鼠标悬停时的光标样式 */
}

.icon-delete {
  margin-left: 10px;
  cursor: pointer;
  /* 设置鼠标悬停时的光标样式 */
}
</style>
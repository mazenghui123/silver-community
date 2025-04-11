<template>
  <div class="app-container">

    <div class="floor-container">

      <el-link type="success" v-for="(floor, index) in floorList" :key="index" class="custom-link"
        @click="handleFloorClick(floor.id)">
        <span>{{ floor.name }}</span>
        <i class="el-icon-edit" @click="handleUpdateFloor(floor)" style="margin-left: 8px;"></i>
      </el-link>
      <el-button class="addFloor" type="success" plain size="mini" @click="handleAddFloor">添加楼层</el-button>
    </div>

    <div style="margin-bottom: 10px; margin-left: 20px; margin-top: 10px;">
      <img src="@/assets/images/bedStatus-avaible.png"
        style="width: 30px; height: auto; display: inline-block; vertical-align: middle;">
      <span style="font-size: 14px; display: inline-block; vertical-align: middle; margin-left: 5px;">空闲中</span>
      <img src="@/assets/images/bedStatus-busy.png"
        style="width: 30px; height: auto; display: inline-block; vertical-align: middle; margin-left: 10px;">
      <span style="font-size: 14px; display: inline-block; vertical-align: middle; margin-left: 5px;">已入住</span>
      <img src="@/assets/images/bedStatus-rest.png"
        style="width: 30px; height: auto; display: inline-block; vertical-align: middle; margin-left: 10px;">
      <span style="font-size: 14px; display: inline-block; vertical-align: middle; margin-left: 5px;">请假中</span>
      <el-button class="addRoom" type="success" plain size="mini" @click="handleAddRoom">添加房间</el-button>
    </div>

    <div class="card-container">
      <el-card style="max-width: 620px" v-for="(roomVo, index) in roomVoList" :key="index" class="textItem"
        @selection-change="handleSelectionChange">
        <span>{{ roomVo.code }}</span>
        <span class="roomType">{{ roomVo.typeName }}</span>
        <span class="icon-update" @click="handleUpdateRoom(roomVo)"><i class="el-icon-edit"></i></span>
        <span class="icon-delete" @click="handleDeleteRoom(roomVo)"><i class="el-icon-delete"></i></span>
        <span class="icon-add" @click="handleAdd">
          <i class="el-icon-circle-plus-outline"></i>
        </span>

        <div class="bed-list">
          <el-card style="width: 180px" v-for="(bedVo, index) in roomVo.bedVoList" :key="index" shadow="hover"
            class="textItem2">
            <div style="display: flex; align-items: center;">
              <img v-if="bedVo.bedStatus === 0" src="@/assets/images/bedStatus-avaible.png"
                style="width: 20px; height: auto; margin-right: 5px;">
              <img v-else-if="bedVo.bedStatus === 1" src="@/assets/images/bedStatus-busy.png"
                style="width: 20px; height: auto; margin-right: 5px;">
              <img v-else-if="bedVo.bedStatus === 2" src="@/assets/images/bedStatus-rest.png"
                style="width: 20px; height: auto; margin-right: 5px;">
              <span>床位号：{{ bedVo.bedNumber }}</span>
              <span class="icon-update" @click="handleUpdate(roomVo)"><i class="el-icon-edit"></i></span>
              <span class="icon-delete" @click="handleDelete(roomVo)"><i class="el-icon-delete"></i></span>
            </div>
            <span style="margin-left: 25px;">姓 名：{{ bedVo.name }}</span>
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

    <!-- <el-table v-loading="loading" :data="bedList" @selection-change="handleSelectionChange">
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
</el-table> -->

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
        <el-form-item label="排序号" prop="sort">
          <el-input v-model="form.sort" placeholder="请输入排序号" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改房间对话框 -->
    <el-dialog :title="title" :visible.sync="openRoom" width="500px" append-to-body>
      <el-form ref="formRoom" :model="formRoom" :rules="rulesRoom" label-width="80px">
        <el-form-item label="房间编号" prop="code">
          <el-input v-model="formRoom.code" placeholder="请输入房间编号" />
        </el-form-item>
        <el-form-item label="排序号" prop="sort">
          <el-input v-model="formRoom.sort" placeholder="请输入排序号" />
        </el-form-item>
        <el-form-item label="房间类型" prop="typeId">
          <el-select v-model="formRoom.typeId" placeholder="请选择房间类型">
            <el-option v-for="roomType in RoomTypeList" :key="roomType.id" :label="roomType.name"
              :value="roomType.id" />
          </el-select>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormRoom">确 定</el-button>
        <el-button @click="cancelRoom">取 消</el-button>
      </div>
    </el-dialog>

    <!-- 添加或修改楼层对话框 -->
    <el-dialog :title="title" :visible.sync="openFloor" width="500px" append-to-body>
      <el-form ref="formFloor" :model="formFloor" :rules="rulesFloor" label-width="80px">
        <el-form-item label="名称" prop="name">
          <el-input v-model="formFloor.name" placeholder="请输入名称" />
        </el-form-item>
        <el-form-item label="排序" prop="code">
          <el-input-number v-model="formFloor.code" placeholder="请输入编号" :min="1" :max="100" />
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button type="primary" @click="submitFormFloor">确 定</el-button>
        <el-button type="danger" plain @click="handleDeleteFloor(formFloor)">删除</el-button>
        <el-button @click="cancelFloor">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { listBed, getBed, delBed, addBed, updateBed } from "@/api/com/bed";
import { listRoom, getRoom, delRoom, addRoom, updateRoom, listRoomVo } from "@/api/com/room";
import { listFloor, getFloor, delFloor, addFloor, updateFloor } from "@/api/com/floor";
import { listRoomType } from "@/api/com/RoomType";
import { h } from "vue";

export default {
  name: "Bed",
  dicts: ['bed_status'],
  data() {
    return {
      roomVoList: [],
      floorList: [],
      RoomTypeList: [],
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
      openFloor: false,
      openRoom: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 999,
        bedNumber: null,
        bedStatus: null,
        sort: null,
        floorId: 1,
        roomId: null,
      },
      // 表单参数
      form: {},
      formFloor: {},
      formRoom: {},
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
        ]
      },
      rulesRoom: {
        code: [
          { required: true, message: "房间编号不能为空", trigger: "blur" }
        ],
        sort: [
          { required: true, message: "排序号不能为空", trigger: "blur" }
        ],
        typeId: [
          { required: true, message: "房间类型id不能为空", trigger: "blur" }
        ]
      },
      rulesFloor: {
        name: [
          { required: true, message: "名称不能为空", trigger: "blur" }
        ],
        code: [
          { required: true, message: "编号不能为空", trigger: "blur" }
        ],
      },
      showEditIcon: null, // 用于控制编辑图标的显示
      currentFloorId: 1, // 新增变量来存储当前选中的楼层 ID
    };
  },
  created() {
    this.getList();
    this.getFloorList();
    this.getRoomVoList(1);
    this.getListRoomType();
  },
  methods: {
    /** 查询楼层列表 */
    getFloorList() {
      listFloor().then(response => {
        this.floorList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    /** 查询床位列表 */
    getList() {
      this.loading = true;
      listBed(this.queryParams).then(response => {
        this.bedList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },

    /** 查询房间vo列表 */
    getRoomVoList(floorId) {
      this.queryParams.floorId = floorId; // 设置楼层ID
      listRoomVo(this.queryParams).then(response => {
        this.roomVoList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    /** 查询房型列表 */
    getListRoomType() {
      this.loading = true;
      listRoomType(this.queryParams).then(response => {
        this.RoomTypeList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    handleFloorClick(floorId) {
      this.currentFloorId = floorId; // 更新当前选中的楼层 ID
      this.getRoomVoList(floorId);
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
    // 取消按钮
    cancelFloor() {
      this.openFloor = false;
      this.resetFloor();
    },
    cancelRoom() {
      this.openRoom = false;
      this.resetRoom();
    },
    // 表单重置
    resetFloor() {
      this.formFloor = {
        id: null,
        name: null,
        code: null,
        createTime: null,
        updateTime: null,
        createBy: null,
        updateBy: null,
        remark: null
      };
      this.resetForm("formFloor");
    },
    // 表单重置
    resetRoom() {
      this.formRoom = {
        id: null,
        code: null,
        sort: null,
        typeId: null,
        typeName: null,
        floorId: null,
        remark: null,
        isDeleted: null,
        createBy: null,
        updateBy: null,
        createTime: null,
        updateTime: null
      };
      this.resetForm("formRoom");
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
    handleAddFloor() {
      this.resetFloor();
      this.openFloor = true;
      this.title = "添加楼层";
      /*       this.$nextTick(() => {
              this.$refs.formFloor.resetFields(); // 重置表单字段和验证状态
            }); */
    },
    /** 修改按钮操作 */
    handleUpdateFloor(row) {
      this.resetFloor();
      const id = row.id || this.ids
      getFloor(id).then(response => {
        this.formFloor = response.data;
        this.openFloor = true;
        this.title = "修改楼层";
      });
    },
    /** 新增按钮操作 */
    handleAddRoom() {
      this.resetRoom();
      // 使用 currentFloorId 作为 floorId
      if (this.currentFloorId !== null) {
        this.formRoom.floorId = this.currentFloorId;
      }
      this.openRoom = true;
      this.title = "添加房间";
    },
    /** 修改按钮操作 */
    handleUpdateRoom(row) {
      this.resetRoom();
      const id = row.id || this.ids
      getRoom(id).then(response => {
        this.formRoom = response.data;
        this.openRoom = true;
        this.title = "修改房间";
      });
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      // 使用 currentFloorId 作为 floorId
      if (this.currentFloorId !== null) {
        this.form.floorId = this.currentFloorId;
      }
      /* if (this.roomVoList.length > 0) {
        this.formRoom.floorId = this.roomVoList[0].floorId;
      } */
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
              this.getRoomVoList(this.currentFloorId);
            });
          } else {
            addBed(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getRoomVoList(this.currentFloorId);
            });
          }
        }
      });
    },
    submitFormRoom() {
      this.$refs["formRoom"].validate(valid => {
        if (valid) {
          if (this.formRoom.id != null) {
            updateRoom(this.formRoom).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.openRoom = false;
              this.getRoomVoList(this.currentFloorId); // 使用 currentFloorId
            });
          } else {
            addRoom(this.formRoom).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.openRoom = false;
              this.getRoomVoList(this.currentFloorId); // 使用 currentFloorId
            });
          }
        }
      });
    },
    submitFormFloor() {
      this.$refs["formFloor"].validate(valid => {
        if (valid) {
          if (this.formFloor.id != null) {
            updateFloor(this.formFloor).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.openFloor = false;
              this.getFloorList();
            });
          } else {
            addFloor(this.formFloor).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.openFloor = false;
              this.getFloorList();
            });
          }
        }
      });
    },
    /** 删除床位按钮操作 */
    handleDelete(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除床位编号为"' + ids + '"的数据项？').then(function () {
        return delBed(ids);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 删除楼层按钮操作 */
    handleDeleteFloor(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除"' + row.name + '"').then(function () {
        return delFloor(ids);
      }).then(() => {
        this.getFloorList();
        this.$modal.msgSuccess("删除成功");
        this.openFloor = false;
      }).catch(() => { });
    },
    /** 删除房间按钮操作 */
    handleDeleteRoom(row) {
      const ids = row.id || this.ids;
      this.$modal.confirm('是否确认删除房间"' + row.code + '"').then(function () {
        return delRoom(ids);
      }).then(() => {
        this.getRoomVoList(row.floorId);
        this.$modal.msgSuccess("删除成功");
      }).catch(() => { });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('com/bed/export', {
        ...this.queryParams
      }, `bed_${new Date().getTime()}.xlsx`)
    },
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

.roomType {
  margin-left: 5px;
  background: rgb(206, 232, 165);
  font-size: 0.55em;
}

.floor-container {
  border-bottom: 1px solid rgb(187, 223, 220);
  padding-bottom: 20px;
}

.custom-link {
  width: 60px;
  color: black;
  /* 默认颜色为黑色 */
  position: relative;

}

.edit-floorUpdate {
  position: absolute;
  right: 0;
  top: 50%;
  transform: translateY(-50%);
  cursor: pointer;
}

.addFloor {
  float: right
}

.addRoom {
  float: right;
}
</style>
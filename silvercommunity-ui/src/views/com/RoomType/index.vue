<template>
    <div class="app-container">
        <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch"
            label-width="68px">
            <el-form-item label="房型名称" prop="name">
                <el-input v-model="queryParams.name" placeholder="请输入房型名称" clearable
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item label="床位数量" prop="bedCount">
                <el-input v-model="queryParams.bedCount" placeholder="请输入床位数量" clearable
                    @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item label="费用" prop="price">
                <el-input v-model="queryParams.price" placeholder="请输入费用" clearable @keyup.enter.native="handleQuery" />
            </el-form-item>
            <el-form-item label="状态" prop="status">
                <el-select v-model="queryParams.status" placeholder="请选择状态" clearable>
                    <el-option v-for="dict in dict.type.com_dev_status" :key="dict.value" :label="dict.label"
                        :value="dict.value" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
            </el-form-item>
        </el-form>

        <el-row :gutter="10" class="mb8">
            <el-col :span="1.5">
                <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                    v-hasPermi="['com:RoomType:add']">新增</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="success" plain icon="el-icon-edit" size="mini" :disabled="single" @click="handleUpdate"
                    v-hasPermi="['com:RoomType:edit']">修改</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="danger" plain icon="el-icon-delete" size="mini" :disabled="multiple"
                    @click="handleDelete" v-hasPermi="['com:RoomType:remove']">删除</el-button>
            </el-col>
            <el-col :span="1.5">
                <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                    v-hasPermi="['com:RoomType:export']">导出</el-button>
            </el-col>
            <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
        </el-row>

        <el-table v-loading="loading" :data="RoomTypeList" @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" align="center" />
            <el-table-column label="主键ID" align="center" prop="id" />
            <el-table-column label="房型名称" align="center" prop="name" />
            <el-table-column label="床位数量" align="center" prop="bedCount" />
            <el-table-column label="费用" align="center" prop="price" />
            <el-table-column label="介绍" align="center" prop="introduction" show-overflow-tooltip/>
            <el-table-column label="照片" align="center" prop="photo" show-overflow-tooltip/>
            <el-table-column label="状态" align="center" prop="status">
                <template slot-scope="scope">
                    <dict-tag :options="dict.type.com_dev_status" :value="scope.row.status" />
                </template>
            </el-table-column>
            <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                <template slot-scope="scope">
                    <el-button size="mini" type="text" icon="el-icon-edit" @click="handleUpdate(scope.row)"
                        v-hasPermi="['com:RoomType:edit']">修改</el-button>
                    <el-button size="mini" type="text" icon="el-icon-delete" @click="handleDelete(scope.row)"
                        v-hasPermi="['com:RoomType:remove']">删除</el-button>
                </template>
            </el-table-column>
        </el-table>

        <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum"
            :limit.sync="queryParams.pageSize" @pagination="getList" />

        <!-- 添加或修改房型对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                <el-form-item label="房型名称" prop="name">
                    <el-input v-model="form.name" placeholder="请输入房型名称" />
                </el-form-item>
                <el-form-item label="床位数量" prop="bedCount">
                    <el-input v-model="form.bedCount" placeholder="请输入床位数量" />
                </el-form-item>
                <el-form-item label="费用" prop="price">
                    <el-input v-model="form.price" placeholder="请输入费用" />
                </el-form-item>
                <el-form-item label="介绍" prop="introduction">
                    <el-input v-model="form.introduction" type="textarea" placeholder="请输入内容" />
                </el-form-item>
                <el-form-item label="照片" prop="photo">
                    <el-input v-model="form.photo" placeholder="请输入照片" />
                </el-form-item>
                <el-form-item label="状态" prop="status">
                    <el-radio-group v-model="form.status">
                        <el-radio v-for="dict in dict.type.com_dev_status" :key="dict.value"
                            :label="parseInt(dict.value)">{{
                                dict.label }}</el-radio>
                    </el-radio-group>
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
import { listRoomType, getRoomType, delRoomType, addRoomType, updateRoomType } from "@/api/com/RoomType";

export default {
    name: "RoomType",
    dicts: ['com_dev_status'],
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
            // 房型表格数据
            RoomTypeList: [],
            // 弹出层标题
            title: "",
            // 是否显示弹出层
            open: false,
            // 查询参数
            queryParams: {
                pageNum: 1,
                pageSize: 10,
                name: null,
                bedCount: null,
                price: null,
                status: null,
            },
            // 表单参数
            form: {},
            // 表单校验
            rules: {
                name: [
                    { required: true, message: "房型名称不能为空", trigger: "blur" }
                ],
                bedCount: [
                    { required: true, message: "床位数量不能为空", trigger: "blur" }
                ],
                price: [
                    { required: true, message: "费用不能为空", trigger: "blur" }
                ],
                status: [
                    { required: true, message: "状态不能为空", trigger: "change" }
                ],
            }
        };
    },
    created() {
        this.getList();
    },
    methods: {
        /** 查询房型列表 */
        getList() {
            this.loading = true;
            listRoomType(this.queryParams).then(response => {
                this.RoomTypeList = response.rows;
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
                name: null,
                bedCount: null,
                price: null,
                introduction: null,
                photo: null,
                status: null,
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
            this.title = "添加房型";
        },
        /** 修改按钮操作 */
        handleUpdate(row) {
            this.reset();
            const id = row.id || this.ids
            getRoomType(id).then(response => {
                this.form = response.data;
                this.open = true;
                this.title = "修改房型";
            });
        },
        /** 提交按钮 */
        submitForm() {
            this.$refs["form"].validate(valid => {
                if (valid) {
                    if (this.form.id != null) {
                        updateRoomType(this.form).then(response => {
                            this.$modal.msgSuccess("修改成功");
                            this.open = false;
                            this.getList();
                        });
                    } else {
                        addRoomType(this.form).then(response => {
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
            this.$modal.confirm('是否确认删除房型编号为"' + ids + '"的数据项？').then(function () {
                return delRoomType(ids);
            }).then(() => {
                this.getList();
                this.$modal.msgSuccess("删除成功");
            }).catch(() => { });
        },
        /** 导出按钮操作 */
        handleExport() {
            this.download('com/RoomType/export', {
                ...this.queryParams
            }, `RoomType_${new Date().getTime()}.xlsx`)
        }
    }
};
</script>
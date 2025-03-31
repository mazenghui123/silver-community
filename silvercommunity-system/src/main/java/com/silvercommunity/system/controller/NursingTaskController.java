package com.silvercommunity.system.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.silvercommunity.common.annotation.Log;
import com.silvercommunity.common.core.controller.BaseController;
import com.silvercommunity.common.core.domain.AjaxResult;
import com.silvercommunity.common.enums.BusinessType;
import com.silvercommunity.system.domain.NursingTask;
import com.silvercommunity.system.service.INursingTaskService;
import com.silvercommunity.common.utils.poi.ExcelUtil;
import com.silvercommunity.common.core.page.TableDataInfo;

/**
 * 护理任务Controller
 * 
 * @author mzh
 * @date 2025-03-20
 */
@RestController
@RequestMapping("/nur/task")
public class NursingTaskController extends BaseController
{
    @Autowired
    private INursingTaskService nursingTaskService;

    /**
     * 查询护理任务列表
     */
    @PreAuthorize("@ss.hasPermi('nur:task:list')")
    @GetMapping("/list")
    public TableDataInfo list(NursingTask nursingTask)
    {
        startPage();
        List<NursingTask> list = nursingTaskService.selectNursingTaskList(nursingTask);
        return getDataTable(list);
    }

    /**
     * 导出护理任务列表
     */
    @PreAuthorize("@ss.hasPermi('nur:task:export')")
    @Log(title = "护理任务", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, NursingTask nursingTask)
    {
        List<NursingTask> list = nursingTaskService.selectNursingTaskList(nursingTask);
        ExcelUtil<NursingTask> util = new ExcelUtil<NursingTask>(NursingTask.class);
        util.exportExcel(response, list, "护理任务数据");
    }

    /**
     * 获取护理任务详细信息
     */
    @PreAuthorize("@ss.hasPermi('nur:task:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(nursingTaskService.selectNursingTaskById(id));
    }

    /**
     * 新增护理任务
     */
    @PreAuthorize("@ss.hasPermi('nur:task:add')")
    @Log(title = "护理任务", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody NursingTask nursingTask)
    {
        return toAjax(nursingTaskService.insertNursingTask(nursingTask));
    }

    /**
     * 修改护理任务
     */
    @PreAuthorize("@ss.hasPermi('nur:task:edit')")
    @Log(title = "护理任务", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody NursingTask nursingTask)
    {
        return toAjax(nursingTaskService.updateNursingTask(nursingTask));
    }

    /**
     * 删除护理任务
     */
    @PreAuthorize("@ss.hasPermi('nur:task:remove')")
    @Log(title = "护理任务", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(nursingTaskService.deleteNursingTaskByIds(ids));
    }
}

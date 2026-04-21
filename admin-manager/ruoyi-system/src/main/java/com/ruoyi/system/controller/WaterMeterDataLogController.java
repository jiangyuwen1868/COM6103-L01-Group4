package com.ruoyi.system.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.WaterMeterDataLog;
import com.ruoyi.system.service.IWaterMeterDataLogService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 智能水数据流水Controller
 * 
 * @author ruoyi
 * @date 2026-03-17
 */
@Controller
@RequestMapping("/system/devicelog")
public class WaterMeterDataLogController extends BaseController
{
    private String prefix = "system/devicelog";

    @Autowired
    private IWaterMeterDataLogService waterMeterDataLogService;

    @RequiresPermissions("system:devicelog:view")
    @GetMapping()
    public String devicelog()
    {
        return prefix + "/devicelog";
    }

    /**
     * 查询智能水数据流水列表
     */
    @RequiresPermissions("system:devicelog:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WaterMeterDataLog waterMeterDataLog)
    {
        startPage();
        List<WaterMeterDataLog> list = waterMeterDataLogService.selectWaterMeterDataLogList(waterMeterDataLog);
        return getDataTable(list);
    }

    /**
     * 导出智能水数据流水列表
     */
    @RequiresPermissions("system:devicelog:export")
    @Log(title = "智能水数据流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WaterMeterDataLog waterMeterDataLog)
    {
        List<WaterMeterDataLog> list = waterMeterDataLogService.selectWaterMeterDataLogList(waterMeterDataLog);
        ExcelUtil<WaterMeterDataLog> util = new ExcelUtil<WaterMeterDataLog>(WaterMeterDataLog.class);
        return util.exportExcel(list, "log");
    }

    /**
     * 新增智能水数据流水
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存智能水数据流水
     */
    @RequiresPermissions("system:devicelog:add")
    @Log(title = "智能水数据流水", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WaterMeterDataLog waterMeterDataLog)
    {
        return toAjax(waterMeterDataLogService.insertWaterMeterDataLog(waterMeterDataLog));
    }

    /**
     * 修改智能水数据流水
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        WaterMeterDataLog waterMeterDataLog = waterMeterDataLogService.selectWaterMeterDataLogById(id);
        mmap.put("waterMeterDataLog", waterMeterDataLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存智能水数据流水
     */
    @RequiresPermissions("system:devicelog:edit")
    @Log(title = "智能水数据流水", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WaterMeterDataLog waterMeterDataLog)
    {
        return toAjax(waterMeterDataLogService.updateWaterMeterDataLog(waterMeterDataLog));
    }

    /**
     * 删除智能水数据流水
     */
    @RequiresPermissions("system:devicelog:remove")
    @Log(title = "智能水数据流水", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(waterMeterDataLogService.deleteWaterMeterDataLogByIds(ids));
    }
}

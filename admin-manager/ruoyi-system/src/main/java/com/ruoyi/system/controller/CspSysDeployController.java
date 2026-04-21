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
import com.ruoyi.system.domain.CspSysDeploy;
import com.ruoyi.system.service.ICspSysDeployService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
@Controller
@RequestMapping("/system/deploy")
public class CspSysDeployController extends BaseController
{
    private String prefix = "system/deploy";

    @Autowired
    private ICspSysDeployService cspSysDeployService;

    @RequiresPermissions("system:deploy:view")
    @GetMapping()
    public String deploy()
    {
        return prefix + "/deploy";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:deploy:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CspSysDeploy cspSysDeploy)
    {
        startPage();
        List<CspSysDeploy> list = cspSysDeployService.selectCspSysDeployList(cspSysDeploy);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:deploy:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CspSysDeploy cspSysDeploy)
    {
        List<CspSysDeploy> list = cspSysDeployService.selectCspSysDeployList(cspSysDeploy);
        ExcelUtil<CspSysDeploy> util = new ExcelUtil<CspSysDeploy>(CspSysDeploy.class);
        return util.exportExcel(list, "deploy");
    }

    /**
     * 新增【请填写功能名称】
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存【请填写功能名称】
     */
    @RequiresPermissions("system:deploy:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CspSysDeploy cspSysDeploy)
    {
        return toAjax(cspSysDeployService.insertCspSysDeploy(cspSysDeploy));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{groupid}")
    public String edit(@PathVariable("groupid") String groupid, ModelMap mmap)
    {
        CspSysDeploy cspSysDeploy = cspSysDeployService.selectCspSysDeployById(groupid);
        mmap.put("cspSysDeploy", cspSysDeploy);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("system:deploy:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CspSysDeploy cspSysDeploy)
    {
        return toAjax(cspSysDeployService.updateCspSysDeploy(cspSysDeploy));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:deploy:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cspSysDeployService.deleteCspSysDeployByIds(ids));
    }
}

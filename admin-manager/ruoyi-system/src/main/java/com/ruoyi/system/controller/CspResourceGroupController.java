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
import com.ruoyi.system.domain.CspResourceGroup;
import com.ruoyi.system.service.ICspResourceGroupService;
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
@RequestMapping("/system/group")
public class CspResourceGroupController extends BaseController
{
    private String prefix = "system/group";

    @Autowired
    private ICspResourceGroupService cspResourceGroupService;

    @RequiresPermissions("system:group:view")
    @GetMapping()
    public String group()
    {
        return prefix + "/group";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:group:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CspResourceGroup cspResourceGroup)
    {
        startPage();
        List<CspResourceGroup> list = cspResourceGroupService.selectCspResourceGroupList(cspResourceGroup);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:group:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CspResourceGroup cspResourceGroup)
    {
        List<CspResourceGroup> list = cspResourceGroupService.selectCspResourceGroupList(cspResourceGroup);
        ExcelUtil<CspResourceGroup> util = new ExcelUtil<CspResourceGroup>(CspResourceGroup.class);
        return util.exportExcel(list, "group");
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
    @RequiresPermissions("system:group:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CspResourceGroup cspResourceGroup)
    {
        return toAjax(cspResourceGroupService.insertCspResourceGroup(cspResourceGroup));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{groupid}")
    public String edit(@PathVariable("groupid") String groupid, ModelMap mmap)
    {
        CspResourceGroup cspResourceGroup = cspResourceGroupService.selectCspResourceGroupById(groupid);
        mmap.put("cspResourceGroup", cspResourceGroup);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("system:group:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CspResourceGroup cspResourceGroup)
    {
        return toAjax(cspResourceGroupService.updateCspResourceGroup(cspResourceGroup));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:group:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cspResourceGroupService.deleteCspResourceGroupByIds(ids));
    }
}

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
import com.ruoyi.system.domain.CspErrorinfo;
import com.ruoyi.system.service.ICspErrorinfoService;
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
@RequestMapping("/system/errorinfo")
public class CspErrorinfoController extends BaseController
{
    private String prefix = "system/errorinfo";

    @Autowired
    private ICspErrorinfoService cspErrorinfoService;

    @RequiresPermissions("system:errorinfo:view")
    @GetMapping()
    public String errorinfo()
    {
        return prefix + "/errorinfo";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:errorinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CspErrorinfo cspErrorinfo)
    {
        startPage();
        List<CspErrorinfo> list = cspErrorinfoService.selectCspErrorinfoList(cspErrorinfo);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:errorinfo:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CspErrorinfo cspErrorinfo)
    {
        List<CspErrorinfo> list = cspErrorinfoService.selectCspErrorinfoList(cspErrorinfo);
        ExcelUtil<CspErrorinfo> util = new ExcelUtil<CspErrorinfo>(CspErrorinfo.class);
        return util.exportExcel(list, "errorinfo");
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
    @RequiresPermissions("system:errorinfo:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CspErrorinfo cspErrorinfo)
    {
        return toAjax(cspErrorinfoService.insertCspErrorinfo(cspErrorinfo));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{errorcode}")
    public String edit(@PathVariable("errorcode") String errorcode, ModelMap mmap)
    {
        CspErrorinfo cspErrorinfo = cspErrorinfoService.selectCspErrorinfoById(errorcode);
        mmap.put("cspErrorinfo", cspErrorinfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("system:errorinfo:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CspErrorinfo cspErrorinfo)
    {
        return toAjax(cspErrorinfoService.updateCspErrorinfo(cspErrorinfo));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:errorinfo:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cspErrorinfoService.deleteCspErrorinfoByIds(ids));
    }
}

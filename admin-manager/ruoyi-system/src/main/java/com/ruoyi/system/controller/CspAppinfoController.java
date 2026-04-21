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
import com.ruoyi.system.domain.CspAppinfo;
import com.ruoyi.system.service.ICspAppinfoService;
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
@RequestMapping("/system/appinfo")
public class CspAppinfoController extends BaseController
{
    private String prefix = "system/appinfo";

    @Autowired
    private ICspAppinfoService cspAppinfoService;

    @RequiresPermissions("system:appinfo:view")
    @GetMapping()
    public String appinfo()
    {
        return prefix + "/appinfo";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:appinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CspAppinfo cspAppinfo)
    {
        startPage();
        List<CspAppinfo> list = cspAppinfoService.selectCspAppinfoList(cspAppinfo);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:appinfo:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CspAppinfo cspAppinfo)
    {
        List<CspAppinfo> list = cspAppinfoService.selectCspAppinfoList(cspAppinfo);
        ExcelUtil<CspAppinfo> util = new ExcelUtil<CspAppinfo>(CspAppinfo.class);
        return util.exportExcel(list, "appinfo");
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
    @RequiresPermissions("system:appinfo:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CspAppinfo cspAppinfo)
    {
        return toAjax(cspAppinfoService.insertCspAppinfo(cspAppinfo));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{appid}")
    public String edit(@PathVariable("appid") String appid, ModelMap mmap)
    {
        CspAppinfo cspAppinfo = cspAppinfoService.selectCspAppinfoById(appid);
        mmap.put("cspAppinfo", cspAppinfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("system:appinfo:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CspAppinfo cspAppinfo)
    {
        return toAjax(cspAppinfoService.updateCspAppinfo(cspAppinfo));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:appinfo:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cspAppinfoService.deleteCspAppinfoByIds(ids));
    }
}

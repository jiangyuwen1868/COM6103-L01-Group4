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
import com.ruoyi.system.domain.CspAppSrvauth;
import com.ruoyi.system.service.ICspAppSrvauthService;
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
@RequestMapping("/system/srvauth")
public class CspAppSrvauthController extends BaseController
{
    private String prefix = "system/srvauth";

    @Autowired
    private ICspAppSrvauthService cspAppSrvauthService;

    @RequiresPermissions("system:srvauth:view")
    @GetMapping()
    public String srvauth()
    {
        return prefix + "/srvauth";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:srvauth:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CspAppSrvauth cspAppSrvauth)
    {
        startPage();
        List<CspAppSrvauth> list = cspAppSrvauthService.selectCspAppSrvauthList(cspAppSrvauth);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:srvauth:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CspAppSrvauth cspAppSrvauth)
    {
        List<CspAppSrvauth> list = cspAppSrvauthService.selectCspAppSrvauthList(cspAppSrvauth);
        ExcelUtil<CspAppSrvauth> util = new ExcelUtil<CspAppSrvauth>(CspAppSrvauth.class);
        return util.exportExcel(list, "srvauth");
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
    @RequiresPermissions("system:srvauth:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CspAppSrvauth cspAppSrvauth)
    {
        return toAjax(cspAppSrvauthService.insertCspAppSrvauth(cspAppSrvauth));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{appid}")
    public String edit(@PathVariable("appid") String appid, ModelMap mmap)
    {
        CspAppSrvauth cspAppSrvauth = cspAppSrvauthService.selectCspAppSrvauthById(appid);
        mmap.put("cspAppSrvauth", cspAppSrvauth);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("system:srvauth:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CspAppSrvauth cspAppSrvauth)
    {
        return toAjax(cspAppSrvauthService.updateCspAppSrvauth(cspAppSrvauth));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:srvauth:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cspAppSrvauthService.deleteCspAppSrvauthByIds(ids));
    }
}

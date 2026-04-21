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
import com.ruoyi.system.domain.CspFlowcontrol;
import com.ruoyi.system.service.ICspFlowcontrolService;
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
@RequestMapping("/system/flowcontrol")
public class CspFlowcontrolController extends BaseController
{
    private String prefix = "system/flowcontrol";

    @Autowired
    private ICspFlowcontrolService cspFlowcontrolService;

    @RequiresPermissions("system:flowcontrol:view")
    @GetMapping()
    public String flowcontrol()
    {
        return prefix + "/flowcontrol";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("system:flowcontrol:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CspFlowcontrol cspFlowcontrol)
    {
        startPage();
        List<CspFlowcontrol> list = cspFlowcontrolService.selectCspFlowcontrolList(cspFlowcontrol);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("system:flowcontrol:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CspFlowcontrol cspFlowcontrol)
    {
        List<CspFlowcontrol> list = cspFlowcontrolService.selectCspFlowcontrolList(cspFlowcontrol);
        ExcelUtil<CspFlowcontrol> util = new ExcelUtil<CspFlowcontrol>(CspFlowcontrol.class);
        return util.exportExcel(list, "flowcontrol");
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
    @RequiresPermissions("system:flowcontrol:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CspFlowcontrol cspFlowcontrol)
    {
        return toAjax(cspFlowcontrolService.insertCspFlowcontrol(cspFlowcontrol));
    }

    /**
     * 修改【请填写功能名称】
     */
    @GetMapping("/edit/{appid}/{txcode}")
    public String edit(@PathVariable("appid") String appid, @PathVariable("txcode") String txcode, ModelMap mmap)
    {
        CspFlowcontrol cspFlowcontrol = new CspFlowcontrol();
        cspFlowcontrol.setAppid(appid);
        cspFlowcontrol.setTxcode(txcode);
        CspFlowcontrol result = cspFlowcontrolService.selectCspFlowcontrolById(cspFlowcontrol);
        if (result != null) {
            // 保留从路径参数获取的appid和txcode
            result.setAppid(appid);
            result.setTxcode(txcode);
            cspFlowcontrol = result;
        }
        // 确保opswitch字段有默认值
        if (cspFlowcontrol.getOpswitch() == null) {
            cspFlowcontrol.setOpswitch("0");
        }
        mmap.put("cspFlowcontrol", cspFlowcontrol);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("system:flowcontrol:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CspFlowcontrol cspFlowcontrol)
    {
        return toAjax(cspFlowcontrolService.updateCspFlowcontrol(cspFlowcontrol));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("system:flowcontrol:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String appid, String txcode)
    {
        CspFlowcontrol cspFlowcontrol = new CspFlowcontrol();
        cspFlowcontrol.setAppid(appid);
        cspFlowcontrol.setTxcode(txcode);
        return toAjax(cspFlowcontrolService.deleteCspFlowcontrolById(cspFlowcontrol));
    }

    /**
     * 切换流控开关状态
     */
    @RequiresPermissions("system:flowcontrol:edit")
    @Log(title = "流控开关切换", businessType = BusinessType.UPDATE)
    @PostMapping( "/toggleSwitch")
    @ResponseBody
    public AjaxResult toggleSwitch(String appid, String txcode, String opswitch)
    {
        CspFlowcontrol cspFlowcontrol = new CspFlowcontrol();
        cspFlowcontrol.setAppid(appid);
        cspFlowcontrol.setTxcode(txcode);
        cspFlowcontrol.setOpswitch(opswitch);
        return toAjax(cspFlowcontrolService.updateCspFlowcontrol(cspFlowcontrol));
    }
}

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
import com.ruoyi.system.domain.WaterMeterDevice;
import com.ruoyi.system.service.IWaterMeterDeviceService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 智能水设备信息Controller
 * 
 * @author ruoyi
 * @date 2026-03-17
 */
@Controller
@RequestMapping("/system/device")
public class WaterMeterDeviceController extends BaseController
{
    private String prefix = "system/device";

    @Autowired
    private IWaterMeterDeviceService waterMeterDeviceService;

    @RequiresPermissions("system:device:view")
    @GetMapping()
    public String device()
    {
        return prefix + "/device";
    }

    /**
     * 查询智能水设备信息列表
     */
    @RequiresPermissions("system:device:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WaterMeterDevice waterMeterDevice)
    {
        startPage();
        List<WaterMeterDevice> list = waterMeterDeviceService.selectWaterMeterDeviceList(waterMeterDevice);
        return getDataTable(list);
    }

    /**
     * 导出智能水设备信息列表
     */
    @RequiresPermissions("system:device:export")
    @Log(title = "智能水设备信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WaterMeterDevice waterMeterDevice)
    {
        List<WaterMeterDevice> list = waterMeterDeviceService.selectWaterMeterDeviceList(waterMeterDevice);
        ExcelUtil<WaterMeterDevice> util = new ExcelUtil<WaterMeterDevice>(WaterMeterDevice.class);
        return util.exportExcel(list, "device");
    }

    /**
     * 新增智能水设备信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存智能水设备信息
     */
    @RequiresPermissions("system:device:add")
    @Log(title = "智能水设备信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WaterMeterDevice waterMeterDevice)
    {
        return toAjax(waterMeterDeviceService.insertWaterMeterDevice(waterMeterDevice));
    }

    /**
     * 修改智能水设备信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        WaterMeterDevice waterMeterDevice = waterMeterDeviceService.selectWaterMeterDeviceById(id);
        mmap.put("waterMeterDevice", waterMeterDevice);
        return prefix + "/edit";
    }

    /**
     * 修改保存智能水设备信息
     */
    @RequiresPermissions("system:device:edit")
    @Log(title = "智能水设备信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WaterMeterDevice waterMeterDevice)
    {
        return toAjax(waterMeterDeviceService.updateWaterMeterDevice(waterMeterDevice));
    }

    /**
     * 删除智能水设备信息
     */
    @RequiresPermissions("system:device:remove")
    @Log(title = "智能水设备信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(waterMeterDeviceService.deleteWaterMeterDeviceByIds(ids));
    }

    /**
     * 获取设备总数
     */
    @PostMapping("/getTotalDevices")
    @ResponseBody
    public AjaxResult getTotalDevices()
    {
        return AjaxResult.success(waterMeterDeviceService.getTotalDevices());
    }

    /**
     * 获取正常运行设备数
     */
    @PostMapping("/getNormalDevices")
    @ResponseBody
    public AjaxResult getNormalDevices()
    {
        return AjaxResult.success(waterMeterDeviceService.getNormalDevices());
    }

    /**
     * 获取故障设备数
     */
    @PostMapping("/getFaultyDevices")
    @ResponseBody
    public AjaxResult getFaultyDevices()
    {
        return AjaxResult.success(waterMeterDeviceService.getFaultyDevices());
    }

    /**
     * 获取未激活设备数
     */
    @PostMapping("/getUnactivatedDevices")
    @ResponseBody
    public AjaxResult getUnactivatedDevices()
    {
        return AjaxResult.success(waterMeterDeviceService.getUnactivatedDevices());
    }

    /**
     * 获取设备状态分布
     */
    @PostMapping("/getStatusDistribution")
    @ResponseBody
    public AjaxResult getStatusDistribution()
    {
        return AjaxResult.success(waterMeterDeviceService.getStatusDistribution());
    }

    /**
     * 获取设备类型分布
     */
    @PostMapping("/getTypeDistribution")
    @ResponseBody
    public AjaxResult getTypeDistribution()
    {
        return AjaxResult.success(waterMeterDeviceService.getTypeDistribution());
    }

    /**
     * 获取用水量统计
     */
    @PostMapping("/getWaterUsage")
    @ResponseBody
    public AjaxResult getWaterUsage()
    {
        return AjaxResult.success(waterMeterDeviceService.getWaterUsage());
    }
}

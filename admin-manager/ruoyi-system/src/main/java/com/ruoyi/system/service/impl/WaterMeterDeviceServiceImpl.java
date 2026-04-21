package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WaterMeterDeviceMapper;
import com.ruoyi.system.domain.WaterMeterDevice;
import com.ruoyi.system.service.IWaterMeterDeviceService;
import com.ruoyi.common.core.text.Convert;

/**
 * 智能水设备信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-17
 */
@Service
public class WaterMeterDeviceServiceImpl implements IWaterMeterDeviceService 
{
    @Autowired
    private WaterMeterDeviceMapper waterMeterDeviceMapper;

    /**
     * 查询智能水设备信息
     * 
     * @param id 智能水设备信息ID
     * @return 智能水设备信息
     */
    @Override
    public WaterMeterDevice selectWaterMeterDeviceById(String id)
    {
        return waterMeterDeviceMapper.selectWaterMeterDeviceById(id);
    }

    /**
     * 查询智能水设备信息列表
     * 
     * @param waterMeterDevice 智能水设备信息
     * @return 智能水设备信息
     */
    @Override
    public List<WaterMeterDevice> selectWaterMeterDeviceList(WaterMeterDevice waterMeterDevice)
    {
        return waterMeterDeviceMapper.selectWaterMeterDeviceList(waterMeterDevice);
    }

    /**
     * 新增智能水设备信息
     * 
     * @param waterMeterDevice 智能水设备信息
     * @return 结果
     */
    @Override
    public int insertWaterMeterDevice(WaterMeterDevice waterMeterDevice)
    {
        waterMeterDevice.setCreateTime(DateUtils.getNowDate());
        return waterMeterDeviceMapper.insertWaterMeterDevice(waterMeterDevice);
    }

    /**
     * 修改智能水设备信息
     * 
     * @param waterMeterDevice 智能水设备信息
     * @return 结果
     */
    @Override
    public int updateWaterMeterDevice(WaterMeterDevice waterMeterDevice)
    {
        waterMeterDevice.setUpdateTime(DateUtils.getNowDate());
        return waterMeterDeviceMapper.updateWaterMeterDevice(waterMeterDevice);
    }

    /**
     * 删除智能水设备信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWaterMeterDeviceByIds(String ids)
    {
        return waterMeterDeviceMapper.deleteWaterMeterDeviceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除智能水设备信息信息
     * 
     * @param id 智能水设备信息ID
     * @return 结果
     */
    @Override
    public int deleteWaterMeterDeviceById(String id)
    {
        return waterMeterDeviceMapper.deleteWaterMeterDeviceById(id);
    }

    /**
     * 获取设备总数
     * 
     * @return 设备总数
     */
    @Override
    public long getTotalDevices()
    {
        return waterMeterDeviceMapper.getTotalDevices();
    }

    /**
     * 获取正常运行设备数
     * 
     * @return 正常运行设备数
     */
    @Override
    public long getNormalDevices()
    {
        return waterMeterDeviceMapper.getNormalDevices();
    }

    /**
     * 获取故障设备数
     * 
     * @return 故障设备数
     */
    @Override
    public long getFaultyDevices()
    {
        return waterMeterDeviceMapper.getFaultyDevices();
    }

    /**
     * 获取未激活设备数
     * 
     * @return 未激活设备数
     */
    @Override
    public long getUnactivatedDevices()
    {
        return waterMeterDeviceMapper.getUnactivatedDevices();
    }

    /**
     * 获取设备状态分布
     * 
     * @return 设备状态分布
     */
    @Override
    public java.util.Map<Long, Long> getStatusDistribution()
    {
        java.util.List<java.util.Map<String, Object>> list = waterMeterDeviceMapper.getStatusDistribution();
        java.util.Map<Long, Long> map = new java.util.HashMap<>();
        for (java.util.Map<String, Object> item : list)
        {
            Long status = ((Number) item.get("status")).longValue();
            Long count = ((Number) item.get("count")).longValue();
            map.put(status, count);
        }
        return map;
    }

    /**
     * 获取设备类型分布
     * 
     * @return 设备类型分布
     */
    @Override
    public java.util.Map<Long, Long> getTypeDistribution()
    {
        java.util.List<java.util.Map<String, Object>> list = waterMeterDeviceMapper.getTypeDistribution();
        java.util.Map<Long, Long> map = new java.util.HashMap<>();
        for (java.util.Map<String, Object> item : list)
        {
            Long deviceType = ((Number) item.get("device_type")).longValue();
            Long count = ((Number) item.get("count")).longValue();
            map.put(deviceType, count);
        }
        return map;
    }

    /**
     * 获取用水量统计
     * 
     * @return 用水量统计
     */
    @Override
    public java.util.Map<String, java.math.BigDecimal> getWaterUsage()
    {
        java.util.Map<String, Object> result = waterMeterDeviceMapper.getWaterUsage();
        java.util.Map<String, java.math.BigDecimal> map = new java.util.HashMap<>();
        if (result != null)
        {
            java.math.BigDecimal totalUsage = (java.math.BigDecimal) result.get("totalUsage");
            java.math.BigDecimal averageUsage = (java.math.BigDecimal) result.get("averageUsage");
            map.put("totalUsage", totalUsage != null ? totalUsage : java.math.BigDecimal.ZERO);
            map.put("averageUsage", averageUsage != null ? averageUsage : java.math.BigDecimal.ZERO);
        }
        return map;
    }
}

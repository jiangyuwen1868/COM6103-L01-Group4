package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.WaterMeterDevice;

/**
 * 智能水设备信息Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-17
 */
public interface WaterMeterDeviceMapper 
{
    /**
     * 查询智能水设备信息
     * 
     * @param id 智能水设备信息ID
     * @return 智能水设备信息
     */
    public WaterMeterDevice selectWaterMeterDeviceById(String id);

    /**
     * 查询智能水设备信息列表
     * 
     * @param waterMeterDevice 智能水设备信息
     * @return 智能水设备信息集合
     */
    public List<WaterMeterDevice> selectWaterMeterDeviceList(WaterMeterDevice waterMeterDevice);

    /**
     * 新增智能水设备信息
     * 
     * @param waterMeterDevice 智能水设备信息
     * @return 结果
     */
    public int insertWaterMeterDevice(WaterMeterDevice waterMeterDevice);

    /**
     * 修改智能水设备信息
     * 
     * @param waterMeterDevice 智能水设备信息
     * @return 结果
     */
    public int updateWaterMeterDevice(WaterMeterDevice waterMeterDevice);

    /**
     * 删除智能水设备信息
     * 
     * @param id 智能水设备信息ID
     * @return 结果
     */
    public int deleteWaterMeterDeviceById(String id);

    /**
     * 批量删除智能水设备信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWaterMeterDeviceByIds(String[] ids);

    /**
     * 获取设备总数
     * 
     * @return 设备总数
     */
    public long getTotalDevices();

    /**
     * 获取正常运行设备数
     * 
     * @return 正常运行设备数
     */
    public long getNormalDevices();

    /**
     * 获取故障设备数
     * 
     * @return 故障设备数
     */
    public long getFaultyDevices();

    /**
     * 获取未激活设备数
     * 
     * @return 未激活设备数
     */
    public long getUnactivatedDevices();

    /**
     * 获取设备状态分布
     * 
     * @return 设备状态分布
     */
    public java.util.List<java.util.Map<String, Object>> getStatusDistribution();

    /**
     * 获取设备类型分布
     * 
     * @return 设备类型分布
     */
    public java.util.List<java.util.Map<String, Object>> getTypeDistribution();

    /**
     * 获取用水量统计
     * 
     * @return 用水量统计
     */
    public java.util.Map<String, Object> getWaterUsage();
}

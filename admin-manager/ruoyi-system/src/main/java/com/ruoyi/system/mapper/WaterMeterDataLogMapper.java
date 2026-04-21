package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.WaterMeterDataLog;

/**
 * 智能水数据流水Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-17
 */
public interface WaterMeterDataLogMapper 
{
    /**
     * 查询智能水数据流水
     * 
     * @param id 智能水数据流水ID
     * @return 智能水数据流水
     */
    public WaterMeterDataLog selectWaterMeterDataLogById(String id);

    /**
     * 查询智能水数据流水列表
     * 
     * @param waterMeterDataLog 智能水数据流水
     * @return 智能水数据流水集合
     */
    public List<WaterMeterDataLog> selectWaterMeterDataLogList(WaterMeterDataLog waterMeterDataLog);

    /**
     * 新增智能水数据流水
     * 
     * @param waterMeterDataLog 智能水数据流水
     * @return 结果
     */
    public int insertWaterMeterDataLog(WaterMeterDataLog waterMeterDataLog);

    /**
     * 修改智能水数据流水
     * 
     * @param waterMeterDataLog 智能水数据流水
     * @return 结果
     */
    public int updateWaterMeterDataLog(WaterMeterDataLog waterMeterDataLog);

    /**
     * 删除智能水数据流水
     * 
     * @param id 智能水数据流水ID
     * @return 结果
     */
    public int deleteWaterMeterDataLogById(String id);

    /**
     * 批量删除智能水数据流水
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWaterMeterDataLogByIds(String[] ids);
}

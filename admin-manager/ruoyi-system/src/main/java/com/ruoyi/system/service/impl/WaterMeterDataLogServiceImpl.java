package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.WaterMeterDataLogMapper;
import com.ruoyi.system.domain.WaterMeterDataLog;
import com.ruoyi.system.service.IWaterMeterDataLogService;
import com.ruoyi.common.core.text.Convert;

/**
 * 智能水数据流水Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-17
 */
@Service
public class WaterMeterDataLogServiceImpl implements IWaterMeterDataLogService 
{
    @Autowired
    private WaterMeterDataLogMapper waterMeterDataLogMapper;

    /**
     * 查询智能水数据流水
     * 
     * @param id 智能水数据流水ID
     * @return 智能水数据流水
     */
    @Override
    public WaterMeterDataLog selectWaterMeterDataLogById(String id)
    {
        return waterMeterDataLogMapper.selectWaterMeterDataLogById(id);
    }

    /**
     * 查询智能水数据流水列表
     * 
     * @param waterMeterDataLog 智能水数据流水
     * @return 智能水数据流水
     */
    @Override
    public List<WaterMeterDataLog> selectWaterMeterDataLogList(WaterMeterDataLog waterMeterDataLog)
    {
        return waterMeterDataLogMapper.selectWaterMeterDataLogList(waterMeterDataLog);
    }

    /**
     * 新增智能水数据流水
     * 
     * @param waterMeterDataLog 智能水数据流水
     * @return 结果
     */
    @Override
    public int insertWaterMeterDataLog(WaterMeterDataLog waterMeterDataLog)
    {
        waterMeterDataLog.setCreateTime(DateUtils.getNowDate());
        return waterMeterDataLogMapper.insertWaterMeterDataLog(waterMeterDataLog);
    }

    /**
     * 修改智能水数据流水
     * 
     * @param waterMeterDataLog 智能水数据流水
     * @return 结果
     */
    @Override
    public int updateWaterMeterDataLog(WaterMeterDataLog waterMeterDataLog)
    {
        return waterMeterDataLogMapper.updateWaterMeterDataLog(waterMeterDataLog);
    }

    /**
     * 删除智能水数据流水对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteWaterMeterDataLogByIds(String ids)
    {
        return waterMeterDataLogMapper.deleteWaterMeterDataLogByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除智能水数据流水信息
     * 
     * @param id 智能水数据流水ID
     * @return 结果
     */
    @Override
    public int deleteWaterMeterDataLogById(String id)
    {
        return waterMeterDataLogMapper.deleteWaterMeterDataLogById(id);
    }
}

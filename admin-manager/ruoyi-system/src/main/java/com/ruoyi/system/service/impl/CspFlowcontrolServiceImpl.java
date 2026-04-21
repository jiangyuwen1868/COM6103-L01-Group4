package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CspFlowcontrolMapper;
import com.ruoyi.system.domain.CspFlowcontrol;
import com.ruoyi.system.service.ICspFlowcontrolService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
@Service
public class CspFlowcontrolServiceImpl implements ICspFlowcontrolService 
{
    @Autowired
    private CspFlowcontrolMapper cspFlowcontrolMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param cspFlowcontrol 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public CspFlowcontrol selectCspFlowcontrolById(CspFlowcontrol cspFlowcontrol)
    {
        return cspFlowcontrolMapper.selectCspFlowcontrolById(cspFlowcontrol);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cspFlowcontrol 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CspFlowcontrol> selectCspFlowcontrolList(CspFlowcontrol cspFlowcontrol)
    {
        return cspFlowcontrolMapper.selectCspFlowcontrolList(cspFlowcontrol);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param cspFlowcontrol 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCspFlowcontrol(CspFlowcontrol cspFlowcontrol)
    {
        return cspFlowcontrolMapper.insertCspFlowcontrol(cspFlowcontrol);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param cspFlowcontrol 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCspFlowcontrol(CspFlowcontrol cspFlowcontrol)
    {
        return cspFlowcontrolMapper.updateCspFlowcontrol(cspFlowcontrol);
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param cspFlowcontrol 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int deleteCspFlowcontrolById(CspFlowcontrol cspFlowcontrol)
    {
        return cspFlowcontrolMapper.deleteCspFlowcontrolById(cspFlowcontrol);
    }
}

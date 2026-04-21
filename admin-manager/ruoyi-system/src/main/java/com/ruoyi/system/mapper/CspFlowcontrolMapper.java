package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CspFlowcontrol;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
public interface CspFlowcontrolMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param cspFlowcontrol 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    public CspFlowcontrol selectCspFlowcontrolById(CspFlowcontrol cspFlowcontrol);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cspFlowcontrol 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CspFlowcontrol> selectCspFlowcontrolList(CspFlowcontrol cspFlowcontrol);

    /**
     * 新增【请填写功能名称】
     * 
     * @param cspFlowcontrol 【请填写功能名称】
     * @return 结果
     */
    public int insertCspFlowcontrol(CspFlowcontrol cspFlowcontrol);

    /**
     * 修改【请填写功能名称】
     * 
     * @param cspFlowcontrol 【请填写功能名称】
     * @return 结果
     */
    public int updateCspFlowcontrol(CspFlowcontrol cspFlowcontrol);

    /**
     * 删除【请填写功能名称】
     * 
     * @param cspFlowcontrol 【请填写功能名称】
     * @return 结果
     */
    public int deleteCspFlowcontrolById(CspFlowcontrol cspFlowcontrol);
}

package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CspResourceGroupMapper;
import com.ruoyi.system.domain.CspResourceGroup;
import com.ruoyi.system.service.ICspResourceGroupService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
@Service
public class CspResourceGroupServiceImpl implements ICspResourceGroupService 
{
    @Autowired
    private CspResourceGroupMapper cspResourceGroupMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param groupid 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public CspResourceGroup selectCspResourceGroupById(String groupid)
    {
        return cspResourceGroupMapper.selectCspResourceGroupById(groupid);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cspResourceGroup 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CspResourceGroup> selectCspResourceGroupList(CspResourceGroup cspResourceGroup)
    {
        return cspResourceGroupMapper.selectCspResourceGroupList(cspResourceGroup);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param cspResourceGroup 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCspResourceGroup(CspResourceGroup cspResourceGroup)
    {
        return cspResourceGroupMapper.insertCspResourceGroup(cspResourceGroup);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param cspResourceGroup 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCspResourceGroup(CspResourceGroup cspResourceGroup)
    {
        return cspResourceGroupMapper.updateCspResourceGroup(cspResourceGroup);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCspResourceGroupByIds(String ids)
    {
        return cspResourceGroupMapper.deleteCspResourceGroupByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param groupid 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteCspResourceGroupById(String groupid)
    {
        return cspResourceGroupMapper.deleteCspResourceGroupById(groupid);
    }
}

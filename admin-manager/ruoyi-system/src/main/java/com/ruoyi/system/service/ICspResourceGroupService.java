package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.CspResourceGroup;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
public interface ICspResourceGroupService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param groupid 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public CspResourceGroup selectCspResourceGroupById(String groupid);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cspResourceGroup 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CspResourceGroup> selectCspResourceGroupList(CspResourceGroup cspResourceGroup);

    /**
     * 新增【请填写功能名称】
     * 
     * @param cspResourceGroup 【请填写功能名称】
     * @return 结果
     */
    public int insertCspResourceGroup(CspResourceGroup cspResourceGroup);

    /**
     * 修改【请填写功能名称】
     * 
     * @param cspResourceGroup 【请填写功能名称】
     * @return 结果
     */
    public int updateCspResourceGroup(CspResourceGroup cspResourceGroup);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCspResourceGroupByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param groupid 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteCspResourceGroupById(String groupid);
}

package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CspSysDeploy;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
public interface CspSysDeployMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param groupid 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public CspSysDeploy selectCspSysDeployById(String groupid);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cspSysDeploy 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CspSysDeploy> selectCspSysDeployList(CspSysDeploy cspSysDeploy);

    /**
     * 新增【请填写功能名称】
     * 
     * @param cspSysDeploy 【请填写功能名称】
     * @return 结果
     */
    public int insertCspSysDeploy(CspSysDeploy cspSysDeploy);

    /**
     * 修改【请填写功能名称】
     * 
     * @param cspSysDeploy 【请填写功能名称】
     * @return 结果
     */
    public int updateCspSysDeploy(CspSysDeploy cspSysDeploy);

    /**
     * 删除【请填写功能名称】
     * 
     * @param groupid 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteCspSysDeployById(String groupid);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param groupids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCspSysDeployByIds(String[] groupids);
}

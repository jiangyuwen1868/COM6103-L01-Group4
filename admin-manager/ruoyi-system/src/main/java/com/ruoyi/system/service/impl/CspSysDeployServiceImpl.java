package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CspSysDeployMapper;
import com.ruoyi.system.domain.CspSysDeploy;
import com.ruoyi.system.service.ICspSysDeployService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
@Service
public class CspSysDeployServiceImpl implements ICspSysDeployService 
{
    @Autowired
    private CspSysDeployMapper cspSysDeployMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param groupid 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public CspSysDeploy selectCspSysDeployById(String groupid)
    {
        return cspSysDeployMapper.selectCspSysDeployById(groupid);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cspSysDeploy 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CspSysDeploy> selectCspSysDeployList(CspSysDeploy cspSysDeploy)
    {
        return cspSysDeployMapper.selectCspSysDeployList(cspSysDeploy);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param cspSysDeploy 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCspSysDeploy(CspSysDeploy cspSysDeploy)
    {
        return cspSysDeployMapper.insertCspSysDeploy(cspSysDeploy);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param cspSysDeploy 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCspSysDeploy(CspSysDeploy cspSysDeploy)
    {
        return cspSysDeployMapper.updateCspSysDeploy(cspSysDeploy);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCspSysDeployByIds(String ids)
    {
        return cspSysDeployMapper.deleteCspSysDeployByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param groupid 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteCspSysDeployById(String groupid)
    {
        return cspSysDeployMapper.deleteCspSysDeployById(groupid);
    }
}

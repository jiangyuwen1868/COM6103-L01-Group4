package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CspSrvauthFlowcontrolMapper;
import com.ruoyi.system.domain.CspSrvauthFlowcontrol;
import com.ruoyi.system.service.ICspSrvauthFlowcontrolService;
import com.ruoyi.common.core.text.Convert;

/**
 * 应用授权流控Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
@Service
public class CspSrvauthFlowcontrolServiceImpl implements ICspSrvauthFlowcontrolService 
{
    @Autowired
    private CspSrvauthFlowcontrolMapper cspSrvauthFlowcontrolMapper;

    /**
     * 查询应用授权流控
     * 
     * @param id 应用授权流控ID
     * @return 应用授权流控
     */
    @Override
    public CspSrvauthFlowcontrol selectCspSrvauthFlowcontrolById(Long id)
    {
        return cspSrvauthFlowcontrolMapper.selectCspSrvauthFlowcontrolById(id);
    }

    /**
     * 查询应用授权流控列表
     * 
     * @param cspSrvauthFlowcontrol 应用授权流控
     * @return 应用授权流控
     */
    @Override
    public List<CspSrvauthFlowcontrol> selectCspSrvauthFlowcontrolList(CspSrvauthFlowcontrol cspSrvauthFlowcontrol)
    {
        return cspSrvauthFlowcontrolMapper.selectCspSrvauthFlowcontrolList(cspSrvauthFlowcontrol);
    }

    /**
     * 新增应用授权流控
     * 
     * @param cspSrvauthFlowcontrol 应用授权流控
     * @return 结果
     */
    @Override
    public int insertCspSrvauthFlowcontrol(CspSrvauthFlowcontrol cspSrvauthFlowcontrol)
    {
        return cspSrvauthFlowcontrolMapper.insertCspSrvauthFlowcontrol(cspSrvauthFlowcontrol);
    }

    /**
     * 修改应用授权流控
     * 
     * @param cspSrvauthFlowcontrol 应用授权流控
     * @return 结果
     */
    @Override
    public int updateCspSrvauthFlowcontrol(CspSrvauthFlowcontrol cspSrvauthFlowcontrol)
    {
        return cspSrvauthFlowcontrolMapper.updateCspSrvauthFlowcontrol(cspSrvauthFlowcontrol);
    }

    /**
     * 删除应用授权流控对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCspSrvauthFlowcontrolByIds(String ids)
    {
        return cspSrvauthFlowcontrolMapper.deleteCspSrvauthFlowcontrolByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除应用授权流控信息
     * 
     * @param id 应用授权流控ID
     * @return 结果
     */
    @Override
    public int deleteCspSrvauthFlowcontrolById(Long id)
    {
        return cspSrvauthFlowcontrolMapper.deleteCspSrvauthFlowcontrolById(id);
    }
}

package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.CspSrvauthFlowcontrol;

/**
 * 应用授权流控Service接口
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
public interface ICspSrvauthFlowcontrolService 
{
    /**
     * 查询应用授权流控
     * 
     * @param id 应用授权流控ID
     * @return 应用授权流控
     */
    public CspSrvauthFlowcontrol selectCspSrvauthFlowcontrolById(Long id);

    /**
     * 查询应用授权流控列表
     * 
     * @param cspSrvauthFlowcontrol 应用授权流控
     * @return 应用授权流控集合
     */
    public List<CspSrvauthFlowcontrol> selectCspSrvauthFlowcontrolList(CspSrvauthFlowcontrol cspSrvauthFlowcontrol);

    /**
     * 新增应用授权流控
     * 
     * @param cspSrvauthFlowcontrol 应用授权流控
     * @return 结果
     */
    public int insertCspSrvauthFlowcontrol(CspSrvauthFlowcontrol cspSrvauthFlowcontrol);

    /**
     * 修改应用授权流控
     * 
     * @param cspSrvauthFlowcontrol 应用授权流控
     * @return 结果
     */
    public int updateCspSrvauthFlowcontrol(CspSrvauthFlowcontrol cspSrvauthFlowcontrol);

    /**
     * 批量删除应用授权流控
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCspSrvauthFlowcontrolByIds(String ids);

    /**
     * 删除应用授权流控信息
     * 
     * @param id 应用授权流控ID
     * @return 结果
     */
    public int deleteCspSrvauthFlowcontrolById(Long id);
}

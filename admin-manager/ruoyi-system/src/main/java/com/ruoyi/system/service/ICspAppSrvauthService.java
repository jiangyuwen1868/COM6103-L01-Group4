package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.CspAppSrvauth;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
public interface ICspAppSrvauthService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param appid 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public CspAppSrvauth selectCspAppSrvauthById(String appid);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cspAppSrvauth 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CspAppSrvauth> selectCspAppSrvauthList(CspAppSrvauth cspAppSrvauth);

    /**
     * 新增【请填写功能名称】
     * 
     * @param cspAppSrvauth 【请填写功能名称】
     * @return 结果
     */
    public int insertCspAppSrvauth(CspAppSrvauth cspAppSrvauth);

    /**
     * 修改【请填写功能名称】
     * 
     * @param cspAppSrvauth 【请填写功能名称】
     * @return 结果
     */
    public int updateCspAppSrvauth(CspAppSrvauth cspAppSrvauth);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCspAppSrvauthByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param appid 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteCspAppSrvauthById(String appid);
}

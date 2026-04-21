package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.CspAppinfo;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
public interface ICspAppinfoService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param appid 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public CspAppinfo selectCspAppinfoById(String appid);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cspAppinfo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CspAppinfo> selectCspAppinfoList(CspAppinfo cspAppinfo);

    /**
     * 新增【请填写功能名称】
     * 
     * @param cspAppinfo 【请填写功能名称】
     * @return 结果
     */
    public int insertCspAppinfo(CspAppinfo cspAppinfo);

    /**
     * 修改【请填写功能名称】
     * 
     * @param cspAppinfo 【请填写功能名称】
     * @return 结果
     */
    public int updateCspAppinfo(CspAppinfo cspAppinfo);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCspAppinfoByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param appid 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteCspAppinfoById(String appid);
}

package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.system.domain.CspErrorinfo;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
public interface ICspErrorinfoService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param errorcode 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public CspErrorinfo selectCspErrorinfoById(String errorcode);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cspErrorinfo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CspErrorinfo> selectCspErrorinfoList(CspErrorinfo cspErrorinfo);

    /**
     * 新增【请填写功能名称】
     * 
     * @param cspErrorinfo 【请填写功能名称】
     * @return 结果
     */
    public int insertCspErrorinfo(CspErrorinfo cspErrorinfo);

    /**
     * 修改【请填写功能名称】
     * 
     * @param cspErrorinfo 【请填写功能名称】
     * @return 结果
     */
    public int updateCspErrorinfo(CspErrorinfo cspErrorinfo);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCspErrorinfoByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param errorcode 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteCspErrorinfoById(String errorcode);
}

package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CspErrorinfoMapper;
import com.ruoyi.system.domain.CspErrorinfo;
import com.ruoyi.system.service.ICspErrorinfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
@Service
public class CspErrorinfoServiceImpl implements ICspErrorinfoService 
{
    @Autowired
    private CspErrorinfoMapper cspErrorinfoMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param errorcode 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public CspErrorinfo selectCspErrorinfoById(String errorcode)
    {
        return cspErrorinfoMapper.selectCspErrorinfoById(errorcode);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cspErrorinfo 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CspErrorinfo> selectCspErrorinfoList(CspErrorinfo cspErrorinfo)
    {
        return cspErrorinfoMapper.selectCspErrorinfoList(cspErrorinfo);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param cspErrorinfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCspErrorinfo(CspErrorinfo cspErrorinfo)
    {
        return cspErrorinfoMapper.insertCspErrorinfo(cspErrorinfo);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param cspErrorinfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCspErrorinfo(CspErrorinfo cspErrorinfo)
    {
        return cspErrorinfoMapper.updateCspErrorinfo(cspErrorinfo);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCspErrorinfoByIds(String ids)
    {
        return cspErrorinfoMapper.deleteCspErrorinfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param errorcode 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteCspErrorinfoById(String errorcode)
    {
        return cspErrorinfoMapper.deleteCspErrorinfoById(errorcode);
    }
}

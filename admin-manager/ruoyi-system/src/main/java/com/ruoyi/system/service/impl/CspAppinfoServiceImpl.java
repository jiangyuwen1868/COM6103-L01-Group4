package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CspAppinfoMapper;
import com.ruoyi.system.domain.CspAppinfo;
import com.ruoyi.system.service.ICspAppinfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
@Service
public class CspAppinfoServiceImpl implements ICspAppinfoService 
{
    @Autowired
    private CspAppinfoMapper cspAppinfoMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param appid 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public CspAppinfo selectCspAppinfoById(String appid)
    {
        return cspAppinfoMapper.selectCspAppinfoById(appid);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cspAppinfo 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CspAppinfo> selectCspAppinfoList(CspAppinfo cspAppinfo)
    {
        return cspAppinfoMapper.selectCspAppinfoList(cspAppinfo);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param cspAppinfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCspAppinfo(CspAppinfo cspAppinfo)
    {
        return cspAppinfoMapper.insertCspAppinfo(cspAppinfo);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param cspAppinfo 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCspAppinfo(CspAppinfo cspAppinfo)
    {
        return cspAppinfoMapper.updateCspAppinfo(cspAppinfo);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCspAppinfoByIds(String ids)
    {
        return cspAppinfoMapper.deleteCspAppinfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param appid 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteCspAppinfoById(String appid)
    {
        return cspAppinfoMapper.deleteCspAppinfoById(appid);
    }
}

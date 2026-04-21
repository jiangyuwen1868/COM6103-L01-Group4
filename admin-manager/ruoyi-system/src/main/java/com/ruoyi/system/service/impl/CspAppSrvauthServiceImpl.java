package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CspAppSrvauthMapper;
import com.ruoyi.system.domain.CspAppSrvauth;
import com.ruoyi.system.service.ICspAppSrvauthService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
@Service
public class CspAppSrvauthServiceImpl implements ICspAppSrvauthService 
{
    @Autowired
    private CspAppSrvauthMapper cspAppSrvauthMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param appid 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public CspAppSrvauth selectCspAppSrvauthById(String appid)
    {
        return cspAppSrvauthMapper.selectCspAppSrvauthById(appid);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cspAppSrvauth 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CspAppSrvauth> selectCspAppSrvauthList(CspAppSrvauth cspAppSrvauth)
    {
        return cspAppSrvauthMapper.selectCspAppSrvauthList(cspAppSrvauth);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param cspAppSrvauth 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCspAppSrvauth(CspAppSrvauth cspAppSrvauth)
    {
        return cspAppSrvauthMapper.insertCspAppSrvauth(cspAppSrvauth);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param cspAppSrvauth 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCspAppSrvauth(CspAppSrvauth cspAppSrvauth)
    {
        return cspAppSrvauthMapper.updateCspAppSrvauth(cspAppSrvauth);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCspAppSrvauthByIds(String ids)
    {
        return cspAppSrvauthMapper.deleteCspAppSrvauthByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param appid 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteCspAppSrvauthById(String appid)
    {
        return cspAppSrvauthMapper.deleteCspAppSrvauthById(appid);
    }
}

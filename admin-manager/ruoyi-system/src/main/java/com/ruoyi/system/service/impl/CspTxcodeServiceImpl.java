package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.CspTxcodeMapper;
import com.ruoyi.system.domain.CspTxcode;
import com.ruoyi.system.service.ICspTxcodeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
@Service
public class CspTxcodeServiceImpl implements ICspTxcodeService 
{
    @Autowired
    private CspTxcodeMapper cspTxcodeMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param txcode 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    @Override
    public CspTxcode selectCspTxcodeById(String txcode)
    {
        return cspTxcodeMapper.selectCspTxcodeById(txcode);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cspTxcode 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<CspTxcode> selectCspTxcodeList(CspTxcode cspTxcode)
    {
        return cspTxcodeMapper.selectCspTxcodeList(cspTxcode);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param cspTxcode 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertCspTxcode(CspTxcode cspTxcode)
    {
        return cspTxcodeMapper.insertCspTxcode(cspTxcode);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param cspTxcode 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateCspTxcode(CspTxcode cspTxcode)
    {
        return cspTxcodeMapper.updateCspTxcode(cspTxcode);
    }

    /**
     * 删除【请填写功能名称】对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCspTxcodeByIds(String ids)
    {
        return cspTxcodeMapper.deleteCspTxcodeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param txcode 【请填写功能名称】ID
     * @return 结果
     */
    @Override
    public int deleteCspTxcodeById(String txcode)
    {
        return cspTxcodeMapper.deleteCspTxcodeById(txcode);
    }
}

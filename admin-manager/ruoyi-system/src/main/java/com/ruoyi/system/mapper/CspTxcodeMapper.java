package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.system.domain.CspTxcode;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
public interface CspTxcodeMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param txcode 【请填写功能名称】ID
     * @return 【请填写功能名称】
     */
    public CspTxcode selectCspTxcodeById(String txcode);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param cspTxcode 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<CspTxcode> selectCspTxcodeList(CspTxcode cspTxcode);

    /**
     * 新增【请填写功能名称】
     * 
     * @param cspTxcode 【请填写功能名称】
     * @return 结果
     */
    public int insertCspTxcode(CspTxcode cspTxcode);

    /**
     * 修改【请填写功能名称】
     * 
     * @param cspTxcode 【请填写功能名称】
     * @return 结果
     */
    public int updateCspTxcode(CspTxcode cspTxcode);

    /**
     * 删除【请填写功能名称】
     * 
     * @param txcode 【请填写功能名称】ID
     * @return 结果
     */
    public int deleteCspTxcodeById(String txcode);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param txcodes 需要删除的数据ID
     * @return 结果
     */
    public int deleteCspTxcodeByIds(String[] txcodes);
}

package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 csp_flowcontrol
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
public class CspFlowcontrol extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String appid;

    /** $column.columnComment */
    private String txcode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long tpscount;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String opswitch;

    public void setAppid(String appid) 
    {
        this.appid = appid;
    }

    public String getAppid() 
    {
        return appid;
    }
    public void setTxcode(String txcode) 
    {
        this.txcode = txcode;
    }

    public String getTxcode() 
    {
        return txcode;
    }
    public void setTpscount(Long tpscount) 
    {
        this.tpscount = tpscount;
    }

    public Long getTpscount() 
    {
        return tpscount;
    }
    public void setOpswitch(String opswitch) 
    {
        this.opswitch = opswitch;
    }

    public String getOpswitch() 
    {
        return opswitch;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("appid", getAppid())
            .append("txcode", getTxcode())
            .append("tpscount", getTpscount())
            .append("opswitch", getOpswitch())
            .toString();
    }
}

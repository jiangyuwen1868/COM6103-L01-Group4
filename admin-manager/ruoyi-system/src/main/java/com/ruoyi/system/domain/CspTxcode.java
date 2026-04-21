package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 csp_txcode
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
public class CspTxcode extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String txcode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String txname;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String txtype;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String isoutbound;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String ptxcode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String groupid;

    public void setTxcode(String txcode) 
    {
        this.txcode = txcode;
    }

    public String getTxcode() 
    {
        return txcode;
    }
    public void setTxname(String txname) 
    {
        this.txname = txname;
    }

    public String getTxname() 
    {
        return txname;
    }
    public void setTxtype(String txtype) 
    {
        this.txtype = txtype;
    }

    public String getTxtype() 
    {
        return txtype;
    }
    public void setIsoutbound(String isoutbound) 
    {
        this.isoutbound = isoutbound;
    }

    public String getIsoutbound() 
    {
        return isoutbound;
    }
    public void setPtxcode(String ptxcode) 
    {
        this.ptxcode = ptxcode;
    }

    public String getPtxcode() 
    {
        return ptxcode;
    }
    public void setGroupid(String groupid) 
    {
        this.groupid = groupid;
    }

    public String getGroupid() 
    {
        return groupid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("txcode", getTxcode())
            .append("txname", getTxname())
            .append("txtype", getTxtype())
            .append("isoutbound", getIsoutbound())
            .append("ptxcode", getPtxcode())
            .append("groupid", getGroupid())
            .append("remark", getRemark())
            .toString();
    }
}

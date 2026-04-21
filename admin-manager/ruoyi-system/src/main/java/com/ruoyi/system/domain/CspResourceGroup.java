package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 csp_resource_group
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
public class CspResourceGroup extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String groupid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String reqmethod;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String contenttype;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long conntimeout;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long sotimeout;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long connmaxsize;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long heartinteval;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String lbstrategy;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String isbalance;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String isuseproxyauthor;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String proxyip;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private Long proxyport;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String proxyusername;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String proxyuserpass;

    public void setGroupid(String groupid) 
    {
        this.groupid = groupid;
    }

    public String getGroupid() 
    {
        return groupid;
    }
    public void setReqmethod(String reqmethod) 
    {
        this.reqmethod = reqmethod;
    }

    public String getReqmethod() 
    {
        return reqmethod;
    }
    public void setContenttype(String contenttype) 
    {
        this.contenttype = contenttype;
    }

    public String getContenttype() 
    {
        return contenttype;
    }
    public void setConntimeout(Long conntimeout) 
    {
        this.conntimeout = conntimeout;
    }

    public Long getConntimeout() 
    {
        return conntimeout;
    }
    public void setSotimeout(Long sotimeout) 
    {
        this.sotimeout = sotimeout;
    }

    public Long getSotimeout() 
    {
        return sotimeout;
    }
    public void setConnmaxsize(Long connmaxsize) 
    {
        this.connmaxsize = connmaxsize;
    }

    public Long getConnmaxsize() 
    {
        return connmaxsize;
    }
    public void setHeartinteval(Long heartinteval) 
    {
        this.heartinteval = heartinteval;
    }

    public Long getHeartinteval() 
    {
        return heartinteval;
    }
    public void setLbstrategy(String lbstrategy) 
    {
        this.lbstrategy = lbstrategy;
    }

    public String getLbstrategy() 
    {
        return lbstrategy;
    }
    public void setIsbalance(String isbalance) 
    {
        this.isbalance = isbalance;
    }

    public String getIsbalance() 
    {
        return isbalance;
    }
    public void setIsuseproxyauthor(String isuseproxyauthor) 
    {
        this.isuseproxyauthor = isuseproxyauthor;
    }

    public String getIsuseproxyauthor() 
    {
        return isuseproxyauthor;
    }
    public void setProxyip(String proxyip) 
    {
        this.proxyip = proxyip;
    }

    public String getProxyip() 
    {
        return proxyip;
    }
    public void setProxyport(Long proxyport) 
    {
        this.proxyport = proxyport;
    }

    public Long getProxyport() 
    {
        return proxyport;
    }
    public void setProxyusername(String proxyusername) 
    {
        this.proxyusername = proxyusername;
    }

    public String getProxyusername() 
    {
        return proxyusername;
    }
    public void setProxyuserpass(String proxyuserpass) 
    {
        this.proxyuserpass = proxyuserpass;
    }

    public String getProxyuserpass() 
    {
        return proxyuserpass;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("groupid", getGroupid())
            .append("reqmethod", getReqmethod())
            .append("contenttype", getContenttype())
            .append("conntimeout", getConntimeout())
            .append("sotimeout", getSotimeout())
            .append("connmaxsize", getConnmaxsize())
            .append("heartinteval", getHeartinteval())
            .append("lbstrategy", getLbstrategy())
            .append("isbalance", getIsbalance())
            .append("isuseproxyauthor", getIsuseproxyauthor())
            .append("proxyip", getProxyip())
            .append("proxyport", getProxyport())
            .append("proxyusername", getProxyusername())
            .append("proxyuserpass", getProxyuserpass())
            .append("remark", getRemark())
            .toString();
    }
}

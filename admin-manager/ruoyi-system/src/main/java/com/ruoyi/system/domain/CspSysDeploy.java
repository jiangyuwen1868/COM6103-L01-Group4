package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 csp_sys_deploy
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
public class CspSysDeploy extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private String groupid;

    /** $column.columnComment */
    private String deployid;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String hostname;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String username;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String deploycode;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String appcode;

    /** ip长度或域名长度 */
    @Excel(name = "ip长度或域名长度")
    private String sysip;

    /** $column.columnComment */
    @Excel(name = "ip长度或域名长度")
    private Long sysport;

    /** $column.columnComment */
    @Excel(name = "ip长度或域名长度")
    private String servaddr;

    /** $column.columnComment */
    @Excel(name = "ip长度或域名长度")
    private String isuseful;

    /** $column.columnComment */
    @Excel(name = "ip长度或域名长度")
    private Long weight;

    /** 所属中心编号 */
    @Excel(name = "所属中心编号")
    private String centerid;

    public void setGroupid(String groupid) 
    {
        this.groupid = groupid;
    }

    public String getGroupid() 
    {
        return groupid;
    }
    public void setDeployid(String deployid) 
    {
        this.deployid = deployid;
    }

    public String getDeployid() 
    {
        return deployid;
    }
    public void setHostname(String hostname) 
    {
        this.hostname = hostname;
    }

    public String getHostname() 
    {
        return hostname;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setDeploycode(String deploycode) 
    {
        this.deploycode = deploycode;
    }

    public String getDeploycode() 
    {
        return deploycode;
    }
    public void setAppcode(String appcode) 
    {
        this.appcode = appcode;
    }

    public String getAppcode() 
    {
        return appcode;
    }
    public void setSysip(String sysip) 
    {
        this.sysip = sysip;
    }

    public String getSysip() 
    {
        return sysip;
    }
    public void setSysport(Long sysport) 
    {
        this.sysport = sysport;
    }

    public Long getSysport() 
    {
        return sysport;
    }
    public void setServaddr(String servaddr) 
    {
        this.servaddr = servaddr;
    }

    public String getServaddr() 
    {
        return servaddr;
    }
    public void setIsuseful(String isuseful) 
    {
        this.isuseful = isuseful;
    }

    public String getIsuseful() 
    {
        return isuseful;
    }
    public void setWeight(Long weight) 
    {
        this.weight = weight;
    }

    public Long getWeight() 
    {
        return weight;
    }
    public void setCenterid(String centerid) 
    {
        this.centerid = centerid;
    }

    public String getCenterid() 
    {
        return centerid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("groupid", getGroupid())
            .append("deployid", getDeployid())
            .append("hostname", getHostname())
            .append("username", getUsername())
            .append("deploycode", getDeploycode())
            .append("appcode", getAppcode())
            .append("sysip", getSysip())
            .append("sysport", getSysport())
            .append("servaddr", getServaddr())
            .append("isuseful", getIsuseful())
            .append("weight", getWeight())
            .append("remark", getRemark())
            .append("centerid", getCenterid())
            .toString();
    }
}

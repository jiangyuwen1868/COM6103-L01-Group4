package com.ruoyi.system.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 csp_appinfo
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
public class CspAppinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 应用编号 */
    private String appid;

    /** 应用名称 */
    @Excel(name = "应用名称")
    private String appname;

    /** 应用密钥 */
    @Excel(name = "应用密钥")
    private String appsecret;

    /** $column.columnComment */
    @Excel(name = "应用密钥")
    private String tenantid;

    /** 所属部门 */
    @Excel(name = "所属部门")
    private Long deptId;

    /** 所属用户 */
    @Excel(name = "所属用户")
    private Long userId;

    /** 认证方式 */
    @Excel(name = "认证方式")
    private String authtype;

    /** 应用状态 */
    @Excel(name = "应用状态")
    private String appstatus;

    /** 应用所属加密机分组 */
    @Excel(name = "应用所属加密机分组")
    private String hsmgroups;

    /** 密码服务分组 */
    @Excel(name = "密码服务分组")
    private String srvgroups;

    /** 应用创建者 */
    @Excel(name = "应用创建者")
    private String creator;

    /** 应用创建时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "应用创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creattime;

    /** 应用说明 */
    @Excel(name = "应用说明")
    private String appdesc;

    /** 密评级别 */
    @Excel(name = "密评级别")
    private String secretrating;

    /** 密评有效期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "密评有效期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date secretdate;

    /** 应用服务私钥 */
    @Excel(name = "应用服务私钥")
    private String appVk;

    /** 应用服务公钥 */
    @Excel(name = "应用服务公钥")
    private String appPk;

    public void setAppid(String appid) 
    {
        this.appid = appid;
    }

    public String getAppid() 
    {
        return appid;
    }
    public void setAppname(String appname) 
    {
        this.appname = appname;
    }

    public String getAppname() 
    {
        return appname;
    }
    public void setAppsecret(String appsecret) 
    {
        this.appsecret = appsecret;
    }

    public String getAppsecret() 
    {
        return appsecret;
    }
    public void setTenantid(String tenantid) 
    {
        this.tenantid = tenantid;
    }

    public String getTenantid() 
    {
        return tenantid;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setAuthtype(String authtype) 
    {
        this.authtype = authtype;
    }

    public String getAuthtype() 
    {
        return authtype;
    }
    public void setAppstatus(String appstatus) 
    {
        this.appstatus = appstatus;
    }

    public String getAppstatus() 
    {
        return appstatus;
    }
    public void setHsmgroups(String hsmgroups) 
    {
        this.hsmgroups = hsmgroups;
    }

    public String getHsmgroups() 
    {
        return hsmgroups;
    }
    public void setSrvgroups(String srvgroups) 
    {
        this.srvgroups = srvgroups;
    }

    public String getSrvgroups() 
    {
        return srvgroups;
    }
    public void setCreator(String creator) 
    {
        this.creator = creator;
    }

    public String getCreator() 
    {
        return creator;
    }
    public void setCreattime(Date creattime) 
    {
        this.creattime = creattime;
    }

    public Date getCreattime() 
    {
        return creattime;
    }
    public void setAppdesc(String appdesc) 
    {
        this.appdesc = appdesc;
    }

    public String getAppdesc() 
    {
        return appdesc;
    }
    public void setSecretrating(String secretrating) 
    {
        this.secretrating = secretrating;
    }

    public String getSecretrating() 
    {
        return secretrating;
    }
    public void setSecretdate(Date secretdate) 
    {
        this.secretdate = secretdate;
    }

    public Date getSecretdate() 
    {
        return secretdate;
    }
    public void setAppVk(String appVk) 
    {
        this.appVk = appVk;
    }

    public String getAppVk() 
    {
        return appVk;
    }
    public void setAppPk(String appPk) 
    {
        this.appPk = appPk;
    }

    public String getAppPk() 
    {
        return appPk;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("appid", getAppid())
            .append("appname", getAppname())
            .append("appsecret", getAppsecret())
            .append("tenantid", getTenantid())
            .append("deptId", getDeptId())
            .append("userId", getUserId())
            .append("authtype", getAuthtype())
            .append("appstatus", getAppstatus())
            .append("hsmgroups", getHsmgroups())
            .append("srvgroups", getSrvgroups())
            .append("creator", getCreator())
            .append("creattime", getCreattime())
            .append("appdesc", getAppdesc())
            .append("secretrating", getSecretrating())
            .append("secretdate", getSecretdate())
            .append("appVk", getAppVk())
            .append("appPk", getAppPk())
            .toString();
    }
}

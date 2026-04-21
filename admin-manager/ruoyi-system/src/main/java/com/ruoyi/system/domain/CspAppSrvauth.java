package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 csp_app_srvauth
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
public class CspAppSrvauth extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 应用编号 */
    private String appid;

    /** 服务码 */
    private String txcode;

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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("appid", getAppid())
            .append("txcode", getTxcode())
            .toString();
    }
}

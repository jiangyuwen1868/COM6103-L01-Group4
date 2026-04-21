package com.ruoyi.web.controller.tx.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class TxRequestBodyCom1 implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 租户编号
     */
    private String tenantId;
    /**
     * 系统渠道交易码
     */
    private String channelTxCode;
    /**
     * 应用ID
     */
    @NotBlank(message = "应用标识不能为空")
    private String appId;

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }

    public String getChannelTxCode() {
        return channelTxCode;
    }

    public void setChannelTxCode(String channelTxCode) {
        this.channelTxCode = channelTxCode;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }
}

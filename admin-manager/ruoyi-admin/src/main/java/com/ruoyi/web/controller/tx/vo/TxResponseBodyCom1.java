package com.ruoyi.web.controller.tx.vo;

import java.io.Serializable;

public class TxResponseBodyCom1 implements Serializable {
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
    private String appId;
    /**
     * 节点ID
     */
    private String nodeId;

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

    public String getNodeId() {
        return nodeId;
    }

    public void setNodeId(String nodeId) {
        this.nodeId = nodeId;
    }
}

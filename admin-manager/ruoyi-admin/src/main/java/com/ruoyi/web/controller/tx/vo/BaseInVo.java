package com.ruoyi.web.controller.tx.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

public class BaseInVo implements TxRequestBodyEntity, Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 密钥方案标识
     */
    @NotBlank(message = "密钥方案标识不能为空")
    private String designID;
    /**
     * 密钥节点标识
     */
    @NotBlank(message = "密钥节点标识不能为空")
    private String nodeID;
    /**
     * 密钥模板标识
     */
    @NotBlank(message = "密钥模板标识不能为空")
    private String keyModelID;

    public String getDesignID() {
        return designID;
    }

    public void setDesignID(String designID) {
        this.designID = designID;
    }

    public String getNodeID() {
        return nodeID;
    }

    public void setNodeID(String nodeID) {
        this.nodeID = nodeID;
    }

    public String getKeyModelID() {
        return keyModelID;
    }

    public void setKeyModelID(String keyModelID) {
        this.keyModelID = keyModelID;
    }
}

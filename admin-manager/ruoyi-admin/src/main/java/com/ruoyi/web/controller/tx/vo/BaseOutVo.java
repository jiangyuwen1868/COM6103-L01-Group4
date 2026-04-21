package com.ruoyi.web.controller.tx.vo;

import java.io.Serializable;

public class BaseOutVo implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 密钥密文
     */
    private String keyValue;
    /**
     * 密钥校验值
     */
    private String checkValue;

    public String getKeyValue() {
        return keyValue;
    }

    public void setKeyValue(String keyValue) {
        this.keyValue = keyValue;
    }

    public String getCheckValue() {
        return checkValue;
    }

    public void setCheckValue(String checkValue) {
        this.checkValue = checkValue;
    }
}

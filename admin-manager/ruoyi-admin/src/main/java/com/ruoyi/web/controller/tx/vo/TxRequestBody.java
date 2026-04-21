package com.ruoyi.web.controller.tx.vo;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

public class TxRequestBody<E extends TxRequestBodyEntity> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Valid
    @NotNull(message = "请求报文体[tx_body->com1]不能为空")
    private TxRequestBodyCom1 com1;
    @Valid
    @NotNull(message = "请求报文体[tx_body->entity]不能为空")
    @JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.EXTERNAL_PROPERTY, property = "sys_tx_code", defaultImpl = BaseInVo.class)
    private E entity;

    public TxRequestBodyCom1 getCom1() {
        return com1;
    }

    public void setCom1(TxRequestBodyCom1 com1) {
        this.com1 = com1;
    }

    public E getEntity() {
        return entity;
    }

    public void setEntity(E vo) {
        this.entity = vo;
    }
}

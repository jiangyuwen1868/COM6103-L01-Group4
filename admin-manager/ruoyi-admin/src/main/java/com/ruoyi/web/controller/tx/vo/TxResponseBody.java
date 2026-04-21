package com.ruoyi.web.controller.tx.vo;

import java.io.Serializable;

public class TxResponseBody<E extends TxResponseBodyEntity> implements Serializable {
    private static final long serialVersionUID = 1L;

    private TxResponseBodyCom1 com1;
    private E entity;

    public TxResponseBodyCom1 getCom1() {
        return com1;
    }

    public void setCom1(TxResponseBodyCom1 com1) {
        this.com1 = com1;
    }

    public E getEntity() {
        return entity;
    }

    public void setEntity(E vo) {
        this.entity = vo;
    }
}

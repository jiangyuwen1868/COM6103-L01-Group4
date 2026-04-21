package com.ruoyi.web.controller.tx.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TxResponseInfo<E extends TxResponseBodyEntity> implements Serializable {
    private static final long serialVersionUID = 1L;

    @JsonProperty("tx_header")
    private TxResponseHeader header;
    @JsonProperty("tx_body")
    private TxResponseBody<E> body;

    public TxResponseHeader getHeader() {
        return header;
    }

    public void setHeader(TxResponseHeader header) {
        this.header = header;
    }

    public TxResponseBody<E> getBody() {
        return body;
    }

    public void setBody(TxResponseBody<E> body) {
        this.body = body;
    }

    public static TxResponseInfo<TxResponseBodyEntity> getTxResponseInfo() {
        TxResponseHeader txResponseHeader = new TxResponseHeader();

        TxResponseBodyCom1 txResponseBodyCom1 = new TxResponseBodyCom1();

        TxResponseBody<TxResponseBodyEntity> txResponseBody = new TxResponseBody<TxResponseBodyEntity>();
        txResponseBody.setCom1(txResponseBodyCom1);

        TxResponseInfo<TxResponseBodyEntity> txResponseInfo = new TxResponseInfo<TxResponseBodyEntity>();
        txResponseInfo.setHeader(txResponseHeader);
        txResponseInfo.setBody(txResponseBody);

        return txResponseInfo;
    }
}

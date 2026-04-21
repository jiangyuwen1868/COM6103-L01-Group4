package com.ruoyi.web.controller.tx.vo;

import java.io.Serializable;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.annotation.JsonTypeResolver;

@JsonTypeInfo(use = JsonTypeInfo.Id.NONE)
@JsonTypeResolver(CustomTypeResolver.class)
public class TxRequestInfo<E extends TxRequestBodyEntity> implements Serializable {
    private static final long serialVersionUID = 1L;

    @Valid
    @NotNull(message = "请求报文头[tx_header]不能为空")
    @JsonProperty("tx_header")
    private TxRequestHeader header;
    @Valid
    @NotNull(message = "请求报文体[tx_body]不能为空")
    @JsonProperty("tx_body")
    private TxRequestBody<E> body;

    public TxRequestHeader getHeader() {
        return header;
    }

    public void setHeader(TxRequestHeader header) {
        this.header = header;
    }

    public TxRequestBody<E> getBody() {
        return body;
    }

    public void setBody(TxRequestBody<E> body) {
        this.body = body;
    }

    public static TxRequestInfo<TxRequestBodyEntity> getTxRequestInfo() {
        TxRequestHeader txRequestHeader = new TxRequestHeader();

        TxRequestBodyCom1 txRequestBodyCom1 = new TxRequestBodyCom1();

        TxRequestBody<TxRequestBodyEntity> txRequestBody = new TxRequestBody<TxRequestBodyEntity>();
        txRequestBody.setCom1(txRequestBodyCom1);

        TxRequestInfo<TxRequestBodyEntity> txRequestInfo = new TxRequestInfo<TxRequestBodyEntity>();
        txRequestInfo.setHeader(txRequestHeader);
        txRequestInfo.setBody(txRequestBody);

        return txRequestInfo;
    }
}

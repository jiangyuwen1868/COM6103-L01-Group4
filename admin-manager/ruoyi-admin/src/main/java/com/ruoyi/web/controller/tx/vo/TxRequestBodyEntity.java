package com.ruoyi.web.controller.tx.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonSubTypes;

@JsonSubTypes({
    @JsonSubTypes.Type(value = CSP200003InVo.class, name = "CSP200003"),
    @JsonSubTypes.Type(value = CSP200004InVo.class, name = "CSP200004"),
    @JsonSubTypes.Type(value = CSP200005InVo.class, name = "CSP200005"),
    
})
public interface TxRequestBodyEntity extends Serializable {
    public String getNodeID();
}

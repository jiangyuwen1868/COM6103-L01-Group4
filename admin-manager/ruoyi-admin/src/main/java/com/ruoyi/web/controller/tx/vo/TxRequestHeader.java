package com.ruoyi.web.controller.tx.vo;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TxRequestHeader implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 报文格式版本号，默认01
     */
    private String sysPkgVersion;
    /**
     * 全局事件跟踪号
     */
    @NotNull(message = "全局事件跟踪号[tx_header->sys_evt_trace_id]不能为空")
    private String sysEvtTraceId;
    /**
     * 调用路径号
     */
    @NotNull(message = "调用路径号不能为空")
    private String sysSndSerialNo;
    /**
     * 业务流水号
     */
    private String sysServiceSn;
    /**
     * 请求时间，格式：yyyyMMddHHmmssSSS
     */
    private String sysReqTime;
    /**
     * 服务码
     */
    @NotBlank(message = "服务码[tx_header->sys_tx_code]不能为空")
    private String sysTxCode;
    /**
     * 报文状态类型：00，请求报文
     */
    private String sysPkgStsType;

    public String getSysPkgVersion() {
        return sysPkgVersion;
    }

    public void setSysPkgVersion(String sysPkgVersion) {
        this.sysPkgVersion = sysPkgVersion;
    }

    public String getSysEvtTraceId() {
        return sysEvtTraceId;
    }

    public void setSysEvtTraceId(String sysEvtTraceId) {
        this.sysEvtTraceId = sysEvtTraceId;
    }

    public String getSysSndSerialNo() {
        return sysSndSerialNo;
    }

    public void setSysSndSerialNo(String sysSndSerialNo) {
        this.sysSndSerialNo = sysSndSerialNo;
    }

    public String getSysServiceSn() {
        return sysServiceSn;
    }

    public void setSysServiceSn(String sysServiceSn) {
        this.sysServiceSn = sysServiceSn;
    }

    public String getSysReqTime() {
        return sysReqTime;
    }

    public void setSysReqTime(String sysReqTime) {
        this.sysReqTime = sysReqTime;
    }

    public String getSysTxCode() {
        return sysTxCode;
    }

    public void setSysTxCode(String sysTxCode) {
        this.sysTxCode = sysTxCode;
    }

    public String getSysPkgStsType() {
        return sysPkgStsType;
    }

    public void setSysPkgStsType(String sysPkgStsType) {
        this.sysPkgStsType = sysPkgStsType;
    }
}

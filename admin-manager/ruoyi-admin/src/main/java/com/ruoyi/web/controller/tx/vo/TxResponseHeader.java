package com.ruoyi.web.controller.tx.vo;

import java.io.Serializable;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class TxResponseHeader implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 全局事件跟踪号
     */
    private String sysEvtTraceId;
    /**
     * 调用路径号
     */
    private String sysSndSerialNo;
    /**
     * 业务流水号
     */
    private String sysServiceSn;
    /**
     * 服务码
     */
    private String sysTxCode;
    /**
     * 服务接受时间，格式：yyyyMMddHHmmssSSS
     */
    private String sysRecvTime;
    /**
     * 服务响应时间，格式：yyyyMMddHHmmssSSS
     */
    private String sysRespTime;
    /**
     * 报文状态类型：01，响应报文
     */
    private String sysPkgStsType;
    /**
     * 服务响应码
     */
    private String sysRespCode;
    /**
     * 服务响应描述
     */
    private String sysRespDesc;

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

    public String getSysTxCode() {
        return sysTxCode;
    }

    public void setSysTxCode(String sysTxCode) {
        this.sysTxCode = sysTxCode;
    }

    public String getSysRecvTime() {
        return sysRecvTime;
    }

    public void setSysRecvTime(String sysRecvTime) {
        this.sysRecvTime = sysRecvTime;
    }

    public String getSysRespTime() {
        return sysRespTime;
    }

    public void setSysRespTime(String sysRespTime) {
        this.sysRespTime = sysRespTime;
    }

    public String getSysPkgStsType() {
        return sysPkgStsType;
    }

    public void setSysPkgStsType(String sysPkgStsType) {
        this.sysPkgStsType = sysPkgStsType;
    }

    public String getSysRespCode() {
        return sysRespCode;
    }

    public void setSysRespCode(String sysRespCode) {
        this.sysRespCode = sysRespCode;
    }

    public void setSysRespCode(int sysRespCode) {
        this.sysRespCode = String.format("%012d", sysRespCode);
    }

    public String getSysRespDesc() {
        return sysRespDesc;
    }

    public void setSysRespDesc(String sysRespDesc) {
        this.sysRespDesc = sysRespDesc;
    }
}

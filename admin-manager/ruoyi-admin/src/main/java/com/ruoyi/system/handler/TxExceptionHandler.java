package com.ruoyi.system.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ruoyi.system.context.SessionContext;
import com.ruoyi.system.enums.ErrorInfo;
import com.ruoyi.system.exception.CspException;
import com.ruoyi.system.util.DateUtils;
import com.ruoyi.web.controller.tx.vo.TxRequestBodyEntity;
import com.ruoyi.web.controller.tx.vo.TxRequestInfo;
import com.ruoyi.web.controller.tx.vo.TxResponseBodyEntity;
import com.ruoyi.web.controller.tx.vo.TxResponseInfo;

@RestControllerAdvice
public class TxExceptionHandler {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @ExceptionHandler(CspException.class)
    @ResponseBody
    public TxResponseInfo<TxResponseBodyEntity> cspExceptionHandler(CspException e) {
        logger.debug("==========TxExceptionHandler->cspExceptionHandler");


        TxRequestInfo<TxRequestBodyEntity> txRequestInfo = SessionContext.getCurrentContext().getTxRequestInfo();

        TxResponseInfo<TxResponseBodyEntity> txResponseInfo = TxResponseInfo.getTxResponseInfo();

        if (txRequestInfo != null && txRequestInfo.getHeader() != null) {
            txResponseInfo.getHeader().setSysEvtTraceId(txRequestInfo.getHeader().getSysEvtTraceId());
            txResponseInfo.getHeader().setSysSndSerialNo(txRequestInfo.getHeader().getSysSndSerialNo());
            txResponseInfo.getHeader().setSysServiceSn(txRequestInfo.getHeader().getSysServiceSn());
            txResponseInfo.getHeader().setSysTxCode(txRequestInfo.getHeader().getSysTxCode());
        }
        txResponseInfo.getHeader().setSysRecvTime(DateUtils.formatYYYYMMDDHHMMSSSSS(SessionContext.getCurrentContext().getSysRecvTime()));
        txResponseInfo.getHeader().setSysRespTime(DateUtils.formatYYYYMMDDHHMMSSSSS(System.currentTimeMillis()));
        txResponseInfo.getHeader().setSysPkgStsType("01");
        txResponseInfo.getHeader().setSysRespCode(e.getCode());
        txResponseInfo.getHeader().setSysRespDesc(e.getMessage());

        if (txRequestInfo != null && txRequestInfo.getBody() != null && txRequestInfo.getBody().getCom1() != null) {
            txResponseInfo.getBody().getCom1().setTenantId(txRequestInfo.getBody().getCom1().getTenantId());
            txResponseInfo.getBody().getCom1().setChannelTxCode(txRequestInfo.getBody().getCom1().getChannelTxCode());
            txResponseInfo.getBody().getCom1().setAppId(txRequestInfo.getBody().getCom1().getAppId());
            if (txRequestInfo.getBody().getEntity() != null) {
                txResponseInfo.getBody().getCom1().setNodeId(txRequestInfo.getBody().getEntity().getNodeID());
            }
        }

        return txResponseInfo;
    }

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public TxResponseInfo<TxResponseBodyEntity> exceptionHandler(Exception e) {
        logger.debug("==========TxExceptionHandler->exceptionHandler");
        logger.error("", e);


        TxRequestInfo<TxRequestBodyEntity> txRequestInfo = SessionContext.getCurrentContext().getTxRequestInfo();

        TxResponseInfo<TxResponseBodyEntity> txResponseInfo = TxResponseInfo.getTxResponseInfo();

        if (txRequestInfo != null && txRequestInfo.getHeader() != null) {
            txResponseInfo.getHeader().setSysEvtTraceId(txRequestInfo.getHeader().getSysEvtTraceId());
            txResponseInfo.getHeader().setSysSndSerialNo(txRequestInfo.getHeader().getSysSndSerialNo());
            txResponseInfo.getHeader().setSysServiceSn(txRequestInfo.getHeader().getSysServiceSn());
            txResponseInfo.getHeader().setSysTxCode(txRequestInfo.getHeader().getSysTxCode());
        }
        txResponseInfo.getHeader().setSysRecvTime(DateUtils.formatYYYYMMDDHHMMSSSSS(SessionContext.getCurrentContext().getSysRecvTime()));
        txResponseInfo.getHeader().setSysRespTime(DateUtils.formatYYYYMMDDHHMMSSSSS(System.currentTimeMillis()));
        txResponseInfo.getHeader().setSysPkgStsType("01");
        txResponseInfo.getHeader().setSysRespCode(ErrorInfo.UNKNOWN.getCode());
        txResponseInfo.getHeader().setSysRespDesc(ErrorInfo.UNKNOWN.getInfo());

        if (txRequestInfo != null && txRequestInfo.getBody() != null && txRequestInfo.getBody().getCom1() != null) {
            txResponseInfo.getBody().getCom1().setTenantId(txRequestInfo.getBody().getCom1().getTenantId());
            txResponseInfo.getBody().getCom1().setChannelTxCode(txRequestInfo.getBody().getCom1().getChannelTxCode());
            txResponseInfo.getBody().getCom1().setAppId(txRequestInfo.getBody().getCom1().getAppId());
            if (txRequestInfo.getBody().getEntity() != null) {
                txResponseInfo.getBody().getCom1().setNodeId(txRequestInfo.getBody().getEntity().getNodeID());
            }
        }

        return txResponseInfo;
    }
}

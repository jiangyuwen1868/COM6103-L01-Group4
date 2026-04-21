package com.ruoyi.system.handler;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.ruoyi.system.context.SessionContext;
import com.ruoyi.system.util.DateUtils;
import com.ruoyi.web.controller.tx.vo.TxRequestBodyEntity;
import com.ruoyi.web.controller.tx.vo.TxRequestInfo;
import com.ruoyi.web.controller.tx.vo.TxResponseBodyEntity;
import com.ruoyi.web.controller.tx.vo.TxResponseInfo;

@RestControllerAdvice
public class BadRequestExceptionHandler extends ResponseEntityExceptionHandler {
    protected final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.debug("==========BadRequestExceptionHandler->handleMethodArgumentNotValid");

        String message = "";
        BindingResult result = ex.getBindingResult();
        if (result.hasErrors()) {
            List<ObjectError> list = result.getAllErrors();
            StringBuilder errorMsgBuffer = new StringBuilder();
            for (ObjectError error : list) {
                if (error instanceof FieldError) {
                    FieldError errorMessage = (FieldError) error;
                    errorMsgBuffer = errorMsgBuffer.append(errorMessage.getDefaultMessage()).append(',');
                }
            }
            message = errorMsgBuffer.toString().substring(0, errorMsgBuffer.length() - 1);
        }

        String pathInfo = "";
        try {
            pathInfo = ((ServletWebRequest) request).getRequest().getServletPath();
            if (pathInfo == null || "".equals(pathInfo.trim())) {
                pathInfo = ((ServletWebRequest) request).getRequest().getPathInfo();
            }
        } catch (Exception e) {
            logger.error("", e);
        }
        System.out.println("====================pathInfo:" + pathInfo);
        if (!"/manager/tx".equals(pathInfo)) {
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode object = objectMapper.createObjectNode();
            object.put("errorCode", "SRV100011006");
            object.put("errorMsg", message);

            return new ResponseEntity<Object>(object, headers, HttpStatus.OK);
        } else {

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
            txResponseInfo.getHeader().setSysRespCode("SRV100011006");
            txResponseInfo.getHeader().setSysRespDesc(message);

            if (txRequestInfo != null && txRequestInfo.getBody() != null && txRequestInfo.getBody().getCom1() != null) {
                txResponseInfo.getBody().getCom1().setTenantId(txRequestInfo.getBody().getCom1().getTenantId());
                txResponseInfo.getBody().getCom1().setChannelTxCode(txRequestInfo.getBody().getCom1().getChannelTxCode());
                txResponseInfo.getBody().getCom1().setAppId(txRequestInfo.getBody().getCom1().getAppId());
                if (txRequestInfo.getBody().getEntity() != null) {
                    txResponseInfo.getBody().getCom1().setNodeId(txRequestInfo.getBody().getEntity().getNodeID());
                }
            }

            return new ResponseEntity<Object>(txResponseInfo, headers, HttpStatus.OK);
        }
    }
}

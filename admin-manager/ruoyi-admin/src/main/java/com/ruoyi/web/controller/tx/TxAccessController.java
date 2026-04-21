package com.ruoyi.web.controller.tx;

import javax.validation.Valid;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ruoyi.system.context.SessionContext;
import com.ruoyi.system.enums.ErrorInfo;
import com.ruoyi.system.exception.CspException;
import com.ruoyi.system.util.DateUtils;
import com.ruoyi.system.util.SpringUtils;
import com.ruoyi.web.controller.tx.service.CspService;
import com.ruoyi.web.controller.tx.vo.TxRequestBodyEntity;
import com.ruoyi.web.controller.tx.vo.TxRequestInfo;
import com.ruoyi.web.controller.tx.vo.TxResponseBodyEntity;
import com.ruoyi.web.controller.tx.vo.TxResponseInfo;

@RestController
public class TxAccessController extends BaseController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields(new String[] { "admin" });
    }

    @PostMapping("/manager/tx")
    @ResponseBody
    public TxResponseInfo<TxResponseBodyEntity> access(@Valid @RequestBody TxRequestInfo<TxRequestBodyEntity> txRequestInfo) {

    	SessionContext context = SessionContext.getCurrentContext();
        context.setSysRecvTime(System.currentTimeMillis());
        context.setTxRequestInfo(txRequestInfo);
        TxResponseInfo<TxResponseBodyEntity> txResponseInfo = TxResponseInfo.getTxResponseInfo();
        if (txRequestInfo == null) {

            txResponseInfo.getHeader().setSysRecvTime(DateUtils.formatYYYYMMDDHHMMSSSSS(SessionContext.getCurrentContext().getSysRecvTime()));
            txResponseInfo.getHeader().setSysRespTime(DateUtils.formatYYYYMMDDHHMMSSSSS(System.currentTimeMillis()));
            txResponseInfo.getHeader().setSysPkgStsType("01");
            txResponseInfo.getHeader().setSysRespCode(ErrorInfo.REQUEST_EMPTY.getCode());
            txResponseInfo.getHeader().setSysRespDesc(ErrorInfo.REQUEST_EMPTY.getInfo());

            return txResponseInfo;
        }
        
        String sysTxCode = txRequestInfo.getHeader().getSysTxCode();

        
        txResponseInfo.getHeader().setSysEvtTraceId(txRequestInfo.getHeader().getSysEvtTraceId());
        txResponseInfo.getHeader().setSysSndSerialNo(txRequestInfo.getHeader().getSysSndSerialNo());
        txResponseInfo.getHeader().setSysServiceSn(txRequestInfo.getHeader().getSysServiceSn());
        txResponseInfo.getHeader().setSysTxCode(sysTxCode);
        txResponseInfo.getHeader().setSysRecvTime(DateUtils.formatYYYYMMDDHHMMSSSSS(SessionContext.getCurrentContext().getSysRecvTime()));
        txResponseInfo.getHeader().setSysPkgStsType("01");
        txResponseInfo.getHeader().setSysRespCode(ErrorInfo.SUCCESS.getCode());
        txResponseInfo.getHeader().setSysRespDesc(ErrorInfo.SUCCESS.getInfo());

        txResponseInfo.getBody().getCom1().setTenantId(txRequestInfo.getBody().getCom1().getTenantId());
        txResponseInfo.getBody().getCom1().setChannelTxCode(txRequestInfo.getBody().getCom1().getChannelTxCode());
        txResponseInfo.getBody().getCom1().setAppId(txRequestInfo.getBody().getCom1().getAppId());
        txResponseInfo.getBody().getCom1().setNodeId(txRequestInfo.getBody().getEntity().getNodeID());

        CspService cspService = null;
        try {
            cspService = SpringUtils.getBean(sysTxCode, CspService.class);
        } catch (Exception e) {
            logger.error("", e);
            throw new CspException(1006, "服务码非法", e);
        }

        cspService.process(txRequestInfo, txResponseInfo);

        return txResponseInfo;
    }
}

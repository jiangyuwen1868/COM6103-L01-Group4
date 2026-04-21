package com.ruoyi.web.controller.tx.service;

import java.io.Serializable;

import com.ruoyi.web.controller.tx.vo.TxRequestBodyEntity;
import com.ruoyi.web.controller.tx.vo.TxRequestInfo;
import com.ruoyi.web.controller.tx.vo.TxResponseBodyEntity;
import com.ruoyi.web.controller.tx.vo.TxResponseInfo;

public interface CspService extends Serializable {
    public void process(TxRequestInfo<TxRequestBodyEntity> txRequestInfo, TxResponseInfo<TxResponseBodyEntity> txResponseInfo);
}

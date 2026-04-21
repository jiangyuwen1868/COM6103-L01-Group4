package com.ruoyi.web.controller.tx.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ruoyi.web.controller.tx.service.CspService;
import com.ruoyi.web.controller.tx.vo.TxRequestBodyEntity;
import com.ruoyi.web.controller.tx.vo.TxRequestInfo;
import com.ruoyi.web.controller.tx.vo.TxResponseBodyEntity;
import com.ruoyi.web.controller.tx.vo.TxResponseInfo;

public abstract class BaseCspService implements CspService {
    private static final long serialVersionUID = 1L;

    protected final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void process(TxRequestInfo<TxRequestBodyEntity> txRequestInfo, TxResponseInfo<TxResponseBodyEntity> txResponseInfo) {
    }

    
}

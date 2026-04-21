package com.ruoyi.system.context;

import java.util.concurrent.ConcurrentHashMap;

import org.springframework.util.StopWatch;

import com.ruoyi.web.controller.tx.vo.TxRequestBodyEntity;
import com.ruoyi.web.controller.tx.vo.TxRequestInfo;
import com.ruoyi.web.controller.tx.vo.TxResponseBodyEntity;
import com.ruoyi.web.controller.tx.vo.TxResponseInfo;

public class SessionContext extends ConcurrentHashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    protected static Class<? extends SessionContext> contextClass = SessionContext.class;

    protected static final ThreadLocal<? extends SessionContext> threadLocal = new ThreadLocal<SessionContext>() {
        @Override
        protected SessionContext initialValue() {
            try {
                return contextClass.newInstance();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        }
    };

    public SessionContext() {
        super();
    }

    public static void setContextClass(Class<? extends SessionContext> contextClass) {
        SessionContext.contextClass = contextClass;
    }

    public static SessionContext getCurrentContext() {
        return threadLocal.get();
    }

    public void release() {
        clear();
        threadLocal.remove();
    }

    public void set(String key, Object value) {
        if (value != null) {
            put(key, value);
        } else {
            remove(key);
        }
    }

    @SuppressWarnings("unchecked")
    public TxRequestInfo<TxRequestBodyEntity> getTxRequestInfo() {
        return (TxRequestInfo<TxRequestBodyEntity>) get("tx_request_info");
    }

    public void setTxRequestInfo(TxRequestInfo<TxRequestBodyEntity> txRequestInfo) {
        set("tx_request_info", txRequestInfo);
    }

    @SuppressWarnings("unchecked")
    public TxResponseInfo<TxResponseBodyEntity> getTxResponseInfo() {
        return (TxResponseInfo<TxResponseBodyEntity>) get("tx_response_info");
    }

    public void setTxResponseInfo(TxResponseInfo<TxResponseBodyEntity> txResponseInfo) {
        set("tx_response_info", txResponseInfo);
    }

    public long getSysRecvTime() {
        return (long) get("sys_recv_time");
    }

    public void setSysRecvTime(long sysRecvTime) {
        set("sys_recv_time", sysRecvTime);
    }

    public long getSysRespTime() {
        return (long) get("sys_resp_time");
    }

    public void setSysRespTime(long sysRespTime) {
        set("sys_resp_time", sysRespTime);
    }

    public StopWatch getStopWatch() {
        return (StopWatch) get("stop_watch");
    }

    public void setStopWatch(StopWatch stopWatch) {
        set("stop_watch", stopWatch);
    }
}

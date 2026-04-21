package com.jyw.csp.api.exception;

public class CspClientException extends RuntimeException {

	/** Serial version UID */
    private static final long serialVersionUID = -2641602465962449893L;

    /** 未知异常 */
    public static final String UNKNOWN_EXCEPTION = "CSP01U100001";

    /** 网络异常 */
    public static final String NETWORK_EXCEPTION = "CSP01N100001";

    /** 超时异常 */
    public static final String TIMEOUT_EXCEPTION = "CSP01T100001";

    /** 业务异常 */
    public static final String BIZ_EXCEPTION = "CSP01B100001";

    /** 拒绝服务 */
    public static final String FORBIDDEN_EXCEPTION = "CSP01F100001";

    /** 配置异常 */
    public static final String CONFIG_EXCEPTION = "CSP01C100001";

    /** 错误码 */
    private String code;

    public CspClientException() {
        super();
    }

    public CspClientException(Throwable cause) {
        super(cause);
    }


    public CspClientException(String code, String message, Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public CspClientException(String code, String message) {
        super(message);
        this.code = code;
    }


    /**
     * getter method
     * 
     * @see RpcException#code
     * @return the code
     */
    public String getCode() {
        return code;
    }

    /**
     * setter method
     * 
     * @see RpcException#code
     * @param code
     *            the code to set
     */
    public void setCode(String code) {
        this.code = code;
    }

    public boolean isBiz() {
        return code == BIZ_EXCEPTION;
    }

    public boolean isForbidded() {
        return code == FORBIDDEN_EXCEPTION;
    }

    public boolean isTimeout() {
        return code == TIMEOUT_EXCEPTION;
    }

    public boolean isNetwork() {
        return code == NETWORK_EXCEPTION;
    }

    public boolean isConfig() {
        return code == CONFIG_EXCEPTION;
    }
}

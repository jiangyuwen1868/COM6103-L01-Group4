package com.jyw.csp.api.config;

import com.jyw.csp.api.exception.CspClientException;
import com.jyw.csp.util.string.StringUtils;

public class ClientConfig implements IConfigCheck {

	@Override
	public void check() throws CspClientException {
		if(StringUtils.checkEmpty(tenantId)) {
			throw new CspClientException(CspClientException.CONFIG_EXCEPTION, "API配置信息，租户编号[tenantId]不能为空");
		}
		if(StringUtils.checkEmpty(appId)) {
			throw new CspClientException(CspClientException.CONFIG_EXCEPTION, "API配置信息，应用编号[appId]不能为空");
		}
		if(StringUtils.checkEmpty(appScrect)) {
			throw new CspClientException(CspClientException.CONFIG_EXCEPTION, "API配置信息，应用密钥[appScrect]不能为空");
		}
		if(StringUtils.checkEmpty(serverAddress)) {
			throw new CspClientException(CspClientException.CONFIG_EXCEPTION, "API配置信息，服务地址[serverAddress]不能为空");
		}
		
		if(isUsedProxy) {
			if(StringUtils.checkEmpty(proxy_ip)) {
				throw new CspClientException(CspClientException.CONFIG_EXCEPTION, "API配置信息，代理服务器IP地址[proxy_ip]不能为空");
			}
			if(proxy_port == 0) {
				throw new CspClientException(CspClientException.CONFIG_EXCEPTION, "API配置信息，代理服务器端口[proxy_port]不能为空");
			}
		}
		
		if(enableHttps) {
			if(StringUtils.checkEmpty(clientCert)) {
				throw new CspClientException(CspClientException.CONFIG_EXCEPTION, "API配置信息，客户端证书[clientCert]不能为空");
			}
			if(StringUtils.checkEmpty(clientCertPwd)) {
				throw new CspClientException(CspClientException.CONFIG_EXCEPTION, "API配置信息，客户端证书密码[clientCertPwd]不能为空");
			}
			if(StringUtils.checkEmpty(trustCert)) {
				throw new CspClientException(CspClientException.CONFIG_EXCEPTION, "API配置信息，信任证书库[trustCert]不能为空");
			}
		}
	}
	
	/** 租户编号 **/
	private String tenantId;
	/**
	 * 应用编号
	 */
	private String appId;
	/**
	 * 应用密钥
	 */
	private String appScrect;
	/** 服务地址 **/
	private String serverAddress;
	
	/** 服务路径 **/
	private String serverPath = "/cspGateway/mainAccess";
	
	/** 连接超时时间 **/
	private int connectionTimeout = 15000;
	
	/** 写入超时时间 **/
	private int writeTimeout = 15000;
	
	/** 读取超时时间 **/
	private int readTimeout = 15000;
	/** 最大空闲连接数 **/
	private int maxIdleConnections = 5;
	/** 最大空闲时间 **/
    private int maxIdleTimes = 60 * 1000;
    /** 长连接保持时间 **/
    private int keepAliveDuration = 50000;
    /** 最大连接数 **/
    private int maxRequests = 640;
    /** 每路由最大默认连接数 **/
    private int maxRequestsPerHost = 50;
	
	/** 是否安全认证，defluat : true**/
	private boolean isSecAuth = true;
	
	/** 是否使用代理访问 **/
	private boolean isUsedProxy = false;
	
	/** 代理服务器IP地址 **/
	private String proxy_ip;
	
	/** 代理服务器端口 **/
	private int proxy_port = 0;
	
	/** 重试次数，默认1次 */
    private int retry = 1;
    
    /** 失败重试次数 **/
    private int failretry = 3;

    private boolean enableHttps = false;
    private String storeType = "PKCS12";
    private String clientCert;
    private String clientCertPwd;
    private String trustCert;
    private String sslProtocol = "GMSSLv1.1";
    private String supportedProtocols = "GMSSLv1.1";
    private String supportedCipherSuites = "ECC_SM4_GCM_SM3,ECC_SM4_CBC_SM3,ECDHE_SM4_GCM_SM3,ECDHE_SM4_CBC_SM3";

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppScrect() {
        return appScrect;
    }

    public void setAppScrect(String appScrect) {
        this.appScrect = appScrect;
    }

    public String getServerAddress() {
		return serverAddress;
	}

	public void setServerAddress(String serverAddress) {
		this.serverAddress = serverAddress;
	}

	public String getServerPath() {
		return serverPath;
	}

	public void setServerPath(String serverPath) {
		this.serverPath = serverPath;
	}

	public int getWriteTimeout() {
		return writeTimeout;
	}

	public void setWriteTimeout(int writeTimeout) {
		this.writeTimeout = writeTimeout;
	}

	public int getReadTimeout() {
		return readTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public int getConnectionTimeout() {
		return connectionTimeout;
	}

	public void setConnectionTimeout(int connectionTimeout) {
		this.connectionTimeout = connectionTimeout;
	}

	public int getMaxIdleConnections() {
		return maxIdleConnections;
	}

	public void setMaxIdleConnections(int maxIdleConnections) {
		this.maxIdleConnections = maxIdleConnections;
	}

	public int getMaxIdleTimes() {
		return maxIdleTimes;
	}

	public void setMaxIdleTimes(int maxIdleTimes) {
		this.maxIdleTimes = maxIdleTimes;
	}

	public int getKeepAliveDuration() {
		return keepAliveDuration;
	}

	public void setKeepAliveDuration(int keepAliveDuration) {
		this.keepAliveDuration = keepAliveDuration;
	}

	public int getMaxRequests() {
		return maxRequests;
	}

	public void setMaxRequests(int maxRequests) {
		this.maxRequests = maxRequests;
	}

	public int getMaxRequestsPerHost() {
		return maxRequestsPerHost;
	}

	public void setMaxRequestsPerHost(int maxRequestsPerHost) {
		this.maxRequestsPerHost = maxRequestsPerHost;
	}

	public boolean isSecAuth() {
		return isSecAuth;
	}

	public void setSecAuth(boolean isSecAuth) {
		this.isSecAuth = isSecAuth;
	}

	public boolean isUsedProxy() {
		return isUsedProxy;
	}

	public void setUsedProxy(boolean isUsedProxy) {
		this.isUsedProxy = isUsedProxy;
	}

	public String getProxy_ip() {
		return proxy_ip;
	}

	public void setProxy_ip(String proxy_ip) {
		this.proxy_ip = proxy_ip;
	}

	public int getProxy_port() {
		return proxy_port;
	}

	public void setProxy_port(int proxy_port) {
		this.proxy_port = proxy_port;
	}

	public int getRetry() {
		return retry;
	}

	public void setRetry(int retry) {
		this.retry = retry;
	}

	public int getFailretry() {
		return failretry;
	}

	public void setFailretry(int failretry) {
		this.failretry = failretry;
	}

    public boolean isEnableHttps() {
        return enableHttps;
    }

    public void setEnableHttps(boolean enableHttps) {
        this.enableHttps = enableHttps;
    }

    public String getStoreType() {
        return storeType;
    }

    public void setStoreType(String storeType) {
        this.storeType = storeType;
    }

    public String getClientCert() {
        return clientCert;
    }

    public void setClientCert(String clientCert) {
        this.clientCert = clientCert;
    }

    public String getClientCertPwd() {
        return clientCertPwd;
    }

    public void setClientCertPwd(String clientCertPwd) {
        this.clientCertPwd = clientCertPwd;
    }

    public String getTrustCert() {
        return trustCert;
    }

    public void setTrustCert(String trustCert) {
        this.trustCert = trustCert;
    }

    public String getSslProtocol() {
        return sslProtocol;
    }

    public void setSslProtocol(String sslProtocol) {
        this.sslProtocol = sslProtocol;
    }

    public String getSupportedProtocols() {
        return supportedProtocols;
    }

    public void setSupportedProtocols(String supportedProtocols) {
        this.supportedProtocols = supportedProtocols;
    }

    public String getSupportedCipherSuites() {
        return supportedCipherSuites;
    }

    public void setSupportedCipherSuites(String supportedCipherSuites) {
        this.supportedCipherSuites = supportedCipherSuites;
    }
}

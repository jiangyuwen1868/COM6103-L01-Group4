package com.jyw.csp.api;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.KeyManager;
import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.X509TrustManager;

import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.anydef.gwapi.sdk.core.https.CertificationUtils;
import com.anydef.gwapi.sdk.core.https.MyX509TrustManager;
import com.jyw.csp.api.CspHttpOpenApiClent.Builder;
import com.jyw.csp.api.config.ClientConfig;
import com.jyw.csp.api.config.ConfigHelper;
import com.jyw.csp.api.configure.PropertiesConfiguration;
import com.jyw.csp.api.impl.CspServicesAPI;
import com.jyw.csp.util.string.StringUtils;

public class CspHttpOpenApi {
    private static final Logger logger = LoggerFactory.getLogger(CspHttpOpenApi.class);

    private static final String CONFIG_FILE_PATH = "classpath:csp-client.properties";

    /** {@link ClientConfig} */
    private static ClientConfig clientConfig;

    // 租户编号
    private String tenantId = "csp";
    // appid
    private String appid = "csp";// "d45d549f";
    // appscrect
    private String appscrect = "0d14fe0b56fa4ff1bdb212460fdd3780";

    // 字符编码
    public final static Charset charset = Charset.forName("UTF-8");

    private static boolean inited = false;

    private static CspHttpOpenApiClent client = null;

    /***** APIs *****/
    private CspServicesAPI servicesAPI = null;


/*
    static {
//        Security.insertProviderAt(new GMJCE(), 1);
//        Security.insertProviderAt(new GMJSSE(), 2);

        try {
            initConfig();
            inited = true;
        } catch (Exception e) {
            logger.error("initConfig", e);
            inited = false;
        }

        logger.debug("---- initConfig end ----");
    }
*/    
    
    private synchronized void m_initConfig(String configFilePath) {
    	try {
//          Security.insertProviderAt(new GMJCE(), 1);
//          Security.insertProviderAt(new GMJSSE(), 2);
    		if(!inited) {
    			if(StringUtils.hasText(configFilePath)) {
    				initConfig(configFilePath);
    			} else {
    				initConfig();
    			}
    		}
            inited = true;
        } catch (Exception e) {
            logger.error("initConfig", e);
            inited = false;
        }

        logger.debug("---- initConfig end ----");
    }
    
    private static void initConfig() {
    	initConfig(CONFIG_FILE_PATH);
    }

    private static void initConfig(String configFilePath) {
        PropertiesConfiguration configuration = PropertiesConfiguration.newInstance(configFilePath);

        // 初始化client
        clientConfig = new ClientConfig();
        ConfigHelper.initConfig(clientConfig, "client.", configuration);
        clientConfig.check();
    }
    
    public CspHttpOpenApi(String configFilePath) {
    	this(null, null, null, null, configFilePath);
    }
    
    public CspHttpOpenApi(ClientConfig clientConfig) {
    	this(null, null, null, clientConfig, null);
    }

    public CspHttpOpenApi() {
        this(null, null, null, null, null);
    }

    public CspHttpOpenApi(String appid, String appscrect) {
        this(null, appid, appscrect, null, null);
    }
    
    public CspHttpOpenApi(String tenantId, String appid, String appscrect) {
    	this(tenantId, appid, appscrect, null, null);
    }

    public CspHttpOpenApi(String tenantId, String appid, String appscrect, ClientConfig clientCfg, String configFilePath) {
    	if(clientCfg != null) {
    		clientConfig = clientCfg;
    		clientConfig.check();
    	} else {
    		m_initConfig(configFilePath);
    	}
        if (StringUtils.hasText(tenantId)) {
            this.tenantId = tenantId;
        } else {
            this.tenantId = clientConfig.getTenantId();
        }
        if (StringUtils.hasText(appid)) {
            this.appid = appid;
        } else {
            this.appid = clientConfig.getAppId();
        }
        if (StringUtils.hasText(appscrect)) {
            this.appscrect = appscrect;
        } else {
            this.appscrect = clientConfig.getAppScrect();
        }

        if (client == null) {
            try {
                Builder builder = CspHttpOpenApiClent.newBuilder();
                builder.appKey(this.appid);
                builder.appSecret(this.appscrect);
                builder.writeTimeoutMillis(clientConfig.getWriteTimeout());
                builder.readTimeoutMillis(clientConfig.getReadTimeout());
                builder.connectionTimeoutMillis(clientConfig.getConnectionTimeout());
                builder.keepAliveDurationMillis(clientConfig.getKeepAliveDuration());
                builder.maxRequests(clientConfig.getMaxRequests());
                builder.maxRequestsPerHost(clientConfig.getMaxRequestsPerHost());

                if (clientConfig.isEnableHttps()) {
                    String storeType = clientConfig.getStoreType();
                    String clientCert = clientConfig.getClientCert();
                    String pwd = clientConfig.getClientCertPwd();

                    KeyStore keyStore = null;
                    InputStream in = null;
                    try {
                        in = Thread.currentThread().getContextClassLoader().getResourceAsStream(clientCert);
                        if (in == null) {
                            throw new IOException("读取[" + clientCert + "]失败");
                        }

                        keyStore = KeyStore.getInstance(storeType);
                        keyStore.load(in, pwd.toCharArray());
                    } catch (KeyStoreException e) {
                        logger.error("", e);
                    } catch (IOException e) {
                        logger.error("", e);
                    } catch (CertificateException e) {
                        logger.error("", e);
                    } catch (NoSuchAlgorithmException e) {
                        logger.error("", e);
                    } finally {
                        if (in != null) {
                            try {
                                in.close();
                            } catch (IOException e) {
                                logger.warn("", e);
                            }
                        }
                    }
                    KeyManager[] km = null;
                    if (keyStore != null) {
                        try {
                            KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
                            kmf.init(keyStore, pwd.toCharArray());
                            km = kmf.getKeyManagers();
                        } catch (NoSuchAlgorithmException e) {
                            logger.error("", e);
                        } catch (UnrecoverableKeyException e) {
                            logger.error("", e);
                        } catch (KeyStoreException e) {
                            logger.error("", e);
                        }
                    }

                    X509Certificate[] trustCerts = null;
                    String trustCert = clientConfig.getTrustCert();
                    if (StringUtils.hasText(trustCert)) {
                        trustCerts = CertificationUtils.loadCertificate(trustCert);
                    }

                    builder.keyManagers(km);
                    if (trustCerts != null) {
                        builder.x509TrustManagers(new X509TrustManager[] { new MyX509TrustManager(trustCerts) });
                    }
                    builder.secureRandom(new SecureRandom());
                    builder.hostnameVerifier(NoopHostnameVerifier.INSTANCE);
                    if (StringUtils.hasText(clientConfig.getSslProtocol())) {
                        builder.sslProtocol(clientConfig.getSslProtocol());
                    }
                    if (StringUtils.hasText(clientConfig.getSupportedProtocols())) {
                        builder.supportedProtocols(clientConfig.getSupportedProtocols().split(","));
                    }
                    if (StringUtils.hasText(clientConfig.getSupportedCipherSuites())) {
                        builder.supportedCipherSuites(clientConfig.getSupportedCipherSuites().split(","));
                    }
                }

                client = builder.build();
            } catch (Exception e) {
                System.err.println("***********API初始化配置文件：" + inited);
            }
        }


        if (servicesAPI == null) {
        	servicesAPI = new CspServicesAPI(client, this.tenantId, this.appid, this.appscrect);
        }

    }

    public static ClientConfig getClientConfig() {
        return clientConfig;
    }


    public CspServicesAPI getServicesAPI() {
        return servicesAPI;
    }



    /**
     * 释放资源
     */
    public void close() {
        if (client != null) {
            client.close();
            client = null;
            servicesAPI = null;
        }
    }
}

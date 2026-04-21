package com.jyw.csp.api;

import org.apache.http.HttpHost;

import com.anydef.gwapi.sdk.core.BaseApiClient;
import com.anydef.gwapi.sdk.core.BaseApiClientBuilder;
import com.anydef.gwapi.sdk.core.annotation.NotThreadSafe;
import com.anydef.gwapi.sdk.core.annotation.ThreadSafe;
import com.anydef.gwapi.sdk.core.constant.SdkConstant;
import com.anydef.gwapi.sdk.core.enums.Method;
import com.anydef.gwapi.sdk.core.enums.Scheme;
import com.anydef.gwapi.sdk.core.model.ApiCallBack;
import com.anydef.gwapi.sdk.core.model.ApiRequest;
import com.anydef.gwapi.sdk.core.model.ApiResponse;
import com.anydef.gwapi.sdk.core.model.BuilderParams;

@ThreadSafe
public final class CspHttpOpenApiClent extends BaseApiClient {
	
    private CspHttpOpenApiClent(BuilderParams builderParams) {
        super(builderParams);
    }

    @NotThreadSafe
    public static class Builder extends BaseApiClientBuilder<CspHttpOpenApiClent.Builder, CspHttpOpenApiClent>{

        @Override
        protected CspHttpOpenApiClent build(BuilderParams params) {
        	boolean isUsedProxy = CspHttpOpenApi.getClientConfig().isUsedProxy();
        	if(isUsedProxy) {
	        	HttpHost proxy = new HttpHost(CspHttpOpenApi.getClientConfig().getProxy_ip(), 
	        			CspHttpOpenApi.getClientConfig().getProxy_port());
	        	params.setExtParam(SdkConstant.PROXY_HTTPHOST_OBJECT_KEY, proxy);
        	}
            return new CspHttpOpenApiClent(params);
        }
    }

    public static Builder newBuilder(){
        return new CspHttpOpenApiClent.Builder();
    }

    public static CspHttpOpenApiClent getInstance(){
        return getApiClassInstance(CspHttpOpenApiClent.class);
    }

    /**
     * 异步请求发送数据（不带SDK安全请求header）
     * @param scheme http scheme
     * @param method 请求方式
     * @param group_host 服务器分组地址，多个使用“;”分号分隔
     * @param path 请求路径
     * @param body 请求数据内容
     * @param isLoadBalance 是否负载均衡
     * @param _callBack 回调方法
     */
    private void asyncPostData(Scheme scheme, Method method, String group_host, String path, String body, boolean isLoadBalance, ApiCallBack _callBack) {
    	if(body==null) {
    		body = "";
    	}
    	ApiRequest _apiRequest = new ApiRequest(scheme, method, group_host, path, body.getBytes(), isLoadBalance);
        asyncInvoke(_apiRequest, _callBack, CspHttpOpenApi.getClientConfig().isSecAuth());
    }
    
    /**
     * 异步请求发送数据
     * @param body 请求数据内容
     * @param _callBack 回调方法
     */
    public void asyncPostData(String body, ApiCallBack _callBack) {
    	asyncPostData(Scheme.HTTP, Method.POST_JSON, CspHttpOpenApi.getClientConfig().getServerAddress(),
    			CspHttpOpenApi.getClientConfig().getServerPath(), body, true, _callBack);
    }
    
    
    /**
     * 同步请求发送数据（参数配置是否不带SDK安全请求header）
     * @param scheme http scheme
     * @param method 请求方式
     * @param group_host 服务器分组地址，多个使用“;”分号分隔
     * @param path 请求路径
     * @param body 请求数据内容
     * @param isLoadBalance 是否负载均衡
     * @return
     */
    private ApiResponse syncPostData(Scheme scheme, Method method, String group_host, String path, String body, boolean isLoadBalance) throws Exception {
    	if(body==null) {
    		body = "";
    	}
    	ApiRequest _apiRequest = new ApiRequest(scheme, method, group_host, path, body.getBytes(), isLoadBalance);
        try {
        	return syncInvoke(_apiRequest, CspHttpOpenApi.getClientConfig().isSecAuth());
        } catch (Exception e) {
        	throw e;
        }
    }
    
    /**
     * 同步请求发送数据（不带SDK安全请求header）
     * @param scheme http scheme
     * @param method 请求方式
     * @param group_host 服务器分组地址，多个使用“;”分号分隔
     * @param path 请求路径
     * @param body 请求数据内容
     * @param isLoadBalance 是否负载均衡
     * @return
     */
    private ApiResponse syncPostDataNoSecAuth(Scheme scheme, Method method, String group_host, String path, String body, boolean isLoadBalance) throws Exception {
    	if(body==null) {
    		body = "";
    	}
    	ApiRequest _apiRequest = new ApiRequest(scheme, method, group_host, path, body.getBytes(), isLoadBalance);
        try {
        	return syncInvoke(_apiRequest, false);
        } catch (Exception e) {
        	throw e;
        }
    }

    /**
     * 同步请求发送数据
     * @param body 请求数据内容
     * @return
     * @throws Exception
     */
    public ApiResponse syncPostData(String body) throws Exception {
        Scheme scheme = Scheme.HTTP;
        if (CspHttpOpenApi.getClientConfig().isEnableHttps()) {
            scheme = Scheme.HTTPS;
        }
        return syncPostData(scheme, Method.POST_JSON, CspHttpOpenApi.getClientConfig().getServerAddress(), 
                CspHttpOpenApi.getClientConfig().getServerPath(), body, true);
    }
    
    /**
     * 同步请求发送数据
     * @param body 请求数据内容
     * @return
     * @throws Exception
     */
    public ApiResponse syncPostDataNoSecAuth(String body) throws Exception {
        Scheme scheme = Scheme.HTTP;
        if (CspHttpOpenApi.getClientConfig().isEnableHttps()) {
            scheme = Scheme.HTTPS;
        }
        return syncPostDataNoSecAuth(scheme, Method.POST_JSON, CspHttpOpenApi.getClientConfig().getServerAddress(), 
                CspHttpOpenApi.getClientConfig().getServerPath(), body, true);
    }
    
    /**
     * 释放资源
     */
    public void close() {
    	try {
    		shutdown();
    	} catch(Exception e){
    		
    	}
    }
}

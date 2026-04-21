package com.ruoyi.stress;

import org.apache.hc.client5.http.classic.methods.HttpGet;
import org.apache.hc.client5.http.classic.methods.HttpPost;
import org.apache.hc.client5.http.impl.classic.CloseableHttpClient;
import org.apache.hc.client5.http.impl.classic.CloseableHttpResponse;
import org.apache.hc.client5.http.impl.classic.HttpClients;
import org.apache.hc.core5.http.HttpEntity;
import org.apache.hc.core5.http.io.entity.EntityUtils;
import org.apache.hc.core5.http.io.entity.StringEntity;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.entity.SysUser;

public class HttpClient {
    private CloseableHttpClient httpClient;
    
    public HttpClient() {
        this.httpClient = HttpClients.createDefault();
    }
    
    public long executeRequest(TestConfig config) throws Exception {
        long startTime = System.currentTimeMillis();
        
        if ("GET".equalsIgnoreCase(config.getMethod())) {
            HttpGet httpGet = new HttpGet(config.getUrl());
            try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            EntityUtils.consume(response.getEntity());
            return System.currentTimeMillis() - startTime;
            }
        } else if ("POST".equalsIgnoreCase(config.getMethod())) {
            HttpPost httpPost = new HttpPost(config.getUrl());
            httpPost.setHeader("Content-Type", config.getContentType());
            if (config.getRequestBody() != null) {
                if(config.getUrl().contains("addUser")) {
                	SysUser user = new SysUser();
                    user.setDeptId(100L);
                    user.setUserType("00");
                    user.setEmail("pengdy@163.com");
                    user.setPhonenumber("13539876078");
                    user.setSex("0");
                    user.setAge(30);
                    user.setPassword("123456");
                    user.setSalt("111111");
                    user.setStatus("0");
                    user.setRemark("APIInsert");
                    
                	String phone = GenPhoneUtil.randomPhoneNumber();
                	user.setUserName(phone);
                    user.setLoginName(phone);
                    user.setEmail(phone + "@163.com");
                    user.setPhonenumber(phone);
                    
                    httpPost.setEntity(new StringEntity(JSONObject.toJSONString(user)));
                } else if(config.getUrl().contains("updateUser")) {
                	SysUser user = new SysUser();
                	user.setUserId(1000106L);
                    user.setEmail(GenPhoneUtil.randomPhoneNumber() + "@163.com");
                	httpPost.setEntity(new StringEntity(JSONObject.toJSONString(user)));
                } else {
                	httpPost.setEntity(new StringEntity(config.getRequestBody()));
                }
            }
            try (CloseableHttpResponse response = httpClient.execute(httpPost)) {
            	HttpEntity rspEntity = response.getEntity();
            	String rspstr = EntityUtils.toString(rspEntity, "UTF-8");
            	//System.out.println("------rspstr:" + rspstr);
            	EntityUtils.consume(rspEntity);
            	return System.currentTimeMillis() - startTime;
            }
        }
        
        return -1; // 方法不支持
    }
    
    public void close() throws Exception {
        if (httpClient != null) {
            httpClient.close();
        }
    }
}

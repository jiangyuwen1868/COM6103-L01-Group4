package com.jyw.csp.api.impl;

import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;
import org.springframework.util.StopWatch.TaskInfo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.anydef.gwapi.sdk.core.model.ApiResponse;
import com.jyw.csp.api.CspHttpOpenApi;
import com.jyw.csp.api.CspHttpOpenApiClent;
import com.jyw.csp.api.config.CspServicesConfig;
import com.jyw.csp.api.contant.CspContants;
import com.jyw.csp.api.exception.CspClientException;
import com.jyw.csp.api.exception.CspServerException;
import com.jyw.csp.api.exception.HsmCommandException;
import com.jyw.csp.datatransform.message.gw.GwRequestMsg;
import com.jyw.csp.datatransform.message.gw.GwResponseMsg;
import com.jyw.csp.util.Assert;
import com.jyw.csp.util.Base64;
import com.jyw.csp.util.Utils;
import com.jyw.csp.util.chiper.AESCipherUtils;
import com.jyw.csp.util.chiper.DESedeCipherUtils;
import com.jyw.csp.util.chiper.RSAUtils;
import com.jyw.csp.util.date.DateUtils;
import com.jyw.csp.util.string.StringUtils;

public class AbstractServiceImpl {
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	protected CspHttpOpenApiClent client;
	
	protected String tenantId;
	protected String appid;
	protected String appscrect;
	
	//public final String defualtDesignID = "csp";
	public final String defualtNodeID = "csp";
	public final String defualtKeyModelID = "csp";
	
	public final String CLIENT_PRIVATEKEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCVvvoWtifg8hBSzZeI0X4K88w/U6PxmEMMeMWjr18/f8kxSZZPkqDQNd1gTl15Lok2s35sryny/S17VuJbpIkkFk32UL4BDcCObdv2DC7IaECfVuePUdhT5vLumy2hgoUD2MeLrch/6j9PaOk9xwel0PjvvvmQTpfC+QayswUtAdb/LpGzg4aus6/OxCpob4jrGmOFR1jH2s7Osr13qulRH6U6PLHxmdDvtNiaPnulTDCzy5ROtCFGjUe/8B0oRcr15BfoVsfLjCPZPS6Pb3vUKOXpP2xCNkW/b22KrViYH6j/zKgod1IEMqBYSbWL0F1SZ3xXAsxsd7x1oTDxMDbpAgMBAAECggEBAI9OoEoh81IsdoC37oBqdGSI4vvr0KbCH9CMuCApEk9TvLilgusOGUaJdh3XgU8awQgPiTr7+3pm3yL9iE1JzlEx41SJ+vxJK/Fio7H6AL+cPdP/uEWJfyz0haLh8S5zGVcQHA/JtWrtQ798WFLd6ay/q6zNKEr5W/Wkmstj1d02QYemhdBzrq9sTREgTmPvE1v0YRPdztOX87Hj2CCc/+sZC8+GEeDd3BySNO/B98Csm7tyXX0q7uBxs7heNfohT1ewKUTMQd2eRSArBOGgSiusd4S0J1OvKzOOxskuB3AvxGHV49PzJm/S4SuWxs/mcv1yLJTwzElYzUDGZFYpkC0CgYEAyydOho7VqJJgwWkWxD9B8D7Rp08TOwhAbzEUXVM/dA4Clh1tdMRn2ApYfZy9rt6Fp8VYUNGcAeidJLf6+9eKvmIjYTr87l0ArvGA3Okzxd7GhkTyq9rfPl9u23kmTByg76DhToH4Kl5I+dr0Xhn5a+vIib+pUzygNMIvmbj+mVcCgYEAvLMUFVGtiEjLSlPXxvg2UE3qlSHvMJiZZp/m9uK6eCnyJxDRZ5smJ+QOseoKvzj89WX0LAyjPSU9qwM3AuvwrCvqmq5TCMajtQK09qleBsSkYczhoq1lbOiKCFgQaWSpQdTrjChwCOFfosw9r77QtvnSk6/rdmkmCgG9g49OSb8CgYAjkUQsRsGde9M4yX9U6IZQ2461LiD9ytoNHZilJjgLx5AoXAqX0PMlXdpUU6baANeBogluTOR1aRZqIVPt17TQ1DnBgiRaTyoZdurKwoFv+SrkwxmBgRZowyiTgEh2NuTuT0pjBlo+gLdiFzWFbCLBBEzd4RJyIkEiO4U98qeQjQKBgBZEs9wINYhdvs/YsSAj7pTvfP14cbe0anX37NO50fOYT6FYlL27S3dtrGZGjloeqYt0KqqAG0t995cU3AsWOOK4lAkJBXeFqZAU77IkLppx3kxEV2RRPiTqFFADxHwLiwXf0KRLqV0C67xUifsRc6QEO1t+aTUiF4Eaz3iVEtIVAoGBAL3nGnofcPOYGsfkouFYIgqa+19DP9YYx3/WOl6sUQsG8A514DXusrlMwNesl/UWqRFV7ZwyWQy2Vi8KPa4sWnXN+DHI18aWdu3O9mC98IuPgJ5BtHYeHHehGWIbcILlaOwdBpv1OPJGUE5GHFdd+AiZDgNUOiHj19R6fgV2x2fP";
	
	private static ThreadLocal<HashMap<String, String>> sessionContext = new ThreadLocal<HashMap<String, String>>();
	
	protected HashMap<String, String> getSessionMap() {
		return sessionContext.get();
	}

    protected JSONObject execute(Map<String, Object> params) throws Exception {
        return execute(params, "CSP000000");
    }

    protected JSONObject execute(Map<String, Object> params, String txcode) throws Exception {
    	StopWatch sw = new StopWatch();
    	try {
    		sw.start("pkg");
	    	if (params == null) {
	            params = new HashMap<String, Object>();
	        }
	    	
	    	// 参数非空校验
	    	Set<Entry<String, Object>> entrys = params.entrySet();
	    	for(Entry<String, Object> entry: entrys) {
	    		String name = entry.getKey();
	    		Object value = entry.getValue();
	    		Assert.notNull(value, "参数(" + name + ")不能为空");
	    	}
	        
	        JSONObject txJson = new JSONObject(true);
	        Map<String, Object> txHeaderMap = new HashMap<String, Object>();
	        txHeaderMap.put(CspContants.SYS_PKG_VERSION, CspContants.SYS_PKG_VERSION_01);
	        txHeaderMap.put(CspContants.SYS_REQ_TIME, new DateUtils().getYYYYMMDDhhmmssmis());
	        txHeaderMap.put(CspContants.SYS_EVT_TRACE_ID, Utils.GUID());
	        txHeaderMap.put(CspContants.SYS_SND_SERIAL_NO, CspContants.SYS_SND_SERIAL_NO_1);
	        txHeaderMap.put(CspContants.SYS_TX_CODE, CspServicesConfig.getTxCode(txcode));
	        txHeaderMap.put(CspContants.SYS_PKG_STS_TYPE, CspContants.SYS_PKG_STS_TYPE_00);
	        txJson.put(CspContants.TX_HEADER, txHeaderMap);
	        
	        JSONObject txBodyJson = new JSONObject(true);
	        //加密机指令总处理交易
	        if(CspContants.CSP_HSMCMD_MANAGER_TXCODES.contains(txcode)) {
	        	JSONObject mParams = new JSONObject(true);
	        	mParams.put("designID", appid);
	        	mParams.put("nodeID", defualtNodeID);
	        	mParams.put("keyModelID", defualtKeyModelID);
	        	mParams.put(CspContants.ENTITY_PARAMS, params);
	        	txBodyJson.put(CspContants.ENTITY, mParams);
	        } else {
	        	if(params.get("designID")==null 
	        			&& !CspContants.CSP_TS_TXCODES.contains(CspServicesConfig.getTxCode(txcode))
	        			&& !CspServicesConfig.getTxCode(txcode).startsWith(CspContants.CSP_SVS_TXCODES_PREFIX)) {
	        		params.put("designID", appid);
	        	}
	            if (params.get("designID") == null) {
	                params.put("designID", appid);
	            }
	            if(params.get("nodeID") == null) {
	            	params.put("nodeID", defualtNodeID);
	            }
	            if(params.get("keyModelID") == null) {
	            	params.put("keyModelID", defualtKeyModelID);
	            }
	        	txBodyJson.put(CspContants.ENTITY, params);
	        }
	        
	        JSONObject txBodyCom1 = new JSONObject(true);
	        txBodyCom1.put(CspContants.APPID, params.get("designID")==null?appid:params.get("designID"));
	        txBodyCom1.put(CspContants.CHANNELTXCODE, CspContants.CHANNELTXCODE_DEFUALT);
	        txBodyCom1.put(CspContants.TENANTID, tenantId);
	        
	        
	        txBodyJson.put(CspContants.COM1, txBodyCom1);
	        txJson.put(CspContants.TX_BODY, txBodyJson);
	        
	        String txMsg = JSON.toJSONString(txJson, SerializerFeature.WriteMapNullValue);
	        
	        logger.debug("-----txMsg-----\n" + txMsg);
	        sw.stop();
	        
	        GwRequestMsg requestMsg = new GwRequestMsg();
	        sw.start("enc");
	        String request_info = "";
	        
	        if(CspHttpOpenApi.getClientConfig()==null) {
	        	throw new CspClientException(CspClientException.CONFIG_EXCEPTION, "API初始化错误，请确认配置文件是否加载！");
	        }
	        //判断是否为数字信封加接密，是的话就直接做Base64加密
			if(CspContants.BIG_MSG_TXCODES.contains(CspServicesConfig.getTxCode(txcode))){
				requestMsg.setSec_version(CspContants.SEC_VERSION_01);
				requestMsg.setTx_code(CspServicesConfig.getTxCode(txcode));
				request_info = Base64.encodeString(txMsg.getBytes("UTF-8"));
			} else if(CspHttpOpenApi.getClientConfig().isSecAuth()) {  // 无认证直接做Base64加密
	        	requestMsg.setSec_version(CspContants.SEC_VERSION_02);
	        	request_info = DESedeCipherUtils.encryptBase64(appscrect, txMsg);
	        } else {
	        	requestMsg.setSec_version(CspContants.SEC_VERSION_01);
	        	request_info = Base64.encodeString(txMsg.getBytes("UTF-8"));
	        }
	        sw.stop();
	        
	        sw.start("sec_pkg");
	        requestMsg.setApp_id(appid);
	        
//	        MessageDigest md = MessageDigest.getInstance(CspContants.SHA1);
//	        md.update(txMsg.getBytes("UTF-8"));
//	        requestMsg.setSignature(Utils.bytes2Hex(md.digest()));
	        requestMsg.setSignature(RSAUtils.signBase64(txMsg.getBytes("UTF-8"), CLIENT_PRIVATEKEY));
	        requestMsg.setRequest_info(request_info);
	        
	        String sendData = JSON.toJSONString(requestMsg, SerializerFeature.WriteNullStringAsEmpty);
	
	        logger.debug("-----gwRequestMsg-----\n" + sendData);
	        sw.stop();
	        
	        if(client==null) {
	        	throw new CspClientException(CspClientException.CONFIG_EXCEPTION, "API初始化错误，请确认配置文件是否加载！");
	        }
	        
	        sw.start("post");
	        ApiResponse apiResponse = null;
	        if(CspContants.BIG_MSG_TXCODES.contains(CspServicesConfig.getTxCode(txcode))){
	        	apiResponse = client.syncPostDataNoSecAuth(sendData);
	        } else {
	        	apiResponse = client.syncPostData(sendData);
	        }
	        sw.stop();
	        if (apiResponse == null || apiResponse.getBody() == null) {
	            throw new CspServerException("密码服务返回数据为空，请检查配置是否正确及服务是否启动！");
	        }
	        
	        if(apiResponse.getStatusCode() != 200) {
	        	throw new CspServerException(apiResponse.getMessage() + "(" + apiResponse.getStatusCode() + ")");
	        }
	        String responseBody = new String(apiResponse.getBody(), CspHttpOpenApi.charset);
	        logger.debug("-----gwResponseMsg recvData-----\n" + responseBody);
	        GwResponseMsg responseMsg = JSONObject.parseObject(responseBody, GwResponseMsg.class);
	        String traceId = responseMsg.getSys_evt_trace_id();
	        String srvCostTime = responseMsg.getSrv_costtime();
	        HashMap<String, String> sessionMap = new HashMap<String, String>();
	        sessionMap.put(CspContants.TRACEID, traceId);
	        sessionMap.put(CspContants.SRVCOSTTIME, srvCostTime);
	        sessionContext.set(sessionMap);
	        
	        if(!CspContants.SUCCESS_CODE.equals(responseMsg.getReturn_code())) {
	        	throw new CspServerException(responseMsg.getReturn_code(), responseMsg.getReturn_message());
	        }
	       
	        sw.start("dec");
	        String recvData = "";
			//判断是否为数字信封加接密，是的话就直接做Base64解密
			if(CspContants.BIG_MSG_TXCODES.contains(CspServicesConfig.getTxCode(txcode))){
				recvData = Base64.decodeToString(responseMsg.getResponse_info());
			} else if(CspHttpOpenApi.getClientConfig().isSecAuth()) {
	        	recvData = AESCipherUtils.decryptBase64(appscrect, responseMsg.getResponse_info());
	        } else {
	        	recvData = Base64.decodeToString(responseMsg.getResponse_info());
	        }
	        sw.stop();
	        
	        logger.debug("-----gwResponseMsg plaintext-----\n" + recvData);
	        
	        
	        JSONObject jsonObj = JSON.parseObject(recvData);
	        
	        
	        JSONObject rspTxHeaderJson = jsonObj.getJSONObject(CspContants.TX_HEADER);
	        String respcode = rspTxHeaderJson.getString(CspContants.SYS_RESP_CODE);
	        String respdesc = rspTxHeaderJson.getString(CspContants.SYS_RESP_DESC);
	
	        if (!CspContants.SUCCESS_CODE.equals(respcode)) {
	            throw new CspServerException(respcode, respdesc);
	        }
	        
	        JSONObject rspTxBodyJson = jsonObj.getJSONObject(CspContants.TX_BODY);
	        JSONObject rspTxBodyEntityJson = rspTxBodyJson.getJSONObject(CspContants.ENTITY);
	        JSONObject rspHsmDataJson = null;
	        if(rspTxBodyEntityJson!=null) {
	        	rspHsmDataJson = rspTxBodyEntityJson.getJSONObject(CspContants.HSMRSPDATA);
		
		        if (rspHsmDataJson!=null && !CspContants.SUCCESS_HSM_CODE.equals(rspHsmDataJson.getString(CspContants.ERRCODE))) {
		            throw new HsmCommandException(respcode, respdesc + "@加密机处理出错，返回码：" + rspHsmDataJson.getString(CspContants.ERRCODE));
		        }
	        }
	        //加密机指令总处理交易
	        if(CspContants.CSP_HSMCMD_MANAGER_TXCODES.contains(txcode)) {
	        	return rspHsmDataJson;
	        }
	        return rspTxBodyEntityJson;
    	} catch(CspClientException e) {
    		throw e;
    	} catch(CspServerException e) {
    		throw e;
    	} catch(HsmCommandException e) {
    		throw e;
    	} catch(Exception e) {
    		logger.error("execute", e);
    		throw new CspClientException(CspClientException.UNKNOWN_EXCEPTION, e.getMessage());
    	} finally {
    		StringBuilder sb = new StringBuilder("StopWatch: running time = " + sw.getTotalTimeMillis() + " ms");
    		for (TaskInfo task : sw.getTaskInfo()) {
				sb.append("; [").append(task.getTaskName()).append("] took ").append(task.getTimeMillis()).append(" ms");
				long percent = Math.round(100.0 * task.getTimeMillis() / sw.getTotalTimeMillis());
				sb.append(" = ").append(percent).append('%');
			}
    		if(this.getSessionMap()==null) {
    			HashMap<String, String> sessionMap = new HashMap<String, String>();
    	        sessionMap.put(CspContants.TRACEID, "");
    	        sessionMap.put(CspContants.SRVCOSTTIME, "0");
    	        sessionContext.set(sessionMap);
    		}
    		logger.debug("costTime Info:" + sb.toString());
    	}
    }
    
    /**
     * 获取SVS响应参数信息
     * @param params
     * @return
     */
    private Map<String, String> getSvsRespParamMap(String params) {
		Map<String, String> map = new HashMap<String, String>();
		if(params!=null) {
			StringTokenizer st = new StringTokenizer(params, "&");
			while(st.hasMoreTokens()) {
				String str = st.nextToken();
				if(str!=null) {
					int idx = str.indexOf("=");
					if(idx!=-1) {
						map.put(str.substring(0,idx), str.substring(idx+1, str.length()));
					} else {
						map.put(str, "");
					}
				}
			}
		}
		return map;
	}
    
    /**
     * 通过keyName固定格式：应用编号.节点编号.密钥模板标识
     * 兼容处理API所对应的 designID nodeID keyModelID 三个参数
     * @param keyName
     * @return
     */
    public String[] getAPIParamByKeyName(String keyName) {
    	if(StringUtils.isEmpty(keyName)) {
    		return new String[]{"", "", ""};
    	}
    	
    	String[] params = keyName.split("\\.");
    	if(params.length == 1) {
    		return new String[]{params[0], params[0], params[0]};
    	}
    	if(params.length == 2) {
    		return new String[]{params[0], params[1], params[1]};
    	}
    	if(params.length >= 3) {
    		return new String[]{params[0], params[1], params[2]};
    	}
    	
    	return params;
    }
}

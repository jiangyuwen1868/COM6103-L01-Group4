package com.jyw.csp.api.config;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jyw.csp.util.string.StringUtils;

public class CspServicesConfig {
	private static final Logger logger = LoggerFactory.getLogger(CspServicesConfig.class);
	private static Map<String, String> servicesTxCodeMap = new HashMap<String, String>();
	
	static {
		init();
		
		logger.debug("----init CspServicesConfig size:" + servicesTxCodeMap.size());
	}
	
	private static void init() {
		try {
			servicesTxCodeMap.put("applyKeyByZMK", "CSP100001");
			servicesTxCodeMap.put("getDeviceInfo", "CSP200003");
			servicesTxCodeMap.put("deviceRegistration", "CSP200004");
			servicesTxCodeMap.put("submitDeviceEventInfo", "CSP200005");
			
		} catch(Exception e) {
		}
	}
	
	/**
	 * API接口转义服务码
	 * @param mothodName 接口方法名称
	 * @return
	 */
	public static String getTxCode(String mothodName) {
		String txcode = servicesTxCodeMap.get(mothodName);
		if(StringUtils.isEmpty(txcode)) {
			return mothodName;
		}
		
		return txcode;
	}
}

package com.jyw.csp.api.config;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jyw.csp.util.string.StringUtils;

/**
 * （金融数据）密码机密钥类型配置表
 * @author Administrator
 *
 */
public class CspHsmKeyTypeConfig {

	private static final Logger logger = LoggerFactory.getLogger(CspHsmKeyTypeConfig.class);
	private static Map<String, String> keyTypesMap = new HashMap<String, String>();
	
	static {
		init();
		
		logger.debug("----init CspHsmKeyTypeConfig size:" + keyTypesMap.size());
	}
	
	private static void init() {
		try {
			keyTypesMap.put("ZMK", "000"); // 区域主密钥
			keyTypesMap.put("KEK", "000"); // 密钥加密密钥
			keyTypesMap.put("ZMK(Comp)", "100"); // 区域主密钥 Comp-成分
			keyTypesMap.put("KML", "200"); // 
			keyTypesMap.put("ZPK", "001"); // 区域PIN加密密钥
			keyTypesMap.put("PVK", "002"); // PIN验证密钥
			keyTypesMap.put("TPK", "002"); // 终端PIN加密密钥
			keyTypesMap.put("TMK", "002"); // 终端主密钥
			keyTypesMap.put("CVK", "402"); // 卡验证密钥
			keyTypesMap.put("CSCK", "402"); // 
			keyTypesMap.put("TAK", "003"); // 终端认证密钥
			keyTypesMap.put("WWK", "006"); //
			keyTypesMap.put("EDK", "007"); //
			keyTypesMap.put("ZAK", "008"); // 区域认证密钥
			keyTypesMap.put("BDK", "009"); //
			keyTypesMap.put("MK-AC", "109"); // 用于计算认证密文的主密钥
			keyTypesMap.put("MDK", "109"); // 
			keyTypesMap.put("MK-SMI", "209"); // 用于安全报文完整性的主密钥
			keyTypesMap.put("MK-SMC", "309"); // 用于安全报文机密性的主密钥
			keyTypesMap.put("MK-DAK", "409"); // 用于计算数据认证码的主密钥
			keyTypesMap.put("MK-DN", "509"); // 用于产生动态数字的主密钥
			keyTypesMap.put("ZEK", "00A"); // 区域加密密钥
			keyTypesMap.put("DEK", "00A"); // 数据加密密钥
			keyTypesMap.put("TEK", "00B"); // 终端加密密钥
			keyTypesMap.put("RSA-SK", "00C"); //
			keyTypesMap.put("SM2", "010"); //
			keyTypesMap.put("KMC", "011"); // IC卡主控密钥
		} catch(Exception e) {
		}
	}
	
	/**
	 * 根据密钥类型简称编码获取对应密码机指令密钥类型标识码
	 * @param keyType
	 * @return
	 */
	public static String getKeyType(String keyType) {
		if(StringUtils.isEmpty(keyType)) {
			return keyType;
		}
		String hsmKeyType = keyTypesMap.get(keyType.toUpperCase());
		if(StringUtils.isEmpty(hsmKeyType)) {
			return keyType;
		}
		
		return hsmKeyType;
	}
}

package com.jyw.csp.datatransform.message.gw;

public class GwRequestMsg {

	private String app_id;
	private String signature;
	private String sec_version;
	private String request_info;
	//用于判断是否为数字信封加解密大报文
	private String tx_code;
	
	public String getApp_id() {
		return app_id;
	}
	public void setApp_id(String app_id) {
		this.app_id = app_id;
	}
	public String getSignature() {
		return signature;
	}
	public void setSignature(String signature) {
		this.signature = signature;
	}
	public String getSec_version() {
		return sec_version;
	}
	public void setSec_version(String sec_version) {
		this.sec_version = sec_version;
	}
	public String getRequest_info() {
		return request_info;
	}
	public void setRequest_info(String request_info) {
		this.request_info = request_info;
	}
	public String getTx_code() {
		return tx_code;
	}
	public void setTx_code(String tx_code) {
		this.tx_code = tx_code;
	}
}

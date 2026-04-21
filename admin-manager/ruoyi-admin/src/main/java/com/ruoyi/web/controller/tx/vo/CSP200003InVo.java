package com.ruoyi.web.controller.tx.vo;

public class CSP200003InVo extends BaseInVo implements TxRequestBodyEntity {
    private static final long serialVersionUID = 1L;

    private String deviceId;

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}
    
    
}

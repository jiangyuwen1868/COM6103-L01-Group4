package com.ruoyi.web.controller.tx.vo;

import java.math.BigDecimal;

public class CSP200005InVo extends BaseInVo implements TxRequestBodyEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String device_sn;
	private String data_type;
	private String data_value;
	private String data_unit;
	private String data_time;
	private String upload_time;
	private String instruction_content;
	private String instruction_status;
	private String signal_strength;
	private BigDecimal battery_level;
	private String remark;
	public String getDevice_sn() {
		return device_sn;
	}
	public void setDevice_sn(String device_sn) {
		this.device_sn = device_sn;
	}
	public String getData_type() {
		return data_type;
	}
	public void setData_type(String data_type) {
		this.data_type = data_type;
	}
	public String getData_value() {
		return data_value;
	}
	public void setData_value(String data_value) {
		this.data_value = data_value;
	}
	public String getData_unit() {
		return data_unit;
	}
	public void setData_unit(String data_unit) {
		this.data_unit = data_unit;
	}
	public String getData_time() {
		return data_time;
	}
	public void setData_time(String data_time) {
		this.data_time = data_time;
	}
	public String getUpload_time() {
		return upload_time;
	}
	public void setUpload_time(String upload_time) {
		this.upload_time = upload_time;
	}
	public String getInstruction_content() {
		return instruction_content;
	}
	public void setInstruction_content(String instruction_content) {
		this.instruction_content = instruction_content;
	}
	public String getInstruction_status() {
		return instruction_status;
	}
	public void setInstruction_status(String instruction_status) {
		this.instruction_status = instruction_status;
	}
	public String getSignal_strength() {
		return signal_strength;
	}
	public void setSignal_strength(String signal_strength) {
		this.signal_strength = signal_strength;
	}
	public BigDecimal getBattery_level() {
		return battery_level;
	}
	public void setBattery_level(BigDecimal battery_level) {
		this.battery_level = battery_level;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}

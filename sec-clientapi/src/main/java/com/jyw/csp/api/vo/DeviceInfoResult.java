package com.jyw.csp.api.vo;

import java.math.BigDecimal;

public class DeviceInfoResult extends Result{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String device_sn;
    private String device_model;
    private String device_type;
    private String manufacturer;
    private String production_date;
    private String install_address;
    private String user_name;
    private String user_phone;
    private String user_id_card;
    private String install_date;
    private String activation_date;
    private String status;
    private String last_online_time;
    private BigDecimal total_water_usage;
    private BigDecimal remaining_amount;
    private BigDecimal warning_threshold;
    private String firmware_version;
    private String create_time;
    private String update_time;
    private String remark;
	public String getDevice_sn() {
		return device_sn;
	}
	public void setDevice_sn(String device_sn) {
		this.device_sn = device_sn;
	}
	public String getDevice_model() {
		return device_model;
	}
	public void setDevice_model(String device_model) {
		this.device_model = device_model;
	}
	public String getDevice_type() {
		return device_type;
	}
	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getProduction_date() {
		return production_date;
	}
	public void setProduction_date(String production_date) {
		this.production_date = production_date;
	}
	public String getInstall_address() {
		return install_address;
	}
	public void setInstall_address(String install_address) {
		this.install_address = install_address;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_phone() {
		return user_phone;
	}
	public void setUser_phone(String user_phone) {
		this.user_phone = user_phone;
	}
	public String getUser_id_card() {
		return user_id_card;
	}
	public void setUser_id_card(String user_id_card) {
		this.user_id_card = user_id_card;
	}
	public String getInstall_date() {
		return install_date;
	}
	public void setInstall_date(String install_date) {
		this.install_date = install_date;
	}
	public String getActivation_date() {
		return activation_date;
	}
	public void setActivation_date(String activation_date) {
		this.activation_date = activation_date;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLast_online_time() {
		return last_online_time;
	}
	public void setLast_online_time(String last_online_time) {
		this.last_online_time = last_online_time;
	}
	public BigDecimal getTotal_water_usage() {
		return total_water_usage;
	}
	public void setTotal_water_usage(BigDecimal total_water_usage) {
		this.total_water_usage = total_water_usage;
	}
	public BigDecimal getRemaining_amount() {
		return remaining_amount;
	}
	public void setRemaining_amount(BigDecimal remaining_amount) {
		this.remaining_amount = remaining_amount;
	}
	public BigDecimal getWarning_threshold() {
		return warning_threshold;
	}
	public void setWarning_threshold(BigDecimal warning_threshold) {
		this.warning_threshold = warning_threshold;
	}
	public String getFirmware_version() {
		return firmware_version;
	}
	public void setFirmware_version(String firmware_version) {
		this.firmware_version = firmware_version;
	}
	public String getCreate_time() {
		return create_time;
	}
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	public String getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(String update_time) {
		this.update_time = update_time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
}

package com.jyw.csp.api.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.jyw.csp.api.CspHttpOpenApiClent;
import com.jyw.csp.api.contant.CspContants;
import com.jyw.csp.api.exception.CspClientException;
import com.jyw.csp.api.exception.CspServerException;
import com.jyw.csp.api.inf.CspServicesInf;
import com.jyw.csp.api.vo.DevcieEventInfo;
import com.jyw.csp.api.vo.DeviceInfo;
import com.jyw.csp.api.vo.DeviceInfoResult;
import com.jyw.csp.api.vo.Result;

public class CspServicesAPI extends AbstractServiceImpl implements CspServicesInf {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    public CspServicesAPI(CspHttpOpenApiClent mClient, String mTenantId, String mAppid, String mAppscrect) {
        this.client = mClient;
        this.tenantId = mTenantId;
        this.appid = mAppid;
        this.appscrect = mAppscrect;
    }

    @Override
    public DeviceInfoResult getDeviceInfo(String deviceId) {

    	DeviceInfoResult result = new DeviceInfoResult();
        try {
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("deviceId", deviceId);

            JSONObject jsonObj = execute(params, "getDeviceInfo");
            
            String device_sn = jsonObj.getString("device_sn");
            String device_model = jsonObj.getString("device_model");
            String device_type = jsonObj.getString("device_type");
            String manufacturer = jsonObj.getString("manufacturer");
            String production_date = jsonObj.getString("production_date");
            String install_address = jsonObj.getString("install_address");
            String user_name = jsonObj.getString("user_name");
            String user_phone = jsonObj.getString("user_phone");
            String user_id_card = jsonObj.getString("user_id_card");
            String install_date = jsonObj.getString("install_date");
            String activation_date = jsonObj.getString("activation_date");
            String status = jsonObj.getString("status");
            String last_online_time = jsonObj.getString("last_online_time");
            BigDecimal total_water_usage = jsonObj.getBigDecimal("total_water_usage");
            BigDecimal remaining_amount = jsonObj.getBigDecimal("remaining_amount");
            BigDecimal warning_threshold = jsonObj.getBigDecimal("warning_threshold");
            String firmware_version = jsonObj.getString("firmware_version");
            String create_time = jsonObj.getString("create_time");
            String update_time = jsonObj.getString("update_time");
            String remark = jsonObj.getString("remark");

            result.setDevice_sn(device_sn);
            result.setDevice_model(device_model);
            result.setDevice_type(device_type);
            result.setManufacturer(manufacturer);
            result.setProduction_date(production_date);
            result.setInstall_address(install_address);
            result.setUser_name(user_name);
            result.setUser_phone(user_phone);
            result.setUser_id_card(user_id_card);
            result.setInstall_date(install_date);
            result.setActivation_date(activation_date);
            result.setStatus(status);
            result.setLast_online_time(last_online_time);
            result.setTotal_water_usage(total_water_usage);
            result.setRemaining_amount(remaining_amount);
            result.setWarning_threshold(warning_threshold);
            result.setFirmware_version(firmware_version);
            result.setCreate_time(create_time);
            result.setUpdate_time(update_time);
            result.setRemark(remark);
            
        } catch (CspClientException e) {
            result.setSuccess(false);
            result.setErrcode(e.getCode());
            result.setErrmsg(e.getMessage());
        } catch (CspServerException e) {
            result.setSuccess(false);
            result.setErrcode(e.getCode());
            result.setErrmsg(e.getMessage());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setErrcode(CspClientException.UNKNOWN_EXCEPTION);
            result.setErrmsg("未知错误");
            result.setExceptionMessage(e.getMessage());
            result.setExceptionStackTrace(e);
            logger.error("getDeviceInfo", e);
        } finally {
            if (this.getSessionMap() != null) {
                result.setTraceId(this.getSessionMap().get(CspContants.TRACEID));
                result.setSvrCostTime(this.getSessionMap().get(CspContants.SRVCOSTTIME));
            }
        }

        return result;
    }
    
    @Override
    public Result deviceRegistration(DeviceInfo deviceInfo) {
    	Result result = new Result();
    	
    	try {
    		Map<String, Object> params = new HashMap<String, Object>();

    		params.put("device_sn", deviceInfo.getDevice_sn());
    		params.put("device_model", deviceInfo.getDevice_model());
    		params.put("device_type", deviceInfo.getDevice_type());
    		params.put("manufacturer", deviceInfo.getManufacturer());
    		params.put("production_date", deviceInfo.getProduction_date());
    		params.put("install_address", deviceInfo.getInstall_address());
    		params.put("user_name", deviceInfo.getUser_name());
    		params.put("user_phone", deviceInfo.getUser_phone());
    		params.put("user_id_card", deviceInfo.getUser_id_card());
    		params.put("install_date", deviceInfo.getInstall_date());
    		params.put("activation_date", deviceInfo.getActivation_date());
//    		params.put("last_online_time", deviceInfo.getLast_online_time());
//    		params.put("total_water_usage", deviceInfo.getTotal_water_usage());
//    		params.put("remaining_amount", deviceInfo.getRemaining_amount());
//    		params.put("warning_threshold", deviceInfo.getWarning_threshold());
    		params.put("firmware_version", deviceInfo.getFirmware_version());
    		
    		params.put("remark", deviceInfo.getRemark());

            JSONObject jsonObj = execute(params, "deviceRegistration");
            String device_sn = jsonObj.getString("device_sn");
            
    	} catch (CspClientException e) {
            result.setSuccess(false);
            result.setErrcode(e.getCode());
            result.setErrmsg(e.getMessage());
        } catch (CspServerException e) {
            result.setSuccess(false);
            result.setErrcode(e.getCode());
            result.setErrmsg(e.getMessage());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setErrcode(CspClientException.UNKNOWN_EXCEPTION);
            result.setErrmsg("未知错误");
            result.setExceptionMessage(e.getMessage());
            result.setExceptionStackTrace(e);
            logger.error("deviceRegistration", e);
        } finally {
            if (this.getSessionMap() != null) {
                result.setTraceId(this.getSessionMap().get(CspContants.TRACEID));
                result.setSvrCostTime(this.getSessionMap().get(CspContants.SRVCOSTTIME));
            }
        }
    	
    	return result;
    }
    
    @Override
    public Result submitDeviceEventInfo(DevcieEventInfo deviceEventInfo) {
    	Result result = new Result();
    	
    	try {
    		Map<String, Object> params = new HashMap<String, Object>();

    		params.put("device_sn", deviceEventInfo.getDevice_sn());
    		params.put("data_type", deviceEventInfo.getData_type());
    		params.put("data_value", deviceEventInfo.getData_value());
    		params.put("data_unit", deviceEventInfo.getData_unit());
    		params.put("data_time", deviceEventInfo.getData_time());
    		params.put("upload_time", deviceEventInfo.getUpload_time());
    		if("5".equals(deviceEventInfo.getData_type())) {
	    		params.put("instruction_content", deviceEventInfo.getInstruction_content());
	    		params.put("instruction_status", deviceEventInfo.getInstruction_status());
    		}
    		params.put("signal_strength", deviceEventInfo.getSignal_strength());
    		params.put("battery_level", deviceEventInfo.getBattery_level());
    		params.put("remark", deviceEventInfo.getRemark());
    		
    		JSONObject jsonObj = execute(params, "submitDeviceEventInfo");
            String device_sn = jsonObj.getString("device_sn");
            
    	} catch (CspClientException e) {
            result.setSuccess(false);
            result.setErrcode(e.getCode());
            result.setErrmsg(e.getMessage());
        } catch (CspServerException e) {
            result.setSuccess(false);
            result.setErrcode(e.getCode());
            result.setErrmsg(e.getMessage());
        } catch (Exception e) {
            result.setSuccess(false);
            result.setErrcode(CspClientException.UNKNOWN_EXCEPTION);
            result.setErrmsg("未知错误");
            result.setExceptionMessage(e.getMessage());
            result.setExceptionStackTrace(e);
            logger.error("submitDeviceEventInfo", e);
        } finally {
            if (this.getSessionMap() != null) {
                result.setTraceId(this.getSessionMap().get(CspContants.TRACEID));
                result.setSvrCostTime(this.getSessionMap().get(CspContants.SRVCOSTTIME));
            }
        }
    	
    	
    	return result;
    }
}

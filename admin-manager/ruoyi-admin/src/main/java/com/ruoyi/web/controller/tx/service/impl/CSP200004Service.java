package com.ruoyi.web.controller.tx.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.WaterMeterDevice;
import com.ruoyi.system.exception.CommonRuntimeException;
import com.ruoyi.system.exception.CspException;
import com.ruoyi.system.service.IWaterMeterDataLogService;
import com.ruoyi.system.service.IWaterMeterDeviceService;
import com.ruoyi.system.util.DateUtils;
import com.ruoyi.web.controller.tx.service.CspService;
import com.ruoyi.web.controller.tx.vo.CSP200004InVo;
import com.ruoyi.web.controller.tx.vo.CSP200004OutVo;
import com.ruoyi.web.controller.tx.vo.TxRequestBodyEntity;
import com.ruoyi.web.controller.tx.vo.TxRequestInfo;
import com.ruoyi.web.controller.tx.vo.TxResponseBodyEntity;
import com.ruoyi.web.controller.tx.vo.TxResponseInfo;

/**
 * 
 */
@Service("CSP200004")
public class CSP200004Service extends BaseCspService implements CspService {
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private IWaterMeterDeviceService waterMeterDeviceService;
    
    @Autowired
    private IWaterMeterDataLogService waterMeterDataLogService;

    @Override
    public void process(TxRequestInfo<TxRequestBodyEntity> txRequestInfo, TxResponseInfo<TxResponseBodyEntity> txResponseInfo) {
        try {
	    	CSP200004InVo inVo = (CSP200004InVo) txRequestInfo.getBody().getEntity();
	    	
	        WaterMeterDevice waterMeterDevice = new WaterMeterDevice();
	        waterMeterDevice.setDeviceSn(inVo.getDevice_sn());
	        
	        List<WaterMeterDevice> deviceList = waterMeterDeviceService.selectWaterMeterDeviceList(waterMeterDevice);
	        
	        if(deviceList != null && !deviceList.isEmpty()) {
	        	throw new CspException(120001, "设备["+inVo.getDevice_sn()+"]已注册");
	        }
	        
	        waterMeterDevice.setDeviceModel(inVo.getDevice_model());
	        waterMeterDevice.setDeviceType(Long.valueOf(inVo.getDevice_type()));
	        waterMeterDevice.setManufacturer(inVo.getManufacturer());
	        if(StringUtils.isNotEmpty(inVo.getProduction_date()))
	        waterMeterDevice.setProductionDate(DateUtils.parseDate(inVo.getProduction_date()));
	        waterMeterDevice.setInstallAddress(inVo.getInstall_address());
	        waterMeterDevice.setUserName(inVo.getUser_name());
	        waterMeterDevice.setUserPhone(inVo.getUser_phone());
	        waterMeterDevice.setUserIdCard(inVo.getUser_id_card());
	        if(StringUtils.isNotEmpty(inVo.getInstall_date()))
	        waterMeterDevice.setInstallDate(DateUtils.parseDate(inVo.getInstall_date()));
	        if(StringUtils.isNotEmpty(inVo.getActivation_date()))
	        waterMeterDevice.setActivationDate(DateUtils.parseDate(inVo.getActivation_date()));
	        waterMeterDevice.setStatus(1L);
	        if(StringUtils.isNotEmpty(inVo.getLast_online_time()))
	        waterMeterDevice.setLastOnlineTime(DateUtils.parseDate(inVo.getLast_online_time()));
	        waterMeterDevice.setFirmwareVersion(inVo.getFirmware_version());
	        waterMeterDevice.setCreateTime(new Date());
	        waterMeterDevice.setRemark(inVo.getRemark());
	        
	        
	        int ret = waterMeterDeviceService.insertWaterMeterDevice(waterMeterDevice);
	        
	        if(ret < 0) {
	        	throw new CspException(110002, "设备注册失败");
	        }
	
	        CSP200004OutVo outVo = new CSP200004OutVo();
	        outVo.setDevice_sn(waterMeterDevice.getDeviceSn());
	
	        txResponseInfo.getBody().setEntity(outVo);
        } catch (Exception e) {
        	e.printStackTrace();
        	throw new CspException(1199999, e.getMessage());
		}
    }
}

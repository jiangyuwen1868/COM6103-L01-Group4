package com.ruoyi.web.controller.tx.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.WaterMeterDataLog;
import com.ruoyi.system.domain.WaterMeterDevice;
import com.ruoyi.system.exception.CspException;
import com.ruoyi.system.service.IWaterMeterDataLogService;
import com.ruoyi.system.service.IWaterMeterDeviceService;
import com.ruoyi.system.util.DateUtils;
import com.ruoyi.web.controller.tx.service.CspService;
import com.ruoyi.web.controller.tx.vo.CSP200005InVo;
import com.ruoyi.web.controller.tx.vo.CSP200005OutVo;
import com.ruoyi.web.controller.tx.vo.TxRequestBodyEntity;
import com.ruoyi.web.controller.tx.vo.TxRequestInfo;
import com.ruoyi.web.controller.tx.vo.TxResponseBodyEntity;
import com.ruoyi.web.controller.tx.vo.TxResponseInfo;

/**
 * 
 */
@Service("CSP200005")
public class CSP200005Service extends BaseCspService implements CspService {
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private IWaterMeterDeviceService waterMeterDeviceService;
    
    @Autowired
    private IWaterMeterDataLogService waterMeterDataLogService;

    @Override
    public void process(TxRequestInfo<TxRequestBodyEntity> txRequestInfo, TxResponseInfo<TxResponseBodyEntity> txResponseInfo) {
    	try {
	        CSP200005InVo inVo = (CSP200005InVo) txRequestInfo.getBody().getEntity();
	        
	        WaterMeterDevice waterMeterDevice = new WaterMeterDevice();
	        waterMeterDevice.setDeviceSn(inVo.getDevice_sn());
	        
	        List<WaterMeterDevice> deviceList = waterMeterDeviceService.selectWaterMeterDeviceList(waterMeterDevice);
	        
	        if(deviceList == null || deviceList.isEmpty()) {
	        	throw new CspException(110001, "设备["+inVo.getDevice_sn()+"]不存在");
	        }
	
	        WaterMeterDataLog waterMeterDataLog = new WaterMeterDataLog();
	        waterMeterDataLog.setDeviceSn(inVo.getDevice_sn());
	        waterMeterDataLog.setDataType(Long.valueOf(inVo.getData_type()));
	        waterMeterDataLog.setDataValue(inVo.getData_value());
	        waterMeterDataLog.setDataUnit(inVo.getData_unit());
	        if(StringUtils.isNotEmpty(inVo.getData_time()))
	        	waterMeterDataLog.setDataTime(DateUtils.parseDate(inVo.getData_time()));
	        if(StringUtils.isNotEmpty(inVo.getUpload_time()))
	        	waterMeterDataLog.setUploadTime(DateUtils.parseDate(inVo.getUpload_time()));
	        if("5".equals(inVo.getData_type())) {
	        	waterMeterDataLog.setInstructionContent(inVo.getInstruction_content());
	        	waterMeterDataLog.setInstructionStatus(Long.valueOf(inVo.getInstruction_status()));
	        }
	        waterMeterDataLog.setSignalStrength(inVo.getSignal_strength());
	        waterMeterDataLog.setBatteryLevel(inVo.getBattery_level());
	        waterMeterDataLog.setRemark(inVo.getRemark());
	        waterMeterDataLog.setCreateTime(new Date());
	
	        
	        int ret = waterMeterDataLogService.insertWaterMeterDataLog(waterMeterDataLog);
	        
	        if(ret < 0) {
	        	throw new CspException(110003, "设备报送信息失败");
	        }
	
	        CSP200005OutVo outVo = new CSP200005OutVo();
	        outVo.setDevice_sn(waterMeterDataLog.getDeviceSn());
	
	        txResponseInfo.getBody().setEntity(outVo);
    	} catch (Exception e) {
    		e.printStackTrace();
        	throw new CspException(1199999, e.getMessage());
		}
    }
}

package com.ruoyi.web.controller.tx.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.domain.WaterMeterDevice;
import com.ruoyi.system.exception.CspException;
import com.ruoyi.system.service.IWaterMeterDataLogService;
import com.ruoyi.system.service.IWaterMeterDeviceService;
import com.ruoyi.system.util.DateUtils;
import com.ruoyi.web.controller.tx.service.CspService;
import com.ruoyi.web.controller.tx.vo.CSP200003InVo;
import com.ruoyi.web.controller.tx.vo.CSP200003OutVo;
import com.ruoyi.web.controller.tx.vo.TxRequestBodyEntity;
import com.ruoyi.web.controller.tx.vo.TxRequestInfo;
import com.ruoyi.web.controller.tx.vo.TxResponseBodyEntity;
import com.ruoyi.web.controller.tx.vo.TxResponseInfo;

/**
 * 
 */
@Service("CSP200003")
public class CSP200003Service extends BaseCspService implements CspService {
    private static final long serialVersionUID = 1L;
    
    @Autowired
    private IWaterMeterDeviceService waterMeterDeviceService;
    
    @Autowired
    private IWaterMeterDataLogService waterMeterDataLogService;

    @Override
    public void process(TxRequestInfo<TxRequestBodyEntity> txRequestInfo, TxResponseInfo<TxResponseBodyEntity> txResponseInfo) {
        CSP200003InVo inVo = (CSP200003InVo) txRequestInfo.getBody().getEntity();
        WaterMeterDevice waterMeterDevice = new WaterMeterDevice();
        waterMeterDevice.setDeviceSn(inVo.getDeviceId());
        
        List<WaterMeterDevice> deviceList = waterMeterDeviceService.selectWaterMeterDeviceList(waterMeterDevice);
        
        if(deviceList == null || deviceList.isEmpty()) {
        	throw new CspException(110001, "设备["+inVo.getDeviceId()+"]不存在");
        }
        
        WaterMeterDevice device = deviceList.get(0);

        CSP200003OutVo outVo = new CSP200003OutVo();
        outVo.setDevice_sn(device.getDeviceSn());
        outVo.setDevice_model(device.getDeviceModel());
        outVo.setDevice_type(String.valueOf(device.getDeviceType()));
        outVo.setManufacturer(device.getManufacturer());
        outVo.setProduction_date(DateUtils.formatDateTime(device.getProductionDate()));
        outVo.setInstall_address(device.getInstallAddress());
        outVo.setUser_name(device.getUserName());
        outVo.setUser_phone(device.getUserPhone());
        outVo.setUser_id_card(device.getUserIdCard());
        outVo.setInstall_date(DateUtils.formatDateTime(device.getInstallDate()));
        outVo.setActivation_date(DateUtils.formatDateTime(device.getActivationDate()));
        outVo.setStatus(String.valueOf(device.getStatus()));
        outVo.setLast_online_time(DateUtils.formatDateTime(device.getLastOnlineTime()));
        outVo.setTotal_water_usage(device.getTotalWaterUsage());
        outVo.setRemaining_amount(device.getRemainingAmount());
        outVo.setWarning_threshold(device.getWarningThreshold());
        outVo.setFirmware_version(device.getFirmwareVersion());
        outVo.setCreate_time(DateUtils.formatDateTime(device.getCreateTime()));
        outVo.setUpdate_time(DateUtils.formatDateTime(device.getUpdateTime()));
        outVo.setRemark(device.getRemark());


        txResponseInfo.getBody().setEntity(outVo);
    }
}

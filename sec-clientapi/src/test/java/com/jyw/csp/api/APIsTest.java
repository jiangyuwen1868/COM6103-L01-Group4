package com.jyw.csp.api;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.jyw.csp.api.config.ClientConfig;
import com.jyw.csp.api.impl.CspServicesAPI;
import com.jyw.csp.api.vo.DevcieEventInfo;
import com.jyw.csp.api.vo.DeviceInfo;
import com.jyw.csp.api.vo.DeviceInfoResult;
import com.jyw.csp.api.vo.Result;

public class APIsTest {
    String tenantId = "csp";
    String appId = "csp";
    String appScrect = "404142434445464748494A4B4C4D4E4F";
//    String serverAddress = "127.0.0.1:8201";
    String serverAddress = "47.115.53.79:8201";


    //curl -X POST -H "Content-Type:application/json" http://10.193.60.21:8201/cspGateway/mainAccess
    //curl -X POST -H "Content-Type:application/json" http://10.193.60.21:8201/cspGateway/serviceManager/cacheRefresh/appSrvAuth
    //CspHttpOpenApi api = new CspHttpOpenApi(tenantId, appId, appScrect);

    CspHttpOpenApi api = null;
    CspServicesAPI servicesAPI = null;

    @Before
    public void before() {
        ClientConfig clientConfig = new ClientConfig();
        clientConfig.setTenantId(tenantId);
        clientConfig.setAppId(appId);
        clientConfig.setAppScrect(appScrect);
        clientConfig.setServerAddress(serverAddress);

//        api = new CspHttpOpenApi();
		api = new CspHttpOpenApi(clientConfig);
        servicesAPI = api.getServicesAPI();
    }

    @Test
    public void getDeviceInfo() {
    	DeviceInfoResult result = servicesAPI.getDeviceInfo("SN2026001001-3");
    	System.out.println(JSON.toJSONString(result, SerializerFeature.PrettyFormat));
    }
    
    @Test
    public void deviceRegistration() {
    	DeviceInfo deviceInfo =  new DeviceInfo();
    	deviceInfo.setDevice_sn("SN2026001003-4");
    	deviceInfo.setDevice_model("NB-IoT-100");
    	deviceInfo.setDevice_type("2");
    	deviceInfo.setManufacturer("深圳水务科技");
    	deviceInfo.setProduction_date("2025-01-10");
    	deviceInfo.setInstall_address("北京市海淀区中关村南大街5号");
    	deviceInfo.setUser_name("张三3");
    	deviceInfo.setUser_phone("13800138101");
    	deviceInfo.setUser_id_card("110108199001011234");
    	deviceInfo.setInstall_date("2025-02-15");
    	deviceInfo.setActivation_date("2026-02-15 09:30:00");
//    	deviceInfo.setLast_online_time("");
//    	deviceInfo.setTotal_water_usage(null);
//    	deviceInfo.setRemaining_amount(null);
//    	deviceInfo.setWarning_threshold(null);
    	deviceInfo.setFirmware_version("V1.0.2");
    	deviceInfo.setRemark("api reg");
    	
    	Result result = servicesAPI.deviceRegistration(deviceInfo);
    	System.out.println(JSON.toJSONString(result, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));
    }
    
    @Test
    public void submitDeviceEventInfo() {
    	DevcieEventInfo deviceEventInfo = new DevcieEventInfo();
    	deviceEventInfo.setDevice_sn("SN2026001001-3");
    	deviceEventInfo.setData_type("1");
    	deviceEventInfo.setData_value("0.8");
    	deviceEventInfo.setData_unit("立方米");
    	deviceEventInfo.setData_time("2026-03-10 10:19:00");
    	deviceEventInfo.setUpload_time("2026-03-10 10:20:00");
//    	deviceEventInfo.setInstruction_content(appId);
//    	deviceEventInfo.setInstruction_status(appId);
    	deviceEventInfo.setSignal_strength("-85dBm");
    	deviceEventInfo.setBattery_level(new BigDecimal(92.50));
    	deviceEventInfo.setRemark("api submit");
    	
    	Result result = servicesAPI.submitDeviceEventInfo(deviceEventInfo);
    	
    	System.out.println(JSON.toJSONString(result, SerializerFeature.PrettyFormat, SerializerFeature.WriteMapNullValue));
    }
    

    @Test
    public void accessRateLimiterTest() {
        Runnable r = new Runnable() {

            @Override
            public void run() {
                for (int i = 0; i < 1; i++) {
                	getDeviceInfo();
                }

            }
        };
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(r);
            t.start();
        }
        // 阻塞启动服务主线程
        synchronized (APIsTest.class) {
            try {
                APIsTest.class.wait();
            } catch (InterruptedException e) {
            }
        }
    }

   
}

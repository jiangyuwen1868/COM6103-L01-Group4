package com.jyw.csp.api.inf;

import com.jyw.csp.api.vo.DevcieEventInfo;
import com.jyw.csp.api.vo.DeviceInfo;
import com.jyw.csp.api.vo.DeviceInfoResult;
import com.jyw.csp.api.vo.Result;

public interface CspServicesInf {

    public DeviceInfoResult getDeviceInfo(String deviceId);
    public Result deviceRegistration(DeviceInfo deviceInfo);
    public Result submitDeviceEventInfo(DevcieEventInfo deviceEventInfo);

}

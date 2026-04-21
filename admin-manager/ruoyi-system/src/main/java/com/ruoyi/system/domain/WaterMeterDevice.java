package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.desensitize.DesensitizationTypeEnum;
import com.ruoyi.common.desensitize.annotation.Desensitization;

/**
 * 智能水设备信息对象 water_meter_device
 * 
 * @author ruoyi
 * @date 2026-03-17
 */
public class WaterMeterDevice extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 设备序列号（水表唯一标识，如出厂编号） */
    @Excel(name = "设备序列号", readConverterExp = "水=表唯一标识，如出厂编号")
    private String deviceSn;

    /** 水表型号（如NB-IoT-100、LoRa-200） */
    @Excel(name = "水表型号", readConverterExp = "如=NB-IoT-100、LoRa-200")
    private String deviceModel;

    /** 水表类型 1-冷水表 2-热水表 3-远传水表 4-智能预付费水表 */
    @Excel(name = "水表类型 1-冷水表 2-热水表 3-远传水表 4-智能预付费水表")
    private Long deviceType;

    /** 生产厂家 */
    @Excel(name = "生产厂家")
    private String manufacturer;

    /** 生产日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "生产日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date productionDate;

    /** 安装地址（省/市/区/街道/门牌号） */
    @Excel(name = "安装地址", readConverterExp = "省=/市/区/街道/门牌号")
    private String installAddress;

    /** 用户姓名（表主） */
    @Desensitization(type = DesensitizationTypeEnum.CHINESE_NAME)
    @Excel(name = "用户姓名", readConverterExp = "表=主")
    private String userName;

    /** 用户联系电话 */
    @Desensitization(type = DesensitizationTypeEnum.MOBILE_PHONE)
    @Excel(name = "用户联系电话")
    private String userPhone;

    /** 用户身份证号（可选） */
    @Desensitization(type = DesensitizationTypeEnum.ID_CARD)
    @Excel(name = "用户身份证号", readConverterExp = "可=选")
    private String userIdCard;

    /** 安装日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "安装日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date installDate;

    /** 设备激活时间（首次上线时间） */
    @Excel(name = "设备激活时间", readConverterExp = "首=次上线时间")
    private Date activationDate;

    /** 设备状态 0-未激活 1-正常运行 2-故障 3-欠费停机 4-拆机 5-维护中 */
    @Excel(name = "设备状态 0-未激活 1-正常运行 2-故障 3-欠费停机 4-拆机 5-维护中")
    private Long status;

    /** 最后上线时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "最后上线时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lastOnlineTime;

    /** 累计用水量（立方米） */
    @Excel(name = "累计用水量", readConverterExp = "立=方米")
    private BigDecimal totalWaterUsage;

    /** 剩余预存金额（元，预付费表专用） */
    @Excel(name = "剩余预存金额", readConverterExp = "元=，预付费表专用")
    private BigDecimal remainingAmount;

    /** 低余额预警阈值（元） */
    @Excel(name = "低余额预警阈值", readConverterExp = "元=")
    private BigDecimal warningThreshold;

    /** 设备固件版本 */
    @Excel(name = "设备固件版本")
    private String firmwareVersion;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setDeviceSn(String deviceSn) 
    {
        this.deviceSn = deviceSn;
    }

    public String getDeviceSn() 
    {
        return deviceSn;
    }
    public void setDeviceModel(String deviceModel) 
    {
        this.deviceModel = deviceModel;
    }

    public String getDeviceModel() 
    {
        return deviceModel;
    }
    public void setDeviceType(Long deviceType) 
    {
        this.deviceType = deviceType;
    }

    public Long getDeviceType() 
    {
        return deviceType;
    }
    public void setManufacturer(String manufacturer) 
    {
        this.manufacturer = manufacturer;
    }

    public String getManufacturer() 
    {
        return manufacturer;
    }
    public void setProductionDate(Date productionDate) 
    {
        this.productionDate = productionDate;
    }

    public Date getProductionDate() 
    {
        return productionDate;
    }
    public void setInstallAddress(String installAddress) 
    {
        this.installAddress = installAddress;
    }

    public String getInstallAddress() 
    {
        return installAddress;
    }
    public void setUserName(String userName) 
    {
        this.userName = userName;
    }

    public String getUserName() 
    {
        return userName;
    }
    public void setUserPhone(String userPhone) 
    {
        this.userPhone = userPhone;
    }

    public String getUserPhone() 
    {
        return userPhone;
    }
    public void setUserIdCard(String userIdCard) 
    {
        this.userIdCard = userIdCard;
    }

    public String getUserIdCard() 
    {
        return userIdCard;
    }
    public void setInstallDate(Date installDate) 
    {
        this.installDate = installDate;
    }

    public Date getInstallDate() 
    {
        return installDate;
    }
    public void setActivationDate(Date activationDate) 
    {
        this.activationDate = activationDate;
    }

    public Date getActivationDate() 
    {
        return activationDate;
    }
    public void setStatus(Long status) 
    {
        this.status = status;
    }

    public Long getStatus() 
    {
        return status;
    }
    public void setLastOnlineTime(Date lastOnlineTime) 
    {
        this.lastOnlineTime = lastOnlineTime;
    }

    public Date getLastOnlineTime() 
    {
        return lastOnlineTime;
    }
    public void setTotalWaterUsage(BigDecimal totalWaterUsage) 
    {
        this.totalWaterUsage = totalWaterUsage;
    }

    public BigDecimal getTotalWaterUsage() 
    {
        return totalWaterUsage;
    }
    public void setRemainingAmount(BigDecimal remainingAmount) 
    {
        this.remainingAmount = remainingAmount;
    }

    public BigDecimal getRemainingAmount() 
    {
        return remainingAmount;
    }
    public void setWarningThreshold(BigDecimal warningThreshold) 
    {
        this.warningThreshold = warningThreshold;
    }

    public BigDecimal getWarningThreshold() 
    {
        return warningThreshold;
    }
    public void setFirmwareVersion(String firmwareVersion) 
    {
        this.firmwareVersion = firmwareVersion;
    }

    public String getFirmwareVersion() 
    {
        return firmwareVersion;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceSn", getDeviceSn())
            .append("deviceModel", getDeviceModel())
            .append("deviceType", getDeviceType())
            .append("manufacturer", getManufacturer())
            .append("productionDate", getProductionDate())
            .append("installAddress", getInstallAddress())
            .append("userName", getUserName())
            .append("userPhone", getUserPhone())
            .append("userIdCard", getUserIdCard())
            .append("installDate", getInstallDate())
            .append("activationDate", getActivationDate())
            .append("status", getStatus())
            .append("lastOnlineTime", getLastOnlineTime())
            .append("totalWaterUsage", getTotalWaterUsage())
            .append("remainingAmount", getRemainingAmount())
            .append("warningThreshold", getWarningThreshold())
            .append("firmwareVersion", getFirmwareVersion())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}

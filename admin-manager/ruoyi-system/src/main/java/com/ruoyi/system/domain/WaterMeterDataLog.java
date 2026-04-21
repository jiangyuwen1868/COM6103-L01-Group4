package com.ruoyi.system.domain;

import java.math.BigDecimal;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 智能水数据流水对象 water_meter_data_log
 * 
 * @author ruoyi
 * @date 2026-03-17
 */
public class WaterMeterDataLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键ID */
    private String id;

    /** 关联设备序列号 */
    @Excel(name = "关联设备序列号")
    private String deviceSn;

    /** 数据类型 1-实时用水量 2-累计用水量 3-设备状态上报 4-余额上报 5-云端指令下发 6-故障报警 7-低电量报警 */
    @Excel(name = "数据类型 1-实时用水量 2-累计用水量 3-设备状态上报 4-余额上报 5-云端指令下发 6-故障报警 7-低电量报警")
    private Long dataType;

    /** 数据值（如用水量：12.5，指令：停机，故障码：E01） */
    @Excel(name = "数据值", readConverterExp = "如=用水量：12.5，指令：停机，故障码：E01")
    private String dataValue;

    /** 数据单位（立方米、元、无、故障码等） */
    @Excel(name = "数据单位", readConverterExp = "立=方米、元、无、故障码等")
    private String dataUnit;

    /** 数据产生时间（设备端的时间） */
    @Excel(name = "数据产生时间", readConverterExp = "设=备端的时间")
    private Date dataTime;

    /** 数据上传到云端的时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "数据上传到云端的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date uploadTime;

    /** 云端下发的指令内容（仅data_type=5时有值，如：远程开阀、远程关阀、设置预警阈值） */
    @Excel(name = "云端下发的指令内容", readConverterExp = "仅=data_type=5时有值，如：远程开阀、远程关阀、设置预警阈值")
    private String instructionContent;

    /** 指令执行状态（仅data_type=5时有值 0-待执行 1-执行成功 2-执行失败 3-超时） */
    @Excel(name = "指令执行状态", readConverterExp = "仅=data_type=5时有值,0=-待执行,1=-执行成功,2=-执行失败,3=-超时")
    private Long instructionStatus;

    /** 设备上传数据时的信号强度（如NB-IoT信号值） */
    @Excel(name = "设备上传数据时的信号强度", readConverterExp = "如=NB-IoT信号值")
    private String signalStrength;

    /** 设备电池电量（百分比，如85.50） */
    @Excel(name = "设备电池电量", readConverterExp = "百=分比，如85.50")
    private BigDecimal batteryLevel;

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
    public void setDataType(Long dataType) 
    {
        this.dataType = dataType;
    }

    public Long getDataType() 
    {
        return dataType;
    }
    public void setDataValue(String dataValue) 
    {
        this.dataValue = dataValue;
    }

    public String getDataValue() 
    {
        return dataValue;
    }
    public void setDataUnit(String dataUnit) 
    {
        this.dataUnit = dataUnit;
    }

    public String getDataUnit() 
    {
        return dataUnit;
    }
    public void setDataTime(Date dataTime) 
    {
        this.dataTime = dataTime;
    }

    public Date getDataTime() 
    {
        return dataTime;
    }
    public void setUploadTime(Date uploadTime) 
    {
        this.uploadTime = uploadTime;
    }

    public Date getUploadTime() 
    {
        return uploadTime;
    }
    public void setInstructionContent(String instructionContent) 
    {
        this.instructionContent = instructionContent;
    }

    public String getInstructionContent() 
    {
        return instructionContent;
    }
    public void setInstructionStatus(Long instructionStatus) 
    {
        this.instructionStatus = instructionStatus;
    }

    public Long getInstructionStatus() 
    {
        return instructionStatus;
    }
    public void setSignalStrength(String signalStrength) 
    {
        this.signalStrength = signalStrength;
    }

    public String getSignalStrength() 
    {
        return signalStrength;
    }
    public void setBatteryLevel(BigDecimal batteryLevel) 
    {
        this.batteryLevel = batteryLevel;
    }

    public BigDecimal getBatteryLevel() 
    {
        return batteryLevel;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("deviceSn", getDeviceSn())
            .append("dataType", getDataType())
            .append("dataValue", getDataValue())
            .append("dataUnit", getDataUnit())
            .append("dataTime", getDataTime())
            .append("uploadTime", getUploadTime())
            .append("instructionContent", getInstructionContent())
            .append("instructionStatus", getInstructionStatus())
            .append("signalStrength", getSignalStrength())
            .append("batteryLevel", getBatteryLevel())
            .append("remark", getRemark())
            .append("createTime", getCreateTime())
            .toString();
    }
}

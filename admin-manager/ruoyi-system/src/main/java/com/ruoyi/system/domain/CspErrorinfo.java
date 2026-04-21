package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 【请填写功能名称】对象 csp_errorinfo
 * 
 * @author ruoyi
 * @date 2026-03-14
 */
public class CspErrorinfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 错误代码 */
    private String errorcode;

    /** 错误类型 */
    @Excel(name = "错误类型")
    private String errortype;

    /** 错误信息 */
    @Excel(name = "错误信息")
    private String errormsg;

    /** 是否转译错误信息 */
    @Excel(name = "是否转译错误信息")
    private String isconv;

    /** 信息 */
    @Excel(name = "信息")
    private String convertmsg;

    public void setErrorcode(String errorcode) 
    {
        this.errorcode = errorcode;
    }

    public String getErrorcode() 
    {
        return errorcode;
    }
    public void setErrortype(String errortype) 
    {
        this.errortype = errortype;
    }

    public String getErrortype() 
    {
        return errortype;
    }
    public void setErrormsg(String errormsg) 
    {
        this.errormsg = errormsg;
    }

    public String getErrormsg() 
    {
        return errormsg;
    }
    public void setIsconv(String isconv) 
    {
        this.isconv = isconv;
    }

    public String getIsconv() 
    {
        return isconv;
    }
    public void setConvertmsg(String convertmsg) 
    {
        this.convertmsg = convertmsg;
    }

    public String getConvertmsg() 
    {
        return convertmsg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("errorcode", getErrorcode())
            .append("errortype", getErrortype())
            .append("errormsg", getErrormsg())
            .append("isconv", getIsconv())
            .append("remark", getRemark())
            .append("convertmsg", getConvertmsg())
            .toString();
    }
}

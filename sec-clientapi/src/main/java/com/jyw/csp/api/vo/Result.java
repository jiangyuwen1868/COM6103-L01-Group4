package com.jyw.csp.api.vo;

import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StringWriter;

public class Result implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 是否成功：true，成功；false，失败
	 */
	private boolean success = true;
	/**
	 * 返回码
	 */
	private String errcode = "000000000000";
	/**
	 * 返回信息
	 */
	private String errmsg = "success";
	/**
	 * 全局跟踪流水号
	 */
	private String traceId;
	/**
	 * 服务耗时
	 */
	private String svrCostTime;
	/**
	 * Throwable描述信息
	 */
	private String exceptionMessage;
	/**
	 * 异常堆栈信息
	 */
	private String exceptionStackTrace;

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getErrcode() {
		return errcode;
	}

	public void setErrcode(String errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		this.errmsg = errmsg;
	}

	public String getTraceId() {
		return traceId;
	}

	public void setTraceId(String traceId) {
		this.traceId = traceId;
	}

	public String getSvrCostTime() {
		return svrCostTime;
	}

	public void setSvrCostTime(String svrCostTime) {
		this.svrCostTime = svrCostTime;
	}

	public String getExceptionMessage() {
		return exceptionMessage;
	}

	public void setExceptionMessage(String exceptionMessage) {
		this.exceptionMessage = exceptionMessage;
	}

	public String getExceptionStackTrace() {
		return exceptionStackTrace;
	}

	public void setExceptionStackTrace(String exceptionStackTrace) {
		this.exceptionStackTrace = exceptionStackTrace;
	}
	
	public void setExceptionStackTrace(Throwable throwable) {
		StringWriter sw = new StringWriter();
		PrintWriter pw = new PrintWriter(sw);
		try {
			throwable.printStackTrace(pw);
			this.exceptionStackTrace = sw.toString();
			this.exceptionMessage = throwable.getMessage();
			if(this.errmsg==null) {
				this.errmsg = this.exceptionMessage;
			}
		} finally {
			if(pw!=null) {
				pw.close();
			}
			if(sw!=null) {
				try {
					sw.close();
				} catch (Exception e) {
				}
			}
		}
	}
}

package com.jyw.csp.api.vo;

public class DataResult extends Result {
	private static final long serialVersionUID = 1L;

	private int dataLength;
	private String data;

	public int getDataLength() {
		return dataLength;
	}

	public void setDataLength(int dataLength) {
		this.dataLength = dataLength;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}

package com.jyw.csp.util.chiper;

public enum Padding {

	/**
     * 不需要填充
     */
	NOPADDING("NoPadding"),
	
	/**
	 * 80 00补位
	 */
	PADDING("NoPadding"),

    /**
     * 遵循PKCS#7中定义的规范。
     */
	PKCS7PADDING("PKCS5Padding");
	
	private String value;

	private Padding(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}

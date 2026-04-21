package com.ruoyi.stress;

public class TestConfig {
	private String url;
	private int threadCount;
	private int loopCount = -1 ; // 迭代次数（总请求数）-1表示不限制
	private int duration; // 测试持续时间（秒）
	private int rampUpPeriod; // 线程启动间隔（秒）
	private String method = "POST"; // HTTP方法：GET/POST等
	private String contentType = "application/json";
	private String requestBody;
	private long busleep = 10L; // 模拟业务处理时间（毫秒）

	public TestConfig() {
	}

	public TestConfig(String url, int threadCount, int duration, int rampUpPeriod, String method) {
		this.url = url;
		this.threadCount = threadCount;
		this.duration = duration;
		this.rampUpPeriod = rampUpPeriod;
		this.method = method;
	}

	// Getter和Setter方法
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public int getThreadCount() {
		return threadCount;
	}

	public void setThreadCount(int threadCount) {
		this.threadCount = threadCount;
	}

	public int getLoopCount() {
		return loopCount;
	}

	public void setLoopCount(int loopCount) {
		this.loopCount = loopCount;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getRampUpPeriod() {
		return rampUpPeriod;
	}

	public void setRampUpPeriod(int rampUpPeriod) {
		this.rampUpPeriod = rampUpPeriod;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getRequestBody() {
		return requestBody;
	}

	public void setRequestBody(String requestBody) {
		this.requestBody = requestBody;
	}

	public long getBusleep() {
		return busleep;
	}

	public void setBusleep(long busleep) {
		this.busleep = busleep;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
}
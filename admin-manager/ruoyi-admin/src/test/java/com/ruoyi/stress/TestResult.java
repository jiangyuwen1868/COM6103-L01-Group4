package com.ruoyi.stress;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class TestResult {
    private AtomicInteger totalRequests = new AtomicInteger(0);
    private AtomicInteger successRequests = new AtomicInteger(0);
    private AtomicInteger failedRequests = new AtomicInteger(0);
    private AtomicLong totalResponseTime = new AtomicLong(0);
    private long startTime;
    private long endTime;
    private long maxTime;
    private long minTime;
    
    public TestResult() {}
    
    public void recordSuccess(long responseTime) {
        totalRequests.incrementAndGet();
        successRequests.incrementAndGet();
        totalResponseTime.addAndGet(responseTime);
    }
    
    public void recordFailure() {
        totalRequests.incrementAndGet();
        failedRequests.incrementAndGet();
    }
    
    public void startTest() {
        this.startTime = System.currentTimeMillis();
    }
    
    public void endTest() {
        this.endTime = System.currentTimeMillis();
    }
    
    // Getter方法
    public int getTotalRequests() { return totalRequests.get(); }
    public int getSuccessRequests() { return successRequests.get(); }
    public int getFailedRequests() { return failedRequests.get(); }
    public long getTotalResponseTime() { return totalResponseTime.get(); }
    public long getTestDuration() { return endTime - startTime; }
    
    
    
    public long getMaxTime() {
		return maxTime;
	}

	public void setMaxTime(long maxTime) {
		this.maxTime = maxTime;
	}

	public long getMinTime() {
		return minTime;
	}

	public void setMinTime(long minTime) {
		this.minTime = minTime;
	}

	public double getSuccessRate() {
        return totalRequests.get() == 0 ? 0 : (double) successRequests.get() / totalRequests.get() * 100;
    }
    
    public double getAverageResponseTime() {
        return successRequests.get() == 0 ? 0 : (double) totalResponseTime.get() / successRequests.get();
    }
    
    public double getRequestsPerSecond() {
        long duration = getTestDuration() / 1000;
        return duration == 0 ? 0 : (double) totalRequests.get() / duration;
    }
}

package com.ruoyi.stress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StressTestHttpWorker implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(StressTestHttpWorker.class);
    
    private TestConfig config;
    private TestResult result;
    private HttpClient httpClient;
    private volatile boolean running = true;
    
    public StressTestHttpWorker(TestConfig config, TestResult result) {
        this.config = config;
        this.result = result;
        this.httpClient = new HttpClient();
    }
    
    @Override
    public void run() {
        long testEndTime = System.currentTimeMillis() + (config.getDuration() * 1000L);
        
        while (running && System.currentTimeMillis() < testEndTime) {
            try {
            	long startTime = System.currentTimeMillis();
                long responseTime = httpClient.executeRequest(config);
                // 避免过度占用CPU、及模拟业务系统处理业务时间
                Thread.sleep(config.getBusleep());
                
                long endTime = System.currentTimeMillis() - startTime;
                if (responseTime > 0) {
                    result.recordSuccess(endTime);
                    logger.debug("Request successful, response time: {}ms", endTime);
                    
                    if(result.getMaxTime() < endTime) {
                    	result.setMaxTime(endTime);
                    }
                    if(result.getMinTime()==0 || result.getMinTime() > endTime) {
                    	result.setMinTime(endTime);
                    }
                } else {
                    result.recordFailure();
                    logger.warn("Request failed");
                }
            } catch (Exception e) {
                result.recordFailure();
                logger.error("Request execution error", e);
            }
        }
        
        try {
            httpClient.close();
        } catch (Exception e) {
            logger.error("Error closing HTTP client", e);
        }
    }
    
    public void stop() {
        this.running = false;
    }
}

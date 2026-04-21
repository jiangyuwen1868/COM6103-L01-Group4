package com.ruoyi.stress;

import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

public class StressTestEDBSdkWorker implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(StressTestEDBSdkWorker.class);
    
    private TestConfig config;
    private TestResult result;
    private JdbcTemplate jdbcTemplate;
    private volatile boolean running = true;
    
    public StressTestEDBSdkWorker(TestConfig config, TestResult result, JdbcTemplate jdbcTemplate) {
        this.config = config;
        this.result = result;
        this.jdbcTemplate = jdbcTemplate;
    }
    
    @Override
    public void run() {
        long testEndTime = System.currentTimeMillis() + (config.getDuration() * 1000L);
        
        while (running && System.currentTimeMillis() < testEndTime) {
            try {
                long startTime = System.currentTimeMillis();
                String userName = "pengdy";
				String sql = "select user_name,login_name,phonenumber,email,age from sys_user WHERE login_name = ?";
//                String sql = "select user_name,age from sys_user WHERE phone = '15666666666'";
//				String sql = "select user_name,login_name,dept_name,ipaddr,login_location,browser,os,status,start_timestamp,last_access_time from sys_user_online where login_name='"+userName+"'";
                List<Map<String, Object>> results = jdbcTemplate.queryForList(sql, userName);
				if(results==null || results.isEmpty()) {
					result.recordFailure();
					continue;
				}
//				String username = (String) results.get(0).get("user_name");
//				logger.info("username:" + username);
//				if(!userName.equals(username)) {
//					result.recordFailure();
//					System.err.println("---------------------" + username);
//					continue;
//				}
				
				// 避免过度占用CPU、及模拟业务系统处理业务时间
                Thread.sleep(config.getBusleep());
				
				long responseTime = System.currentTimeMillis() - startTime;
//				logger.info("Query successful, response time: {}ms", responseTime);
				result.recordSuccess(responseTime);
//                if (responseTime > 0) {
//                    result.recordSuccess(responseTime);
//                    logger.debug("Query successful, response time: {}ms", responseTime);
//                } else {
//                    result.recordFailure();
//                    logger.warn("Query failed");
//                }
				if(result.getMaxTime() < responseTime) {
                	result.setMaxTime(responseTime);
                }
                if(result.getMinTime() > responseTime) {
                	result.setMinTime(responseTime);
                }
            } catch (Exception e) {
                result.recordFailure();
                logger.error("Query execution error", e);
            }
        }
    }
    
    public void stop() {
        this.running = false;
    }
}

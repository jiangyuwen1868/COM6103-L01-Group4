package com.ruoyi.stress;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.entity.SysUser;

@SpringBootTest
public class EDB_SDK_StressTester {
	
	private static final Logger logger = LoggerFactory.getLogger(EDB_SDK_StressTester.class);

	@Autowired
    public JdbcTemplate jdbcTemplate;
	
	@Test
	public void testMain() {
		try {
            // 创建测试配置
            TestConfig config = new TestConfig();
            config.setThreadCount(10);
            config.setDuration(60);
            config.setRampUpPeriod(0);
            config.setBusleep(10L);
//            config.setUrl("http://localhost:8080/edb/getUsers");
//            config.setUrl("http://localhost:8080/edb/addUser");
//            config.setUrl("http://localhost:8080/edb/updateUser");
//            SysUser user = new SysUser();
            
//            user.setUserName("pengdy"); // 等值查询
//            user.setLoginName("pengdy"); // 模糊查询 %子串%
//            user.setPhonenumber("13539876078"); // 模糊查询 %子串%
            
//            config.setRequestBody(JSONObject.toJSONString(user));
            
            System.out.println("=== Java压力测试工具启动 ===");
            System.out.printf("目标URL: %s%n", config.getUrl());
            System.out.printf("并发线程: %d%n", config.getThreadCount());
            System.out.printf("测试时长: %d秒%n", config.getDuration());
            System.out.printf("Ramp-Up时间: %d秒%n", config.getRampUpPeriod());
            System.out.printf("模拟业务时间: %d毫秒%n", config.getBusleep());
            
            // 执行压力测试（SQL）
            TestResult result = executeTest(config);
            // 执行压力测试（API）
//            TestResult result = executeTest2(config);
            
            // 打印结果
            ResultPrinter.printResults(result);
            
        } catch (Exception e) {
            System.err.println("压力测试执行失败: " + e.getMessage());
            e.printStackTrace();
        }
	}
	
	
	public TestResult executeTest(TestConfig config) throws InterruptedException {
        TestResult result = new TestResult();
        result.startTest();
        
        ExecutorService executor = Executors.newFixedThreadPool(config.getThreadCount());
        List<StressTestEDBSdkWorker> workers = new ArrayList<>();
        
        logger.info("Starting stress test with {} threads for {} seconds", 
            config.getThreadCount(), config.getDuration());
        
        // 创建并提交工作线程
        for (int i = 0; i < config.getThreadCount(); i++) {
        	StressTestEDBSdkWorker worker = new StressTestEDBSdkWorker(config, result, jdbcTemplate);
        	//StressTestHttpWorker worker = new StressTestHttpWorker(config, result);
        	workers.add(worker);
            executor.submit(worker);
            
            // 模拟用户逐渐增加（Ramp-Up）
            if (config.getRampUpPeriod() > 0 && i < config.getThreadCount() - 1) {
                Thread.sleep((long) config.getRampUpPeriod() * 1000 / config.getThreadCount());
            }
        }
        
        // 等待测试完成
        Thread.sleep(config.getDuration() * 1000L);
        
        // 停止所有工作线程
        for (StressTestEDBSdkWorker worker : workers) {
            worker.stop();
        }
        
        executor.shutdown();
        if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
        
        result.endTest();
        return result;
    }
	
	
	public TestResult executeTest2(TestConfig config) throws InterruptedException {
        TestResult result = new TestResult();
        result.startTest();
        
        ExecutorService executor = Executors.newFixedThreadPool(config.getThreadCount());
        List<StressTestHttpWorker> workers = new ArrayList<>();
        
        logger.info("Starting stress test with {} threads for {} seconds", 
            config.getThreadCount(), config.getDuration());
        
        // 创建并提交工作线程
        for (int i = 0; i < config.getThreadCount(); i++) {
        	//StressTestEDBSdkWorker worker = new StressTestEDBSdkWorker(config, result, jdbcTemplate);
        	StressTestHttpWorker worker = new StressTestHttpWorker(config, result);
        	workers.add(worker);
            executor.submit(worker);
            
            // 模拟用户逐渐增加（Ramp-Up）
            if (config.getRampUpPeriod() > 0 && i < config.getThreadCount() - 1) {
                Thread.sleep((long) config.getRampUpPeriod() * 1000 / config.getThreadCount());
            }
        }
        
        // 等待测试完成
        Thread.sleep(config.getDuration() * 1000L);
        
        // 停止所有工作线程
        for (StressTestHttpWorker worker : workers) {
            worker.stop();
        }
        
        executor.shutdown();
        if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
        
        result.endTest();
        return result;
    }
}

package com.ruoyi.stress;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StressTestHttpEngine {
    private static final Logger logger = LoggerFactory.getLogger(StressTestHttpEngine.class);
    
    public TestResult executeTest(TestConfig config) throws InterruptedException {
        TestResult result = new TestResult();
        result.startTest();
        
        ExecutorService executor = Executors.newFixedThreadPool(config.getThreadCount());
        List<StressTestHttpWorker> workers = new ArrayList<>();
        
        logger.info("Starting stress test with {} threads for {} seconds", 
            config.getThreadCount(), config.getDuration());
        
        // 创建并提交工作线程
        for (int i = 0; i < config.getThreadCount(); i++) {
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

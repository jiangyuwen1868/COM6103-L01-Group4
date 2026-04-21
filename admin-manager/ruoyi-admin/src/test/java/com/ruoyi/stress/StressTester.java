package com.ruoyi.stress;

public class StressTester {
    
    public static void main(String[] args) {
        try {
            // 创建测试配置
            TestConfig config = new TestConfig();
            config.setUrl("http://www.baidu.com");
            config.setThreadCount(10);
            config.setDuration(30);
            config.setRampUpPeriod(5);
            config.setMethod("GET");
            
            System.out.println("=== Java压力测试工具启动 ===");
            System.out.printf("目标URL: %s%n", config.getUrl());
            System.out.printf("并发线程: %d%n", config.getThreadCount());
            System.out.printf("测试时长: %d秒%n", config.getDuration());
            System.out.printf("Ramp-Up时间: %d秒%n", config.getRampUpPeriod());
            
            // 执行压力测试
            StressTestHttpEngine engine = new StressTestHttpEngine();
            TestResult result = engine.executeTest(config);
            
            // 打印结果
            ResultPrinter.printResults(result);
            
        } catch (Exception e) {
            System.err.println("压力测试执行失败: " + e.getMessage());
            e.printStackTrace();
        }
    }
}

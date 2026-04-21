package com.ruoyi.stress;

public class ResultPrinter {
    
    public static void printResults(TestResult result) {
        System.out.println("\n=== 压力测试结果 ===");
        System.out.printf("总请求数: %d%n", result.getTotalRequests());
        System.out.printf("成功请求: %d%n", result.getSuccessRequests());
        System.out.printf("失败请求: %d%n", result.getFailedRequests());
        System.out.printf("成功率: %.2f%%%n", result.getSuccessRate());
        System.out.printf("最大响应时间: %d ms%n", result.getMaxTime());
        System.out.printf("最小响应时间: %d ms%n", result.getMinTime());
        System.out.printf("平均响应时间: %.2f ms%n", result.getAverageResponseTime());
        System.out.printf("吞吐量: %.2f 请求/秒%n", result.getRequestsPerSecond());
        System.out.printf("测试持续时间: %d ms%n", result.getTestDuration());
        
        // 性能评级
        double avgResponseTime = result.getAverageResponseTime();
        String performanceGrade;
        if (avgResponseTime < 100) {
            performanceGrade = "优秀";
        } else if (avgResponseTime < 500) {
            performanceGrade = "良好";
        } else if (avgResponseTime < 1000) {
            performanceGrade = "一般";
        } else {
            performanceGrade = "较差";
        }
        System.out.printf("性能评级: %s%n", performanceGrade);
    }
}

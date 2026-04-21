package com.ruoyi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogTest {
    private static final Logger ydLogger = LoggerFactory.getLogger("com.ydxsafe.Test");
    private static final Logger otherLogger = LoggerFactory.getLogger("com.other.Test");

    public static void main(String[] args) {
        ydLogger.info("【INFO】com.ydxsafe 测试信息");
        ydLogger.warn("【WARN】com.ydxsafe 测试警告");
        ydLogger.error("【ERROR】com.ydxsafe 测试错误");

        otherLogger.info("【INFO】其他包测试信息");
        otherLogger.warn("【WARN】其他包测试警告");
        otherLogger.error("【ERROR】其他包测试错误");
    }
}


package com.ruoyi.stress;

import java.security.SecureRandom;

public class GenPhoneUtil {

	// 常见的手机号段（前 3 位）
    private static final String[] PREFIXES = {
            "130", "131", "132", "133", "134", "135", "136", "137", "138", "139",
            "145", "147",
            "150", "151", "152", "153", "155", "156", "157", "158", "159",
            "166",
            "170", "171", "172", "173", "174", "175", "176", "177", "178", "179",
            "180", "181", "182", "183", "184", "185", "186", "187", "188", "189",
            "198", "199"
    };

    private static final SecureRandom RANDOM = new SecureRandom();
	/**
     * 生成一个随机手机号
     *
     * @return 11 位手机号字符串
     */
    public static String randomPhoneNumber() {
        // 1️⃣ 随机选取一个号段
        String prefix = PREFIXES[RANDOM.nextInt(PREFIXES.length)];

        // 2️⃣ 生成后 8 位数字（0\~9）
        StringBuilder sb = new StringBuilder(prefix);
        for (int i = 0; i < 8; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        return sb.toString();
    }
}

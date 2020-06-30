package com.cloud.jarbase.util;

import org.apache.commons.text.RandomStringGenerator;

import java.util.Random;

public class RandomUtils {
    private static final RandomStringGenerator generator = new RandomStringGenerator.Builder()
            .withinRange('0', '}').build();

    private static final RandomStringGenerator generator2 = new RandomStringGenerator.Builder()
            .withinRange('0', '9').build();

    public static RandomStringGenerator getGenerator() {
        return generator;
    }

    public static RandomStringGenerator getGeneratorNumber() {
        return generator2;
    }

    /**
     * 获取一定长度的随机字符串(字母+数字)
     * @param length 指定字符串长度
     * @return 一定长度的字符串
     */
    public static String getRandomStringByLength(int length) {
        String base = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }

}

package com.example.multiDataSource.pojo.dto;

import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.Map;

public class ThreadLocalHolder {

    public final static String WHODB = "whodb";

    private static ThreadLocal<Map<String, String>> mapThreadLocal = new ThreadLocal<Map<String, String>>();

    public static String get(String key) {
        Map<String, String> map = mapThreadLocal.get();
        return MapUtils.getString(map, key);
    }

    public static void set(String key, String value) {
        Map<String, String> map = mapThreadLocal.get();
        if (map == null) {
            map = new HashMap<String, String>();
        }
        map.put(key, value);
        mapThreadLocal.set(map);
    }

    public static String getWhodb() {
        return get(WHODB);
    }
}

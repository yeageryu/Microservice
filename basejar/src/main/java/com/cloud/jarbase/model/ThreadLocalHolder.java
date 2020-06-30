package com.cloud.jarbase.model;

import org.apache.commons.collections4.MapUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @description
 * @create 2019/3/19
 */
public class ThreadLocalHolder {

    public final static String TENANT_FIELD = "tenant";
    public final static String IMEI_FIELD = "imei";
    public final static String SESSION_FIELD = "sessionId";
    public final static String LAN_FIELD = "lan";
    public final static String IP_FIELD = "clientIp";
    public final static String VER_FIELD = "version";
    public final static String DEVICE_FIELD = "device";  //用来判断手机种类
    public final static String TIME_FIELD = "timestamp";
    public final static String SINGLE_FIELD = "idenpotent"; //幂等用

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

    public static String getTenant() {
        return get(TENANT_FIELD);
    }

    public static String getImel() {
        return get(IMEI_FIELD);
    }

    public static String getSession() {
        return get(SESSION_FIELD);
    }

    public static String getLan() {
        return get(LAN_FIELD);
    }

    public static String getClientIp() {
        return get(IP_FIELD);
    }

    public static String getVersion() {
        return get(VER_FIELD);
    }

    public static String getDevice() {
        return get(DEVICE_FIELD);
    }

    public static String getTime() {
        return get(TIME_FIELD);
    }

    public static String getSingle() {
        return get(SINGLE_FIELD);
    }
}


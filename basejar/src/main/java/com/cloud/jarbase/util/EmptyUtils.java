package com.cloud.jarbase.util;

import java.util.Collection;
import java.util.Map;

public class EmptyUtils {
    public static boolean isEmpty(Object obj) {
        boolean result = true;
        if (obj == null) {
            return true;
        }
        Class type = obj.getClass();
        if (obj instanceof String) {
            result = (obj.toString().trim().length() == 0) || obj.toString().trim().equals("null");
        } else if (obj instanceof Collection) {
            result = ((Collection) obj).size() == 0;
        } else if (type.isArray()) {
            Object[] os = (Object[]) obj;
            if(os.length > 0){
                result = false;
            }
        } else if (obj instanceof Map) {
            result = ((Map) obj).isEmpty();
        } else {
            result = (obj.toString().trim().length() < 1);
        }
        return result;
    }
    public static boolean isNotEmpty(Object obj){
        return !isEmpty(obj);
    }
}
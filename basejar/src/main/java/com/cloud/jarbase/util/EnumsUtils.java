package com.cloud.jarbase.util;

import lombok.Data;

import java.lang.reflect.Method;

@Data
public class EnumsUtils {

    public static <T extends Enum<T>> T getByEnum(Class<T> clz,Object value){
        if (value == null) {
            return null;
        }
        T[] enumList = clz.getEnumConstants();
        Method method = null;
        try {
            try {
                method = clz.getDeclaredMethod("getValue");
            }catch (Exception e){
                method = clz.getDeclaredMethod("getCode");
            }

            for (T t : enumList) {
                Object v = method.invoke(t);
                if(value.equals(v)){
                    return t;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

}

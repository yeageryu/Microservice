package com.cloud.jarbase.util;

import com.cloud.jarbase.util.http.NetworkUtil;
import com.cloud.jarbase.util.security.MD5;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.ClientProtocolException;

import java.io.IOException;
import java.math.BigDecimal;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ToolUtils {

    /**
     * 删除所有值为空的属性
     *
     * @param result
     * @return
     */
    public static JSONObject trimObject(Object result) {
        List<String> keyLst = new ArrayList<String>();
        JSONObject json = JSONObject.fromObject(result);
        Iterator iterator = json.keys();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = json.getString(key); //不能用string接收，因为可能是对象

            if ("null".equals(value) || StringUtils.isEmpty(json.getString(key))) {
                keyLst.add(key);
            }
        }

        for (int i = 0; i < keyLst.size(); i++) {
            json.remove(keyLst.get(i));
        }

        return json;
    }


    /**
     * 将金额加密
     * @param amount
     * @return
     */
    public static String getAmtPwd(BigDecimal amount,int custId,String salt){
        try {
            amount = amount.setScale(2,BigDecimal.ROUND_HALF_DOWN);
            return  MD5.md5(amount+"", "_" + salt + "_" + custId);
        } catch (NoSuchAlgorithmException e) {
            return "";
        }
    }

    public static  boolean sendSms(String url, String countryCode,String mobile, String content) {
        if (countryCode.equals("1")) {
            JSONObject json = new JSONObject();
            json.put("toPhone", mobile);
            json.put("content", content);

            try {
                String result = NetworkUtil.sendPostDataByJson(url, json.toString(), "UTF-8");
                json = JSONObject.fromObject(result);

                if ("00".equals(json.get("rspCode"))) {
                    return true;
                }
            } catch (ClientProtocolException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }else {
            return true;  //临时代码，实际接实际接口
        }

        return false;
    }
}

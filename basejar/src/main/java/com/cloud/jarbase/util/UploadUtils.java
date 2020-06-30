package com.cloud.jarbase.util;

import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Base64;
import java.util.UUID;
import org.apache.commons.io.FileUtils;
import sun.misc.BASE64Decoder;

public class UploadUtils {

    public static String saveBase64(String base64Str, String savePath) {
        base64Str = base64Str.replace(" ","+");

        StringBuffer fileName = new StringBuffer();
        fileName.append(UUID.randomUUID().toString().replaceAll("-", ""));
        if (StringUtils.isBlank(base64Str)) {
            return "";
        } else if (base64Str.indexOf("data:image/png;") != -1) {
            base64Str = base64Str.replace("data:image/png;base64,", "");
            fileName.append(".png");
        } else if (base64Str.indexOf("data:image/jpeg;") != -1) {
            base64Str = base64Str.replace("data:image/jpeg;base64,", "");
            fileName.append(".jpeg");
        } else if (base64Str.indexOf("data:image/jpg;") != -1) {
            base64Str = base64Str.replace("data:image/jpg;base64,", "");
            fileName.append(".jpg");
        }else {
            return "";
        }

        File dir = new File(savePath);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        try {
            // Base64解码
            BASE64Decoder decoder = new BASE64Decoder();
            byte[] b = decoder.decodeBuffer(base64Str);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成文件
            OutputStream out = new FileOutputStream(savePath + fileName.toString());
            out.write(b);
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
           return "";
        }
        return  fileName.toString();
    }
}

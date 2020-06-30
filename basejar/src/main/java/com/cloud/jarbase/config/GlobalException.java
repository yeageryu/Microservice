package com.cloud.jarbase.config;

import com.alibaba.fastjson.JSONObject;
import com.cloud.jarbase.enums.Language;
import com.cloud.jarbase.exception.CustomerException;
import com.cloud.jarbase.model.Multilingual;
import com.cloud.jarbase.model.ThreadLocalHolder;
import com.cloud.jarbase.service.MultilingualService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice
@RequiredArgsConstructor
@Slf4j
public class GlobalException {

    @Value("${customer.default_language}")
    private String default_language;

    private final HttpServletRequest request;

    private final MultilingualService multLanService;

    @ExceptionHandler(CustomerException.class)
    public JSONObject globalException(CustomerException e) {
        String code = e.getCode();
        String message = getMessage(code);

        if (message == null && e.getArgs().length>0){  //说明是上一个服务出的问题，本服务的数据库里没有这个errcode，直接返回就好
            Object[] args = e.getArgs();
            message = args[0]+"";
        }else {
            if (!StringUtils.isEmpty(message)) {  //比如想返回e.getMessage()，则在表里保存的errCode对应的message为null
                Object[] args = e.getArgs();
                if (args.length > 0) {
                    if (message.indexOf("%") > 0) {
                        if (args != null && args.length > 0) {
                            message = String.format(message, args);
                        }
                    }
                }
            } else {
                Object[] args = e.getArgs();
                if (args.length > 0) {
                    message = args[0] + "";
                }
            }
        }
        log.error("自定义异常:{} {}", message, code);

        JSONObject result = new JSONObject();

        result.put("msg", message);
        result.put("code", code);
        return result;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public JSONObject paramException(MethodArgumentNotValidException e) {
        log.error("请求参数异常 {}:", e);

        JSONObject result = new JSONObject();
        result.put("msg",e.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        result.put("code", "99999");

        return result;
    }

    @ExceptionHandler(Exception.class)
    public JSONObject systemException(Exception e) {
        log.error("系统异常 {}:", e);

        JSONObject result = new JSONObject();
        result.put("msg",e.getMessage());
        result.put("code", "99999");

        return result;
    }


    private String getMessage(String errorCode) {
        try {
            Multilingual msgObj = multLanService.getByMsgCode(ThreadLocalHolder.getTenant(), errorCode);

            if (msgObj == null) return null;

            String lan = ObjectUtils.defaultIfNull(request.getHeader(ThreadLocalHolder.LAN_FIELD), default_language);
            if (lan.equals(Language.CHINESE.getValue())){
                return msgObj.getMsgCn();
            }else{
                return msgObj.getMsgEn();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "";
    }
}

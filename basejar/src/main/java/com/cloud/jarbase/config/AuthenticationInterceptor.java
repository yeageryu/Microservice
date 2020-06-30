package com.cloud.jarbase.config;

import com.cloud.jarbase.exception.CustomerException;
import com.cloud.jarbase.model.ThreadLocalHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 从 http 请求头中取出 token
        String token = httpServletRequest.getHeader(ThreadLocalHolder.SESSION_FIELD);
        String lan = httpServletRequest.getHeader(ThreadLocalHolder.LAN_FIELD);
        String ver = httpServletRequest.getHeader(ThreadLocalHolder.VER_FIELD);
        String curTime = httpServletRequest.getHeader(ThreadLocalHolder.TIME_FIELD);
        String idenpotent = httpServletRequest.getHeader(ThreadLocalHolder.SINGLE_FIELD);

        ThreadLocalHolder.set(ThreadLocalHolder.SESSION_FIELD, token);
        ThreadLocalHolder.set(ThreadLocalHolder.LAN_FIELD, lan);
        ThreadLocalHolder.set(ThreadLocalHolder.VER_FIELD, ver);
        ThreadLocalHolder.set(ThreadLocalHolder.TIME_FIELD, curTime);
        ThreadLocalHolder.set(ThreadLocalHolder.SINGLE_FIELD, idenpotent);

        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有LoginToken注释，有则跳过认证
        if (method.isAnnotationPresent(LoginToken.class)) {
            LoginToken loginToken = method.getAnnotation(LoginToken.class);
            if (loginToken.required()) {
                if (token == null) {
                    throw new CustomerException("10001");
                }
            }
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }


}

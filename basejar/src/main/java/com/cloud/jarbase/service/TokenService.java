package com.cloud.jarbase.service;

import com.cloud.jarbase.exception.CustomerException;
import com.cloud.jarbase.model.ThreadLocalHolder;
import lombok.AllArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.text.StrBuilder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TokenService {

    private RedisBaseService redisBaseService;

    /**
     * 创建幂等token
     *
     * @return
     */
    public String createToken(){
        String uuid = String.valueOf(UUID.randomUUID());

        StrBuilder token = new StrBuilder();

        try
        {
            token.append(ThreadLocalHolder.getTenant()).append("_token_").append(uuid);
            redisBaseService.setEx(token.toString(),token.toString(),10000L);

            boolean notEmpty = StringUtils.isNotEmpty(token.toString());

            if(notEmpty){
                return token.toString();
            }
        }catch(Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    /**
     * 检验token
     *
     * @param request
     * @return
     */
    public boolean checkToken(HttpServletRequest request) throws Exception {
        String token = ThreadLocalHolder.getSingle();

        if(StringUtils.isBlank(token)){
            throw new CustomerException("幂等token没有提交");
        }

        if(!redisBaseService.exists(token)) {
            throw new CustomerException("17002");
        }

        redisBaseService.remove(token);

        return true;
    }
}

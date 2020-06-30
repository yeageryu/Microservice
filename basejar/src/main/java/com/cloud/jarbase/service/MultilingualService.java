package com.cloud.jarbase.service;

import com.cloud.jarbase.config.RedisCacheConfig;
import com.cloud.jarbase.enums.Language;
import com.cloud.jarbase.mapper.IMultilingualMapper;
import com.cloud.jarbase.model.Multilingual;
import com.cloud.jarbase.model.ThreadLocalHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(rollbackFor = Exception.class)
public class MultilingualService  {

   @Autowired
   private IMultilingualMapper mapper;

    @Cacheable(value = RedisCacheConfig.CACHE_1YEAR, key = "'msgLan.'+#tenant+'.'+#msgCode", unless = "#result == null")
    public Multilingual getByMsgCode(String tenant, String msgCode) {
        return mapper.getByMsgCode(msgCode);
    }

    public String getLanByCode(String msgCode, String lan, Object[] args){
        Multilingual language = getByMsgCode(ThreadLocalHolder.getTenant(), msgCode);
        String message="";
        if (Language.CHINESE.getValue().equals(lan)) {
            message = language.getMsgCn();
        } else {
            message =language.getMsgEn();
        }

        if (args!=null && args.length>0) {
            message = String.format(message, args);
        }
        return message;
    }
}

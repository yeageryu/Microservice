package com.cloud.jarbase.mapper;

import com.cloud.jarbase.model.Multilingual;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface IMultilingualMapper extends Mapper<Multilingual> {
    @Select("select * from multilingual where msg_code = #{msgCode}")
    Multilingual getByMsgCode(String msgCode);
}


package com.example.multiDataSource.controller;

import com.example.multiDataSource.pojo.db.SchoolInfo;
import com.example.multiDataSource.pojo.dto.ThreadLocalHolder;
import com.example.multiDataSource.service.SchoolService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/school")
@AllArgsConstructor
public class SchoolApiController {

    private SchoolService service;

    protected HttpServletRequest request;

    @RequestMapping(value = "/insert")
    public void insert(@RequestBody  SchoolInfo obj){

        //根据header里的lan来决定是哪种语言
        String lan = (String) request.getHeader("language");
        if (StringUtils.isNotEmpty(lan) && lan.equals("zh")){
            ThreadLocalHolder.set(ThreadLocalHolder.WHODB,"cnDb");
        }else {
            ThreadLocalHolder.set(ThreadLocalHolder.WHODB,"enDb");
        }

        service.insert(obj);
    }

}

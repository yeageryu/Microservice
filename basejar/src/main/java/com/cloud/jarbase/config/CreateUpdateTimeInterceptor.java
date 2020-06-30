package com.cloud.jarbase.config;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.*;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.Properties;

/**
 * 时间拦截器.
 */
@EqualsAndHashCode
@Data
@Accessors(chain = true)
@Intercepts({@Signature(
        type = org.apache.ibatis.executor.Executor.class,
        method = "update",
        args = {MappedStatement.class, Object.class})})
public class CreateUpdateTimeInterceptor implements Interceptor {

    private Properties properties;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        MappedStatement mappedStatement = (MappedStatement) invocation.getArgs()[0];

        // 获取 SQL 命令
        SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();

        // 获取参数
        Object parameter = invocation.getArgs()[1];

        // 获取私有成员变量
        Field[] declaredFields = parameter.getClass().getDeclaredFields();
        if (parameter.getClass().getSuperclass() != null) {
            Field[] superField = parameter.getClass().getSuperclass().getDeclaredFields();
            declaredFields = ArrayUtils.addAll(declaredFields, superField);
        }
        // 是否为mybatis plug
        boolean isPlugUpdate = parameter.getClass().getDeclaredFields().length == 1
                && parameter.getClass().getDeclaredFields()[0].getName().equals("serialVersionUID");

        //兼容mybatis plus的update
        if (isPlugUpdate) {
            Map<String, Object> updateParam = (Map<String, Object>) parameter;
            Class<?> updateParamType = updateParam.get("param1").getClass();
            declaredFields = updateParamType.getDeclaredFields();
            if (updateParamType.getSuperclass() != null) {
                Field[] superField = updateParamType.getSuperclass().getDeclaredFields();
                declaredFields = ArrayUtils.addAll(declaredFields, superField);
            }
        }
        for (Field field : declaredFields) {

            // insert
            if (field.getAnnotation(CreatedTimeFuncation.class) != null) {
                if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                    field.setAccessible(true);
                    field.set(parameter, System.currentTimeMillis());
                }
            }

            // update
            if (field.getAnnotation(UpdatedTimeFuncation.class) != null) {
                if (SqlCommandType.INSERT.equals(sqlCommandType)
                        || SqlCommandType.UPDATE.equals(sqlCommandType)) {
                    field.setAccessible(true);

                    //兼容mybatis plus的update
                    if (isPlugUpdate) {
                        Map<String, Object> updateParam = (Map<String, Object>) parameter;
                        field.set(updateParam.get("param1"),System.currentTimeMillis());
                    } else {
                        field.set(parameter, System.currentTimeMillis());
                    }
                }
            }
        }

        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof org.apache.ibatis.executor.Executor) {
            return Plugin.wrap(target, this);
        }
        return target;
    }

    @Override
    public void setProperties(Properties prop) {
        this.properties = prop;
    }
}
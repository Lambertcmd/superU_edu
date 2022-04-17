package com.geek.servicebase.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName MyMetaObjectHandler
 * @Description TODO
 * @Author Lambert
 * @Date 2021/12/28 19:19
 * @Version 1.0
 **/
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 自动填充
     * 使用mp实现插入时这个方法会执行
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        //default MetaObjectHandler setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject)
        log.info("start insert fill....");
        this.setFieldValByName("gmtCreate",new Date(),metaObject);
        this.setFieldValByName("gmtModified",new Date(),metaObject);
        this.setFieldValByName("isDisabled",false,metaObject);//账户是否禁用
        this.setFieldValByName("isDeleted", false,metaObject);//逻辑删除

    }
    /**
     * 自动填充
     * 使用mp实现修改时这个方法会执行
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update  fill....");
        this.setFieldValByName("gmtModified",new Date(),metaObject);
    }
}

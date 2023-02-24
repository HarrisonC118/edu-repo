package com.hatcher.user.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * @author hatcher
 * @date 2023/1/19 13:46
 * @description
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
        this.strictInsertFill(metaObject, "gmtCreate", () -> LocalDateTime.now(), LocalDateTime.class);
        this.strictInsertFill(metaObject, "gmtModified", () -> LocalDateTime.now(), LocalDateTime.class);
        this.strictInsertFill(metaObject, "isDeleted", () -> false, Boolean.class);
        this.strictInsertFill(metaObject, "revision", () -> 1, Integer.class);
        this.strictInsertFill(metaObject, "isValid", () -> true, Boolean.class);
        this.strictInsertFill(metaObject, "isExpired", () -> false, Boolean.class);
        this.strictInsertFill(metaObject, "isLocked", () -> false, Boolean.class);
        this.strictInsertFill(metaObject, "status", () -> "ENABLE", String.class);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
        this.strictInsertFill(metaObject, "gmtModified", () -> LocalDateTime.now(), LocalDateTime.class);

    }
}
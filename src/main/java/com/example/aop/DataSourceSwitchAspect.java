package com.example.aop;

import com.example.config.DBTypeEnum;
import com.example.config.DbContextHolder;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


/**
 * @author 迪富
 */
@Component
@Order(value = -100)
@Slf4j
@Aspect
public class DataSourceSwitchAspect {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Pointcut("execution(* com.example.mapper.db1..*.*(..))")
    private void db1Aspect() {
    }

    @Pointcut("execution(* com.example.mapper.db2..*.*(..))")
    private void db2Aspect() {
    }

    @Pointcut("execution(* com.example.mapper.db3..*.*(..))")
    private void db3Aspect() {
    }

    @Before("db1Aspect()")
    public void db1() {
        logger.info("切换到db1 数据源...");
        DbContextHolder.setDbType(DBTypeEnum.db1);
    }

    @Before("db2Aspect()")
    public void db2() {
        logger.info("切换到db2 数据源...");
        DbContextHolder.setDbType(DBTypeEnum.db2);
    }

    @Before("db3Aspect()")
    public void db3() {
        logger.info("切换到db3 数据源...");
        DbContextHolder.setDbType(DBTypeEnum.db3);
    }
}
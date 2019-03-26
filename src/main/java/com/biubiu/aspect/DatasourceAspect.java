package com.biubiu.aspect;

import com.biubiu.annotation.Slave;
import com.biubiu.constants.DatabaseType;
import com.biubiu.datasource.DatabaseContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;

/**
 * Created by Haibiao.Zhang on 2019-03-26 10:20
 */
@Aspect
@Component
public class DatasourceAspect implements Ordered {

    @Override
    public int getOrder() {
        return 0;
    }

    @Around("@annotation(slave)")
    public Object proceed(ProceedingJoinPoint proceedingJoinPoint, Slave slave) throws Throwable {
        try {
            DatabaseContextHolder.setDatabaseType(DatabaseType.SLAVE);
            return proceedingJoinPoint.proceed();
        } finally {
            DatabaseContextHolder.clearDatabaseType();
        }
    }

}

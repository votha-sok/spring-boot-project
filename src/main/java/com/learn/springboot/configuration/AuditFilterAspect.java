package com.learn.springboot.configuration;


import com.learn.springboot.configuration.annotation.AuditFilter;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.MDC;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
@Aspect
@Component
public class AuditFilterAspect {
    private static final AtomicLong COUNTER = new AtomicLong(0);

    @Around("@annotation(auditFilter)")
    public Object logAudit(ProceedingJoinPoint joinPoint, AuditFilter auditFilter) throws Throwable {
        long requestNumber = COUNTER.incrementAndGet();
        String processId = requestNumber + "-" + UUID.randomUUID().toString().replace("-", "");

        MDC.put("processId", processId);

        Instant startTime = Instant.now();
        log.info("Request to controller start : {}", auditFilter.getClass().getSimpleName());

        try {
            return joinPoint.proceed();
        } finally {
            Instant endTime = Instant.now();
            long spentMillis = endTime.toEpochMilli() - startTime.toEpochMilli();
            log.info("request spent : {} ms", spentMillis);
            MDC.remove("processId");
        }
    }
}

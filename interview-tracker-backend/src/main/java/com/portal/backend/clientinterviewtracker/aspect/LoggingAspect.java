package com.portal.backend.clientinterviewtracker.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Pointcut("within(@org.springframework.stereotype.Repository *)" +
            " || within(@org.springframework.stereotype.Service *)" +
            " || within(@org.springframework.web.bind.annotation.RestController *)")
    public void springBeanPointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @Pointcut("execution(* com.portal.backend.clientinterviewtracker.dao..*(..))" +
            " || execution(* com.portal.backend.clientinterviewtracker.service..*(..))" +
            " || execution(* com.portal.backend.clientinterviewtracker.controller..*(..))" +
            " || execution(* com.portal.backend.clientinterviewtracker.exception..*(..))"
    )
    public void applicationPackagePointcut() {
        // Method is empty as this is just a Pointcut, the implementations are in the advices.
    }

    @AfterThrowing(pointcut = "applicationPackagePointcut()", throwing = "e")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable e) {
        log.error("Exception in {}.{}() with cause = {}", joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(), e.getCause() != null ? e.getCause() : e);
    }

    @Around("applicationPackagePointcut()")
    public Object logAround(ProceedingJoinPoint joinPoint) throws Throwable {

        if (log.isDebugEnabled() || log.isInfoEnabled()) {
            log.info("ExecutedThread[{}] Started: {}.{}() with argument[s] = {}",
                    Thread.currentThread().getId(), joinPoint.getSignature().getDeclaringTypeName(),
                    joinPoint.getSignature().getName(), Arrays.toString(joinPoint.getArgs()));
        }
        try {
            LocalDateTime start = LocalDateTime.now();
            Object result = joinPoint.proceed();
            LocalDateTime end = LocalDateTime.now();
            if (log.isDebugEnabled() || log.isInfoEnabled()) {
                log.info("ExecutedThread[{}] Exit: {}.{}() with result = {}",
                        Thread.currentThread().getId(),joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), result);
                log.info("Api has finished in : {}.{}() with result = {}", joinPoint.getSignature().getDeclaringTypeName(),
                        joinPoint.getSignature().getName(), Duration.between(start, end));
            }
            return result;
        } catch (IllegalArgumentException e) {
            log.error("ExecutedThread[{}] Illegal argument: {} in {}.{}()",
                    Thread.currentThread().getId(), Arrays.toString(joinPoint.getArgs()),
                    joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
            throw e;
        }
    }
}

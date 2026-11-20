package com.itheima.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;


@Slf4j
@Component
@Aspect
public class MyAspect {

    //切入点方法（公共的切入点表达式）
    @Pointcut("execution(* com.itheima.controller.*.*(..))")
    private void pt(){}

    //切入点方法（自定义注解的切入点）
    @Pointcut("@annotation(com.itheima.aspect.anno.LogOperation)")
    private void lo(){}


    //前置通知: 此方法的代码在目标方法执行之前执行
    @Before("pt()")
    public void before(JoinPoint joinPoint) {
        log.info("before ...");
    }

    //后置通知: 此方法的代码在目标方法执行之后执行，无论目标方法是否发生异常
    @After("pt()")
    public void after(JoinPoint joinPoint) {
        log.info("after ...");
    }

    //返回后通知：此方法的代码在目标方法正常执行之后执行
    @AfterReturning("lo()")
    public void afterReturning(JoinPoint joinPoint) {
        log.info("afterReturning ...");
    }

    //异常通知：此方法的代码在目标方法发生异常时执行
    @AfterThrowing("lo()")
    public void afterThrowing(JoinPoint joinPoint) {
        log.info("afterThrowing ...");
    }
}

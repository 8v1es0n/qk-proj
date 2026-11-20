package com.itheima.aspect;


import com.itheima.common.utils.UserHolder;
import com.itheima.entity.OperateLog;
import com.itheima.mapper.OperateLogMapper;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;

@Component
@Aspect
@Slf4j
public class LogAspect {
    @Autowired
    private OperateLogMapper operateLogMapper;


    @Around("@annotation(com.itheima.aspect.anno.LogOperation)")
    public Object recordTime(ProceedingJoinPoint pjp) throws Throwable {
        // 获取请求参数
        String methodArgs = Arrays.toString(pjp.getArgs());

        // 记录方法执行开始时间
        long begin = System.currentTimeMillis();

        // 执行 controller 方法
        Object proceed = pjp.proceed();

        // 记录方法执行结束时间
        long duration = System.currentTimeMillis() - begin;

        OperateLog operateLog = new OperateLog();

        operateLog.setOperateUserId(UserHolder.getCurrentUser()); //操作用户
        operateLog.setOperateTime(LocalDateTime.now()); // 操作时间
        operateLog.setClassName(pjp.getTarget().getClass().getName()); // 类名
        operateLog.setMethodName(pjp.getSignature().getName()); // 方法名
        operateLog.setMethodParams(methodArgs); // 参数
        operateLog.setReturnValue(proceed.toString()); // 返回值
        operateLog.setCostTime(duration); // 耗时

        operateLogMapper.insert(operateLog);

        return proceed;
    }
}

package com.panda.devops.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
@Component
public class LogCollector {
    private static final Logger logger = Logger.getLogger(LogCollector.class);

    @Pointcut("execution(* com.panda.devops.controller.*.*(..))")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void doBefore(JoinPoint joinPoint){
        // 接收到请求，记录请求内容
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        logger.info("StartTime:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        // 记录下请求内容
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        logger.info("IP : " + request.getRemoteAddr());
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("PARAMS :" + joinPoint.getArgs());
    }

    /**
     * 后置通知(正常)
     * @param ret
     */
    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfterReturning(Object ret){
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + ret.toString());
        logger.info("EndTime:" + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    }

    /**
     * 后置通知(异常)
     * @param e
     */
    @AfterThrowing(throwing = "e", pointcut = "webLog()")
    public void doAfterThrowing(JoinPoint joinPoint, Throwable e){
        // 处理完请求，返回内容
        logger.error("Exception : " + e.toString());

        System.out.println("进入切面AfterThrowing方法中...");

        //判断日志输出级别
        if(logger.isInfoEnabled()){
            logger.info("afterThrow " + joinPoint + "\t" + e.getMessage());
        }
        //详细错误信息
        String errorMsg = "";
        StackTraceElement[] trace = e.getStackTrace();
        for (StackTraceElement s : trace) {
            errorMsg += "\tat " + s + "\r\n";
        }
        System.out.println("具体异常信息："+errorMsg);

        System.out.println("afterThrow异常方法名 " + joinPoint + "\t" + e.getMessage());

        System.out.println("进入切面AfterThrowing方法结束！！！");

        //写入异常日志

    }

}

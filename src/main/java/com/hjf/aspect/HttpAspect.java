package com.hjf.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
/**
 * 功能描述:在GirlController.*(..)包下的所有类的所有方法都先被AOP切面(有点像拦截器),
 * 都记录日志操作
 * 
 * 技术点:
 * 1,下面通过Aspect注解来完成AOP面向切面编程,Aop的控制范围通过@Pointcut这个表达式来定义
 * 2,不是在servlet 中如何得到 HttpServletRequest
 * 3,JoinPoint中可以得到类和方法参数等信息
 *
 */
@Aspect
@Component
public class HttpAspect{

/*    //.. 代表不管是什么参数都会被拦截
    //public * com.blsmart.controller.GirlController.girlList(..)) 指定girlList方法
    // * 所有方法
    @Before("execution(public * com.blsmart.controller.GirlController.*(..))")
    public void log(){
        System.out.println(11111);
    }

    @After("execution(public * com.blsmart.controller.GirlController.*(..)))")
    public void log1(){
        System.out.println(2222);
    }*/

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);

    @Pointcut("execution(public * com.hjf.controller.GirlController.*(..))")
    public void filter() {

    }

    @Before("filter()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = (HttpServletRequest) attributes.getRequest();
        logger.info("url={}", request.getRequestURL());   //url
        logger.info("method={}", request.getMethod());  //method
        logger.info("ip={}", request.getRemoteAddr());    //ip
        //类方法
        logger.info("class_method={}", joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        logger.info("args={}", joinPoint.getArgs());       //参数
    }

    @After("filter()")
    public void doAfter() {
        logger.info("doAfter");
    }

    //returning 就是入参
    @AfterReturning(returning = "object", pointcut = "filter()")
    public void doAfterReturning(Object object) {
        logger.info("response={}", object);
    }

}

package com.liuwq.service.member.aop;

import liuwq.shop.service.base.BaseResponse;
import liuwq.shop.service.constants.Constants;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Component
@Aspect
public class BindingResultAop {

    /**
     * 切入点
     * 匹配com.liuwq.demo.controller包及其子包下的所有类的所有方法
     */
    @Pointcut("execution(* com.liuwq.service.member.controller.*.*(..))")
    public void bindingResult() {
    }

    @Around("bindingResult()")
    public Object doAround(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof BindingResult) {
                BindingResult result = (BindingResult) arg;
                if (result.hasErrors()) {
                    FieldError fieldError = result.getFieldError();
                    if (fieldError != null) {
                        return new BaseResponse(Constants.HTTP_RES_CODE_500, Constants.HTTP_RES_CODE_500_VALUE, fieldError.getDefaultMessage());
                    } else {
                        return new BaseResponse(Constants.HTTP_RES_CODE_500, Constants.HTTP_RES_CODE_500_VALUE, null);
                    }
                }
            }
        }
        return joinPoint.proceed();
    }

}

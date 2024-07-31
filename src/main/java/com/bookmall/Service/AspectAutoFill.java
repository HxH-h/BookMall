package com.bookmall.Service;

import com.bookmall.CusException.AutoUpdateTimeException;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class AspectAutoFill {

    @Pointcut("execution(* com.bookmall.Dao.Mapper.*.*(..)) && @annotation(com.bookmall.Service.AutoFill)")
    public void autoFillPointcut(){

    }

    @Before("autoFillPointcut()")
    public void AutoFill(JoinPoint joinPoint) throws AutoUpdateTimeException {
        System.out.println("自动添加");
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AutoFill annotation = signature.getMethod().getAnnotation(AutoFill.class);
        AutoFill.methodName value = annotation.value();

        Object[] args = joinPoint.getArgs();
        if (args == null || args.length == 0){
            return;
        }

        Object o = args[0];
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis());
        String format = formatter.format(date);
        try {
            if (value == AutoFill.methodName.ADD) {
                Method setAddtime = o.getClass().getDeclaredMethod("setAddtime", String.class);
                Method setUpdatetime = o.getClass().getDeclaredMethod("setUpdatetime", String.class);
                setAddtime.invoke(o, format);
                setUpdatetime.invoke(o, format);

            } else if (value == AutoFill.methodName.UPDATE) {
                Method setUpdatetime = o.getClass().getDeclaredMethod("setUpdatetime", String.class);
                setUpdatetime.invoke(o, format);
            }
        }catch (Exception e){
            throw new AutoUpdateTimeException();
        }
    }
}

package com.zh.framework.interceptor;

import com.alibaba.fastjson.JSON;
import com.zh.framework.annotation.Id;
import com.zh.framework.annotation.LogAnnotation;
import com.zh.framework.entity.sys.TbLogInfo;
import com.zh.framework.entity.sys.TbUser;
import com.zh.framework.service.sys.LogInfoServiceI;
import com.zh.framework.service.sys.UserServiceI;
import com.zh.framework.util.ConfigUtil;
import com.zh.framework.util.MD5Util;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志切面
 * Created by Mrkin on 2016/12/15.
 */
@Aspect
@Component
public class LogInfoAspect {
    @Autowired
    private UserServiceI userServiceI;
    @Autowired
    private LogInfoServiceI logInfoServiceI;


//    /**
//     * 如果开启controller是只能读取controller层的切面
//     */
//    //层切点
//    @Pointcut("@annotation(com.zh.framework.annotation.LogAnnotation)")
//    public void controllerAspect() {
//        System.out.println("controller切入点");
//    }
//

    /**
     * 如果开启service  能读取controller和service层的切面
     */
    @Pointcut("@annotation(com.zh.framework.annotation.LogAnnotation)")
    public void serviceAspect() {
        System.out.println("service切入点");
    }

    //    @AfterReturning(pointcut = "controllerAspect()")
//    public void docontrollerAfter(JoinPoint joinPoint) {
//        System.out.println("controller 执行");
//        if (isAnnotaion(joinPoint))
//            save(joinPoint);
//
//    }
    @AfterReturning(pointcut = "serviceAspect()")
    public void doServiceAfter(JoinPoint joinPoint) {
//        System.out.println("service执行");
        //if(ConfigUtil.get("isLogInfo").equals("true")){
            if (isAnnotaion(joinPoint))
                save(joinPoint);
        //}

    }

    public void save(JoinPoint joinPoint) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String userId = (String) request.getAttribute("userId");
        String url = request.getRequestURI();
        String ip = request.getRemoteAddr();
        //获取用户请求方法的参数并序列化为JSON格式字符串
//        String params = "";
//        if (joinPoint.getArgs() !=  null && joinPoint.getArgs().length > 0) {
//            for ( int i = 0; i < joinPoint.getArgs().length; i++) {
//                params += JSON.toJSONString(joinPoint.getArgs()[i]) + ";";
//            }
//        }
        try {
               /*==========数据库日志=========*/
            TbLogInfo log = new TbLogInfo();
            log.setCreateTime(new Date());
            log.setUrl(url);
            log.setlIp(ip);
            log.setlContent(getContent(joinPoint));
            log.setlOperation(getOperation(joinPoint));
            log.setType(getType(joinPoint));
            if (userId == null) {
                Map<String, Object> map = new HashMap<>();
                map.put("uName_=", request.getParameter("loginName"));
                String password = request.getParameter("password");
                TbUser tbUser = userServiceI.getByParam(map);
                if (tbUser != null && password != null && MD5Util.md5(password + tbUser.getuCredentialsSalt()).equals(tbUser.getuPassword())) {
                    log.setuId(tbUser.getId());
                } else {

                    log.setlContent("登录失败");
                }
            } else {
                log.setuId(userId);
            }
            //保存数据库
            logInfoServiceI.save(log);
        } catch (Exception ex) {
            //记录本地异常日志
            ex.printStackTrace();
        }
    }


    /**
     * 获取注解中操作内容
     *
     * @param joinPoint 切点
     * @return 内容
     * @throws Exception
     */
    public static String getContent(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String content = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
                    if (annotation != null) {
                        content = annotation.content();
                    }
                    System.out.println("class----" + targetName + "---------------method:-------" + methodName);
                    break;
                }
            }
        }
        return content;
    }


    /**
     * 获取注解中类型
     *
     * @param joinPoint 切点
     * @return 类型
     * @throws Exception
     */
    public static String getType(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        LogAnnotation.Type operation= LogAnnotation.Type.web;
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
                    if (annotation != null) {
                        operation = annotation.type();
                    }
                    break;
                }
            }
        }
        return operation.toString();
    }

    /**
     * 获取注解中内容
     *
     * @param joinPoint 切点
     * @return 内容
     * @throws Exception
     */
    public static String  getOperation(JoinPoint joinPoint)
            throws Exception {
        String targetName = joinPoint.getTarget().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] arguments = joinPoint.getArgs();
        Class targetClass = Class.forName(targetName);
        Method[] methods = targetClass.getMethods();
        String operation = "";
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                Class[] clazzs = method.getParameterTypes();
                if (clazzs.length == arguments.length) {
                    LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
                    if (annotation != null) {
                        operation = annotation.operation();
                    }
                    break;
                }
            }
        }
        return operation;
    }


    public boolean isAnnotaion(JoinPoint joinPoint) {
        boolean result = false;
        try {
            String targetName = joinPoint.getTarget().getClass().getName();
            Class targetClass = Class.forName(targetName);
            Method[] methods = targetClass.getMethods();
            for (Method method : methods) {
                LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
                if (annotation != null) {
                    result = true;
                    break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}

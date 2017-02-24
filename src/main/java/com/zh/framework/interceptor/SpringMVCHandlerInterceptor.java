package com.zh.framework.interceptor;

import com.alibaba.fastjson.JSON;
import com.zh.framework.util.HttpEntity;
import com.zh.framework.util.JwtUtil;
import com.zh.framework.util.StaticUtil;
import io.jsonwebtoken.Claims;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.Date;

/**token 拦截器
 * Created by Mrkin on 2016/11/8.
 */
public class SpringMVCHandlerInterceptor implements HandlerInterceptor {


    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("SpringMVCHandlerInterceptor");
        //获取head中的Authorization
        String auth = httpServletRequest.getHeader("Authorization");
        if ((auth != null) && (auth.length() > 7)) {
            Claims claims = JwtUtil.parseJWT(auth);
            if (claims != null) {
                try {
                    //获取token中的用户id
                    String userId = claims.get(JwtUtil.USERID).toString();
                    if (StaticUtil.LOGIN_TYPE_WEB.equals(claims.get(JwtUtil.TYPE))) {
                        if (JwtUtil.getTokenValidStore().get(userId+StaticUtil.LOGIN_TYPE_WEB) != null
                                && JwtUtil.getTokenValidStore().get(userId+StaticUtil.LOGIN_TYPE_WEB) - new Date().getTime() > 0) {
                            JwtUtil.getTokenValidStore().put(userId+StaticUtil.LOGIN_TYPE_WEB, new Date().getTime() + JwtUtil.webValidTIME);
                            httpServletRequest.setAttribute("userId",userId);
                            return true;
                        }
                    }else {
                        if (JwtUtil.getTokenValidStore().get(userId+StaticUtil.LOGIN_TYPE_WEB) != null &&
                                JwtUtil.getTokenValidStore().get(userId+StaticUtil.LOGIN_TYPE_WEB) - new Date().getTime() > 0) {
                            JwtUtil.getTokenValidStore().put(userId+StaticUtil.LOGIN_TYPE_WEB, new Date().getTime() + JwtUtil.appValidTIME);
                            httpServletRequest.setAttribute("userId",userId);
                            return true;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        httpServletResponse.setContentType("application/json; charset=UTF-8");
        PrintWriter out = httpServletResponse.getWriter();
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setCode(StaticUtil.CODE_ERROR_TOKEN);
        httpEntity.setSuccess(false);
        httpEntity.setMsg("请重新登录");
        out.print(JSON.toJSON(httpEntity));
        out.close();
        return false;
    }

    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        System.out.println("postHandle");
    }

    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
        System.out.println("afterCompletion");
    }
}

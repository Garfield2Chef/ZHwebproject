package com.zh.framework.interceptor;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.zh.framework.entity.sys.TbResource;
import com.zh.framework.service.sys.ResourceServiceI;
import com.zh.framework.util.HttpEntity;
import com.zh.framework.util.StaticUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.List;

/**权限 拦截器
 * Created by Mrkin on 2016/11/8.
 */
public class PermissionsHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private ResourceServiceI resourceServiceI;

    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        System.out.println("PermissionsHandlerInterceptor");
        String userId = (String) httpServletRequest.getAttribute("userId");
        String url= httpServletRequest.getRequestURI();
        List<TbResource> list= Lists.newArrayList();
        list.addAll(resourceServiceI.getWebListByUserId(userId, ResourceServiceI.Type.FUNCTION));
        for (TbResource tb_resource :list){
            if (url.contains(tb_resource.getsUrl())){
                return true;
            }
        }
        httpServletResponse.setContentType("application/json; charset=UTF-8");
        PrintWriter out = httpServletResponse.getWriter();
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setCode(StaticUtil.CODE_ERROR_PERMISSION);
        httpEntity.setSuccess(false);
        httpEntity.setMsg("请联系管理员授权["+url+"]");
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

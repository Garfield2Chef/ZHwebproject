package com.zh.framework.controller.app;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zh.framework.annotation.LogAnnotation;
import com.zh.framework.annotation.Table;
import com.zh.framework.controller.BaseController;
import com.zh.framework.entity.sys.TbResource;
import com.zh.framework.service.sys.ResourceRoleServiceI;
import com.zh.framework.service.sys.ResourceServiceI;
import com.zh.framework.service.sys.UserServiceI;
import com.zh.framework.util.HttpEntity;
import com.zh.framework.util.StaticUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**资源controller
 * Created by Mrkin on 2016/12/7.
 */
@Controller(value = "ResourceControllerApp")
@RequestMapping("/app/sys/resource")
public class ResourceController extends BaseController<TbResource> {

    @Autowired
    private ResourceRoleServiceI resourceRoleServiceI;
    @Autowired
    private UserServiceI userServiceI;
    @Autowired
    private ResourceServiceI resourceService;

    @LogAnnotation(content = "获取菜单", operation = "查询",type = LogAnnotation.Type.app)
    @ResponseBody
    @RequestMapping(value = "getMenus.do", method = RequestMethod.POST)
    public String getMenus(String userId){
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setMsg("获取成功");
        httpEntity.setObj(((ResourceServiceI)service).getAppListByUserId(userId, ResourceServiceI.Type.MEUN));
        return tojson(httpEntity);
    }
    @LogAnnotation(content = "获取功能", operation = "查询",type = LogAnnotation.Type.app)
    @ResponseBody
    @RequestMapping(value = "getFunctions.do", method = RequestMethod.POST)
    public String getFunctions(String userId){
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setMsg("获取成功");
        httpEntity.setObj(((ResourceServiceI)service).getAppListByUserId(userId, ResourceServiceI.Type.FUNCTION));
        return tojson(httpEntity);
    }

}

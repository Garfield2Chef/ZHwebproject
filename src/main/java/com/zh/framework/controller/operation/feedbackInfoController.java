package com.zh.framework.controller.operation;

import com.zh.framework.annotation.Table;
import com.zh.framework.controller.BaseController;
import com.zh.framework.entity.DataEntity;
import com.zh.framework.entity.data.TbDict;
import com.zh.framework.entity.operation.TbFeedbackInfo;
import com.zh.framework.service.operation.TbFeedbackInfoServiceI;
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

/**
 * Created by ZHLenovo001 on 2017/2/14.
 */
@Controller
@RequestMapping("/operation/feedbackInfo")
public class feedbackInfoController extends BaseController<TbFeedbackInfo> {

    @Override
    public String saveBody(@RequestBody TbFeedbackInfo tbFeedbackInfo) {
        int result=0;
        if (service.getById(tbFeedbackInfo.getId())!= null) {
            result=service.update(tbFeedbackInfo);
        } else {
            tbFeedbackInfo.setCreateTime(new Date());
            result=service.save(tbFeedbackInfo);
        }
        HttpEntity httpEntity = new HttpEntity();
        if (result==1) {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            saveLog(request, "保存", "对" + tbFeedbackInfo.getClass().getAnnotation(Table.class).value() + "表保存数据");
            httpEntity.setMsg("保存成功");
        }else {
            HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
            saveLog(request, "保存", "对" + tbFeedbackInfo.getClass().getAnnotation(Table.class).value() + "表保存数据");
            httpEntity.setCode(StaticUtil.CODE_ERROR_FAIL);
            httpEntity.setMsg("名称重复");
        }
        return tojson(httpEntity);
    }

}

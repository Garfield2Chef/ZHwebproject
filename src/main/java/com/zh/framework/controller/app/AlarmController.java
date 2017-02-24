package com.zh.framework.controller.app;

import com.zh.framework.controller.BaseController;
import com.zh.framework.entity.operation.TbAlarm;
import com.zh.framework.service.operation.AlarmServiceI;
import com.zh.framework.util.FileUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/2/13.
 */
@Controller(value = "AlarmControllerApp")
@RequestMapping("/app/operation/alarm")
public class AlarmController extends BaseController<TbAlarm> {
    @Autowired
    private AlarmServiceI alarmService;
    @ApiOperation(value = "报警上传", notes = "")
    @ResponseBody
    @RequestMapping(value = "nolimit_upload.do", method = RequestMethod.POST)
    public void upload(@RequestParam String json,@RequestParam CommonsMultipartFile[] files,HttpServletRequest request) {
//@RequestBody TbAlarm tbAlarm,
        TbAlarm tbAlarm= (TbAlarm) parse(json,TbAlarm.class);
        FileUtil.saveFile("",files[0],request);
        if(files!=null&&files.length>0){
            //进行操作吧
            for (int i=0;i<files.length;i++){
                switch (i){
                    case 0:
                        tbAlarm.setAlaPic1(FileUtil.saveFile("",files[0],request));
                        break;
                    case 1:
                        tbAlarm.setAlaPic2(FileUtil.saveFile("",files[1],request));
                        break;
                    case 2:
                        tbAlarm.setAlaPic3(FileUtil.saveFile("",files[2],request));
                        break;
                    case 3:
                        tbAlarm.setAlaPic4(FileUtil.saveFile("",files[3],request));
                        break;
                }
            }
        }
    }



}

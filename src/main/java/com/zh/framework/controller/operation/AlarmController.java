package com.zh.framework.controller.operation;

import com.zh.framework.annotation.Table;
import com.zh.framework.controller.BaseController;
import com.zh.framework.entity.operation.TbAlarm;
import com.zh.framework.service.operation.AlarmServiceI;
import com.zh.framework.util.FileUtil;
import com.zh.framework.util.HttpEntity;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Administrator on 2017/2/13.
 */
@Controller
@RequestMapping("/data/alarm")
public class AlarmController extends BaseController<TbAlarm> {
    @Autowired
    private AlarmServiceI alarmService;
    @ApiOperation(value = "上传", notes = "")
    @ResponseBody
    @RequestMapping(value = "upload.do", method = RequestMethod.POST)
    public String  upload(@RequestParam String json,@RequestParam(name = "files",required = false) CommonsMultipartFile[] files,HttpServletRequest request) {
//@RequestBody TbAlarm tbAlarm,
        TbAlarm tbAlarm= (TbAlarm) parse(json,TbAlarm.class);
        if(service.getById(tbAlarm.getId())==null){
            tbAlarm.setCreateTime(new Date());
            tbAlarm.setAlaNo(Getnum());
            tbAlarm.setAlaIsExtSys(0);
        }
      //  FileUtil.saveFile("",files[0],request);
        if(files!=null&&files.length>0){
            //进行操作吧
            for (int i=0;i<files.length;i++){
                switch (i){
                    case 0:
                        tbAlarm.setAlaPic1(FileUtil.saveFile("",files[0],request));
                        tbAlarm.setAlaPic2(null);
                        tbAlarm.setAlaPic3(null);
                        tbAlarm.setAlaPic4(null);
                        break;
                    case 1:
                        tbAlarm.setAlaPic2(FileUtil.saveFile("",files[1],request));
                        tbAlarm.setAlaPic3(null);
                        tbAlarm.setAlaPic4(null);
                        break;
                    case 2:
                        tbAlarm.setAlaPic3(FileUtil.saveFile("",files[2],request));
                        tbAlarm.setAlaPic4(null);
                        break;
                    case 3:
                        tbAlarm.setAlaPic4(FileUtil.saveFile("",files[3],request));
                        break;
                }
            }
        }
        super.save(tbAlarm);
        saveLog(request, "保存", "对" + tbAlarm.getClass().getAnnotation(Table.class).value() + "表保存数据");
        HttpEntity httpEntity = new HttpEntity();
        httpEntity.setMsg("保存成功!");
        return tojson(httpEntity);
    }

    public String getStringDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        System.out.println("TIME:::"+dateString);
        return dateString;
    }
    /**
     * 由年月日时分秒+3位随机数
     * 生成流水号
     * @return
     */
    public String Getnum(){
        String t = getStringDate();
        int x=(int)(Math.random()*900)+100;
        String serial = t + x;
        return serial;
    }


}

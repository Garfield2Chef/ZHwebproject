package com.zh.framework.controller.data;

import com.zh.framework.controller.BaseController;
import com.zh.framework.entity.data.VDevicePoint;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Marlon on 2016/11/7.
 */

@Controller(value = "VDevicePointController")
@RequestMapping("/data/vdevicepoint")
public class VDevicePointController extends BaseController<VDevicePoint> {


}

package com.zh.framework.controller;

import com.zh.framework.entity.sys.TbUser;
import com.zh.framework.service.sys.UserServiceI;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 46595 on 2016/10/31.
 */
@Controller
@RequestMapping("/test")
public class TestController extends BaseController<TbUser> {

    @Autowired
    private UserServiceI userServiceI;

    @ResponseBody
    @RequestMapping("test.do")
    public String test() {
        TbUser tb_user = new TbUser();
//        tb_user.setLoginName("loginName");
//        tb_user.setName("name");
//        tb_user.setPhone("13219550350");
        return tojson(userServiceI.getById("1"));
    }

    @RequestMapping("getUser.do")
    public String getCar() {
//        TbUser t_car = ;
        return tojson(userServiceI.getById("1"));
//        System.out.println(object.toString());
        //String result= object.toString();
        // return object.toString();
    }

    @ResponseBody
    @RequestMapping(value = "getBaseCar.do", method = RequestMethod.POST)
    public String getBaseCar() {
        TbUser t_car = userServiceI.getById("1");


        return t_car.toString() + "\n";

    }

    @ResponseBody
    @RequestMapping("getCarList.do")
    public String getCarList() {
        Map<String, Object> map = new HashMap<String, Object>();
        String[] strings = {"loginName", "sysadmin"}; //=new String[1];
        //strings[0]="sysadmin";
        map.put("username_=", "管理员");
        map.put("loginname_in", "'sysadmin','loginName'");
        //map.put("loginname_in", strings);
        Map<String, String> order = new HashMap<String, String>();
        order.put("id", "desc");
        List<TbUser> list = userServiceI.find(order);
        // List<TbUser> list = userServiceI.findParams(map,order);
//        String[] include = {"carid", "carno"};
        return tojson(list);
    }


}

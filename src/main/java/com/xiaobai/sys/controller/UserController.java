package com.xiaobai.sys.controller;

import com.xiaobai.sys.base.BaseController;
import com.xiaobai.sys.base.SysParam;
import com.xiaobai.sys.entity.UserInfo;
import com.xiaobai.sys.service.UserDetailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Map;

@Controller
@RequestMapping("sys/user")
public class UserController extends BaseController {
    @Autowired
    private UserDetailInfo service;
    @RequestMapping("index")
    public String getIndex(){
        return "sys/user/index";
    }
    @RequestMapping("getAll")
    @ResponseBody
    public Map<String,Object> getAll(SysParam param){
        Map<String,Object> result= service.getAll(param);
        return result;
    }
    @RequestMapping("addOrUpdate")
    @ResponseBody
    public Map<String,Object> addOrUpdate(UserInfo user){
        Map<String,Object> result= service.addOrUpdate(user);
        return result;
    }
    //---------------------以下是密码管理界面------------
    @RequestMapping("password")
    public String getPw(){
        return "sys/user/password";
    }
    @RequestMapping("updatepwd")
    @ResponseBody
    public Map<String,Object> updatepwd(SysParam param){
        Map<String,Object> result= service.updatePwd(param);
        return result;
    }
}

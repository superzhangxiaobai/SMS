package com.xiaobai.model.controller;

import com.xiaobai.model.entity.Sign;
import com.xiaobai.model.service.SignService;
import com.xiaobai.sys.base.BaseController;
import com.xiaobai.sys.base.SysParam;
import com.xiaobai.sys.entity.UserInfo;
import com.xiaobai.sys.service.UserDetailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("model/sign")
public class SignController extends BaseController {
    @Autowired
    private SignService service;
    @Autowired
    private UserDetailInfo userService;
    @RequestMapping("index")
    public String getIndex(){
        return "model/sign/index";
    }
    @RequestMapping("getAll")
    @ResponseBody
    public List<Map<String,Object>> getAll(@RequestParam Map<String,Object> param){
        List<Map<String,Object>> result= service.getAll(param);
        return result;
    }
    @RequestMapping("getAllMap")
    @ResponseBody
    public Map<String,Object> getAllMap(@RequestParam Map<String,Object> param){
        Map<String,Object> result= service.getAllMap(param);
        return result;
    }
    @RequestMapping("addOrUpdate")
    @ResponseBody
    public Map<String,Object> addOrUpdate(Sign entity){
        UserDetails auth = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo user = userService.selectOne(new UserInfo(null,auth.getUsername()));
        entity.setCreator(auth.getUsername());
        entity.setUserid(user.getId());
        Map<String,Object> result= service.addOrUpdate(entity);
        return result;
    }
}

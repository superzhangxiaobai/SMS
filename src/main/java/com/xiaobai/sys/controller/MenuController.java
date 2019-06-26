package com.xiaobai.sys.controller;

import com.xiaobai.sys.base.BaseController;
import com.xiaobai.sys.entity.MenuInfo;
import com.xiaobai.sys.base.SysParam;
import com.xiaobai.sys.service.MenuServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("sys/menu")
public class MenuController extends BaseController {
    @Autowired
    private MenuServiceImpl service;
    @RequestMapping("index")
    //@Secured({"ROLE_ADMIN"})//只能有管理人员操作菜单页面,硬编码不适合
    public String getAllMenuIndex(){
        return "sys/menu/index";
    }
    @RequestMapping("getAll")
    @ResponseBody
    public Map<String,Object> getAll(SysParam param){
        Map<String,Object> result= service.getAll(param);
        return result;
    }
    @RequestMapping(value = "addOrUpdate",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> addOrUpdateMenu(MenuInfo menu){
        UserDetails auth = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        menu.setCreator(auth.getUsername());
        Map<String,Object> result= service.addOrUpdate(menu);
        return result;
    }
}

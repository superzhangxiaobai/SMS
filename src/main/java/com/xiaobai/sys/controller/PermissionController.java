package com.xiaobai.sys.controller;

import com.xiaobai.sys.entity.MenuInfo;
import com.xiaobai.sys.service.PermissionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("sys/permission")
public class PermissionController {
    @Autowired
    private PermissionServiceImpl service;
    @RequestMapping("index")
    public String getIndex(){
        return "sys/permission/index";
    }
    @RequestMapping("user")
    @ResponseBody
    public List<MenuInfo> getPermission(){
        return service.getPermissions();
    }
}

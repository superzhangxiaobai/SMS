package com.xiaobai.sys.controller;

import com.xiaobai.sys.base.BaseController;
import com.xiaobai.sys.base.SysParam;
import com.xiaobai.sys.entity.RoleInfo;
import com.xiaobai.sys.service.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("sys/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleServiceImpl service;
    @RequestMapping("getAll")
    @ResponseBody
    public Map<String,Object> getAll(SysParam param){
        param.setPageSize(1000);//设置不限定内容
        Map<String,Object> result= service.getAll(param);
        return result;
    }
    @RequestMapping("addOrUpdate")
    @ResponseBody
    public Map<String,Object> addOrUpdate(RoleInfo role){
        Map<String,Object> result= service.addOrUpdate(role);
        return result;
    }
}

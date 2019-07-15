package com.xiaobai.model.controller;

import com.xiaobai.model.entity.Project;
import com.xiaobai.model.service.ProjectService;
import com.xiaobai.sys.base.BaseController;
import com.xiaobai.sys.entity.UserInfo;
import com.xiaobai.sys.service.UserDetailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("model/project")
public class ProjectController extends BaseController {
    @RequestMapping("index")
    public String getIndex(){
        return "model/project/index";
    }
    @Autowired
    private ProjectService service;
    @Autowired
    private UserDetailInfo userService;
    @RequestMapping("getAllMap")
    @ResponseBody
    public Map<String,Object> getAllMap(@RequestParam Map<String,Object> param){
        Map<String,Object> result= service.getAllMap(param);
        return result;
    }
    @RequestMapping("addOrUpdate")
    @ResponseBody
    public Map<String,Object> addOrUpdate(Project entity){
        UserDetails auth = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        entity.setCreator(auth.getUsername());
        entity.setCreatetime(new Date());
        Map<String,Object> result= service.addOrUpdate(entity);
        return result;
    }
}

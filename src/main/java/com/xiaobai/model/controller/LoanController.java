package com.xiaobai.model.controller;

import com.xiaobai.model.entity.Loan;
import com.xiaobai.model.service.LoanService;
import com.xiaobai.sys.base.BaseController;
import com.xiaobai.sys.base.SysParam;
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
@RequestMapping("model/loan")
public class LoanController extends BaseController {
    @Autowired
    private LoanService service;
    @RequestMapping("index")
    public String getIndex(){
        return "model/loan/index";
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
    public Map<String,Object> addOrUpdate(Loan entity){
        UserDetails auth = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        entity.setCreator(auth.getUsername());
        Map<String,Object> result= service.addOrUpdate(entity);
        return result;
    }

}

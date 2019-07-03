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
import org.springframework.web.bind.annotation.ResponseBody;

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
    public Map<String,Object> getAll(SysParam param){
        Map<String,Object> result= service.getAll(param);
        super.joinQueue("helllo");
        return result;
    }
    @RequestMapping("addOrUpdate")
    @ResponseBody
    public Map<String,Object> addOrUpdate(Loan loan){
        UserDetails auth = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        loan.setCreator(auth.getUsername());
        Map<String,Object> result= service.addOrUpdate(loan);
        return result;
    }

}

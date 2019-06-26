package com.xiaobai.model.controller;

import com.xiaobai.model.service.LoanService;
import com.xiaobai.sys.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}

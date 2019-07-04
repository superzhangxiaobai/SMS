package com.xiaobai.model.controller;

import com.xiaobai.sys.base.BaseEmail;
import com.xiaobai.sys.entity.MailBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("model")
public class ModelController {
    @RequestMapping("Graph/index")
    public String getGraphIndex(){
        return "model/Graph/index";
    }
    @RequestMapping("QRCode/index")
    public String getQRCodeIndex(){
        return "model/QRCode/index";
    }
    @RequestMapping("Email/index")
    public String getEmailIndex(){
        return "model/Email/index";
    }
    @Autowired
    private BaseEmail baseEmailService;
    @RequestMapping("Email/send")
    @ResponseBody
    public String senEmail(MailBean mail){
        baseEmailService.sendSimpleMail(mail);
        return "发送成功";
    }

}

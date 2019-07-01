package com.xiaobai.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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
}

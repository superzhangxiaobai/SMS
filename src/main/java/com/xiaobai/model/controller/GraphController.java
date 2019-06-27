package com.xiaobai.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("model/graph")
public class GraphController {
    @RequestMapping("index")
    public String getIndex(){
        return "model/graph/index";
    }
}

package com.xiaobai.model.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("model")
public class GraphController {
    @RequestMapping("graph/index")
    public String getGraphIndex(){
        return "model/graph/index";
    }
    @RequestMapping("graph/index")
    public String getGraphIndex(){
        return "model/graph/index";
    }
}

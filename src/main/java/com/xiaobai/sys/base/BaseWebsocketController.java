package com.xiaobai.sys.base;

import com.xiaobai.config.WebsocketServer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@Controller
@RequestMapping("base/websocket")
public class BaseWebsocketController {
    //页面请求
    @GetMapping("/index")
    public ModelAndView socket() {
        UserDetails auth = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ModelAndView mav=new ModelAndView("model/Websocket/index");
        mav.addObject("currentuser", auth.getUsername());
        return mav;
    }
    //推送数据接口
    @ResponseBody
    @RequestMapping("/push/{user}")
    public void pushToWeb(@PathVariable String user,String message) {//ApiReturnObject
        try {
            WebsocketServer.sendInfo(message,user);
        } catch (IOException e) {
            e.printStackTrace();
            //return ApiReturnUtil.error(cid+"#"+e.getMessage());
        }
        //return ApiReturnUtil.success(cid);
    }
}

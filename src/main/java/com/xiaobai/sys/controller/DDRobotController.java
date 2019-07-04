package com.xiaobai.sys.controller;

import com.xiaobai.sys.entity.DDMsg;
import com.xiaobai.util.DDRobotTask;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/dd")
public class DDRobotController {
    @Value("${spring.boot.admin.notify.dingtalk.webhook-token}")
    private String webhook;
    @PostMapping(value = "/sendMsg")
    public String sendTextMessage(DDMsg msg) {
        if(msg.getMsgtype()==null){
            return "请求错误, 没有消息类型";
        }
        switch (msg.getMsgtype()){
            case "text":DDRobotTask.sendToDingding(msg.toTextJsonString(), webhook);
            case "link":DDRobotTask.sendToDingding(msg.toLinkJsonString(), webhook);
            case "markdown":DDRobotTask.sendToDingding(msg.toMarkdownJsonString(), webhook);
        }
        return "发送成功";
    }
}

package com.xiaobai.sys.base;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class BaseMsgReceiver {
    @JmsListener(destination = "msgbox",containerFactory = "myFactory")
    public void sendMessage(String msg){
        System.out.println("<<"+msg+">>");
    }
}

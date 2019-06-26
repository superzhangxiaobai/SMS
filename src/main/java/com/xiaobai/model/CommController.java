package com.xiaobai.model;

import com.xiaobai.sys.base.BaseController;
import com.xiaobai.util.CommUtils;
import com.xiaobai.util.CommUtils2;
import gnu.io.CommPort;
import gnu.io.CommPortIdentifier;
import gnu.io.PortInUseException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.Map;

@Controller
@RequestMapping("model/comm")
public class CommController extends BaseController {
        @RequestMapping("test")
        @ResponseBody
        public void test(){
            //读取comm接口
            CommUtils.listPorts();
            CommUtils.listCommPorts();
            //向comm输入输出数据
            Thread thread=new Thread(new CommUtils2());
            thread.start();
        }
    @RequestMapping("user/login")
    @ResponseBody
    public Map<String,Object> appLogin(String username, String password){
        System.out.println("app请求登录页面");
        return null;
    }
}

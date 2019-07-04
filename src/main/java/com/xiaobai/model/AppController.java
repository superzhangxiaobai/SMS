package com.xiaobai.model;

import com.xiaobai.config.MyAuthenticationProvider;
import com.xiaobai.sys.service.UserDetailInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("app")
public class AppController {
    @Autowired
    private UserDetailInfo userService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    private MyAuthenticationProvider provider;
    @RequestMapping("login")
    public Map<String,Object> appLogin(String username, String password, HttpServletRequest request){
        Map<String,Object> result=new HashMap<>();
        System.out.println("app请求登录页面");
        //根据用户名username加载userDetails
        UserDetails userDetails =userService.loadUserByUsername(username);

        //根据userDetails构建新的Authentication,这里使用了
        //PreAuthenticatedAuthenticationToken当然可以用其他token,如UsernamePasswordAuthenticationToken   
        try{
            //UsernamePasswordAuthenticationToken up=new UsernamePasswordAuthenticationToken(userDetails,password,userDetails.getAuthorities());
            PreAuthenticatedAuthenticationToken authentication =new PreAuthenticatedAuthenticationToken(userDetails,password,userDetails.getAuthorities());
            //设置authentication中details
            authentication.setDetails(new WebAuthenticationDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            provider.authenticate(authentication);
            //存放authentication到SecurityContextHolder
            HttpSession session=request.getSession(true);
            //在session中存放security context,方便同一个session中控制用户的其他操作
            session.setAttribute("SPRING_SECURITY_CONTEXT",SecurityContextHolder.getContext());
             result.put("code",200);
             result.put("token",session.getId());
            result.put("msg","登陆成功");
        }catch (DisabledException e){
            e.printStackTrace();
            result.put("code",404);
            result.put("token","");
            result.put("msg","账号密码错误");
        }
        return result;
    }
    @RequestMapping("user")
    public Map<String,Object> test(){
        System.out.println("app请求登录页面");
        return null;
    }

}

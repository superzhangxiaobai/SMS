package com.xiaobai.sys.service;

import com.xiaobai.sys.entity.MenuInfo;
import com.xiaobai.sys.entity.UserInfo;
import com.xiaobai.sys.mapper.MenuMapper;
import com.xiaobai.sys.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionServiceImpl {
    @Autowired
    private UserInfoMapper userMapper;
    @Autowired
    private MenuMapper mapper;
    public List<MenuInfo> getPermissions() {
        UserDetails auth = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserInfo user = userMapper.getUserLoginInfo(auth.getUsername());
        List<MenuInfo> list = mapper.getListByUserid(user.getId());
        //List<MenuInfo> list = mapper.selectAll();
        return list;
    }
}

package com.xiaobai.sys.service;

import com.xiaobai.sys.base.BaseMapper;
import com.xiaobai.sys.base.BaseService;
import com.xiaobai.sys.entity.MenuInfo;
import com.xiaobai.sys.base.SysParam;
import com.xiaobai.sys.mapper.MenuMapper;
import com.xiaobai.sys.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MenuServiceImpl extends BaseService<MenuInfo> {
    @Autowired
    private UserInfoMapper userMapper;
    @Autowired
    private MenuMapper mapper;

    public Map<String,Object> getAll(SysParam param) {
        Map<String,Object> result=new HashMap<>();
        param.setTablename("t_menu");
        param.setPageSize(1000);
        List<MenuInfo> list = mapper.getAll(param);
        result.put("data",list);
        //可加入分页, 总数等数据
        return result;
    }

    @Override
    public BaseMapper<MenuInfo> getMapper() {
        return mapper;
    }

   /* public Map<String,Object> addOrUpdateMenu(MenuInfo menu) {
        UserDetails auth = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username= userMapper.getUserLoginInfo(auth.getUsername()).getUsername();
        Date now=new Date();
        Map<String,Object> result=new HashMap<>();
        if(menu.getId()==null){
            menu.setCreatetime(now);
            menu.setCreator(username);
            int insert= mapper.insertSelective(menu);
            result.put("msg",insert>0?"操作成功":"操作失败");
            result.put("data",menu);
        }else {
            int update= mapper.updateByPrimaryKeySelective(menu);
            result.put("msg",update>0?"操作成功":"操作失败");
            result.put("data",menu);
        }
        return result;
    }*/
}

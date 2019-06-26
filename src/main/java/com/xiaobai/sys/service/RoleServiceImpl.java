package com.xiaobai.sys.service;

import com.xiaobai.sys.base.SysParam;
import com.xiaobai.sys.entity.RoleInfo;
import com.xiaobai.sys.entity.RoleMenu;
import com.xiaobai.sys.mapper.RoleMapper;
import com.xiaobai.sys.mapper.RoleMenuMapper;
import com.xiaobai.sys.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class RoleServiceImpl {
    //数据库操作
    @Autowired
    private UserInfoMapper userMapper;
    @Autowired
    private RoleMapper mapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;
    public Map<String,Object> getAll(SysParam param) {
        Map<String,Object> result=new HashMap<>();
        List<RoleInfo> list = mapper.getAll(param);
        result.put("data",list);
        //可加入分页, 总数等数据
        return result;
    }

    public Map<String,Object> addOrUpdate(RoleInfo role) {
        UserDetails auth = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username= userMapper.getUserLoginInfo(auth.getUsername()).getUsername();
        Date now=new Date();
        Map<String,Object> result=new HashMap<>();
        if(role.getId()==null){
            role.setCreatetime(now);
            role.setCreator(username);
            int insert= mapper.insertSelective(role);//非空属性插入
            result.put("msg",insert>0?"操作成功":"操作失败");
            result.put("data",role);
        }else {
            role.setUpdatetime(now);
            role.setUpdator(username);
            int update= mapper.updateByPrimaryKeySelective(role);//根据主键进行非空属性更改
            result.put("msg",update>0?"操作成功":"操作失败");
            result.put("data",role);
        }
        //更新角色菜单表
        SysParam param=new SysParam();
        param.setRoleid(role.getId().toString());
        List<RoleMenu> menus = roleMenuMapper.getAll(param);
        List<String> newMenuids = Arrays.asList(role.getMenus().split(","));
        List<String> oldMenuids=new ArrayList<>();
        for (RoleMenu menu: menus){
            //新值中不包含旧值就删除
            if(!newMenuids.contains(menu.getMenuid().toString())){
                RoleMenu temp=new RoleMenu();
                temp.setId(menu.getId());
                roleMenuMapper.delete(temp);
            }
            oldMenuids.add(menu.getMenuid().toString());
        }
        for (String menuid: newMenuids){
            //旧值中不包含新值就插入
            if(!oldMenuids.contains(menuid)){
                RoleMenu temp=new RoleMenu();
                temp.setRoleid(role.getId());
                temp.setMenuid(Long.parseLong(menuid));
                roleMenuMapper.insertSelective(temp);
            }
        }
        return result;
    }
}

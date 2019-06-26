package com.xiaobai.sys.mapper;

import com.xiaobai.sys.base.BaseDaoProvider;
import com.xiaobai.sys.base.BaseMapper;
import com.xiaobai.sys.entity.MenuInfo;
import com.xiaobai.sys.base.SysParam;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public interface MenuMapper extends BaseMapper<MenuInfo> {
    @Select("select * from t_menu where roleid=#{roleid}")
    List<MenuInfo> getListByRoleid(Long roleid);
    @Select(" select menu.* from t_user userinfo, t_user_role user_role, t_role role, t_role_menu role_menu, t_menu menu" +
            " where userinfo.id=user_role.userid and user_role.roleid=role.id and role.id=role_menu.roleid and role_menu.menuid=menu.id"
            +" and userid=#{userid} " +
        "GROUP BY menu.menuname,menu.url,menu.pid,menu.isEnable,menu.icon,menu.createtime,menu.creator,menu.status,menu.type,menu.id")
    List<MenuInfo> getListByUserid(Long userid);
    @SelectProvider(type = BaseDaoProvider.class, method = "find")
    List<MenuInfo> getAll(SysParam param);
}

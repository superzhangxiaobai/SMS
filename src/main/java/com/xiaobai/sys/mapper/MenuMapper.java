package com.xiaobai.sys.mapper;

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
        "GROUP BY menu.menuname,menu.url,menu.pid,menu.isEnable,menu.icon,menu.createtime,menu.creator,menu.updatetime,menu.updator,menu.status,menu.type,menu.id")
    List<MenuInfo> getListByUserid(Long userid);
    @SelectProvider(type = DaoProvider.class, method = "find")
    List<MenuInfo> getAll(SysParam param);

    /*@Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    @InsertProvider(type=DaoProvider.class,method = "insert")
    int insert(MenuInfo menu);
    @InsertProvider(type=DaoProvider.class,method = "update")
    int update(MenuInfo menu);*/


    class DaoProvider {
        private static final String tablename="t_menu";
        public String find(SysParam param) {
            return new SQL(){{
                SELECT("*");
                FROM(tablename);
                WHERE("isEnable= #{isEnable, jdbcType=BIT}");
                if(param.getId()!=null){
                    WHERE(" id = #{id, jdbcType=INTEGER}");
                }
                if(param.getPid()!=null){
                    WHERE(" pid = #{pid, jdbcType=INTEGER}");
                }
                if(param.getStatus()!=null){
                    WHERE(" status = #{status, jdbcType=INTEGER}");
                }
            }}.toString();
        }
        /*public String insert(MenuInfo param){
            SQL sql = new SQL(); //SQL语句对象，所在包：org.apache.ibatis.jdbc.SQL
            sql.INSERT_INTO(tablename);
            if(param.getMenuname() != null){
                sql.VALUES("menuname", "#{menuname}");
            }
            if(param.getUrl() != null){
                sql.VALUES("url", "#{url}");
            }
            if(param.getPid() != null){
                sql.VALUES("pid", "#{pid}");
            }
            if(param.getIcon() != null){
                sql.VALUES("icon", "#{icon}");
            }
            sql.VALUES("isEnable", "#{isEnable,jdbcType=BIT}");
            sql.VALUES("createtime", "#{createtime,jdbcType=DATE}");
            sql.VALUES("creator", "#{creator,jdbcType=VARCHAR}");
            return sql.toString();
        }
        public String update(MenuInfo param){
            return new SQL(){{
                UPDATE(tablename);
                if (!StringUtils.isEmpty(param.getMenuname())) {
                    SET("menuname= #{menuname,jdbcType=VARCHAR}");
                }
                if (!StringUtils.isEmpty(param.getUrl())) {
                    SET("url= #{url,jdbcType=VARCHAR}");
                }
                if (param.getPid()!=null) {
                    SET("pid=#{pid,jdbcType=VARCHAR}");
                }
                if (param.getStatus()!=null) {
                    SET("status=#{status,jdbcType=INTEGER}");
                }
                SET("isEnable=#{isEnable,jdbcType=BIT}");
                SET("updatetime=#{updatetime,jdbcType=DATE}");
                SET("updator=#{updator,jdbcType=VARCHAR}");
                WHERE("id = #{id,jdbcType=INTEGER}" );
            }}.toString();
        }*/
    }
}

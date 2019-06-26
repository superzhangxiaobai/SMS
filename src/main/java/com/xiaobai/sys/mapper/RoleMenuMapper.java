package com.xiaobai.sys.mapper;

import com.alibaba.druid.util.StringUtils;
import com.xiaobai.sys.base.BaseMapper;
import com.xiaobai.sys.base.SysParam;
import com.xiaobai.sys.entity.RoleMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

@Mapper
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {
    @SelectProvider(type = DaoProvider.class, method = "find")
    List<RoleMenu> getAll(SysParam param);
    class DaoProvider {
        private static final String tablename = "t_role_menu";
        public String find(SysParam param) {
            return new SQL() {{
                SELECT("*");
                FROM(tablename);
                if (!StringUtils.isEmpty(param.getMenuid())) {
                    WHERE(" menuid = #{menuid, jdbcType=VARCHAR}");
                }
                if (!StringUtils.isEmpty(param.getRoleid())) {
                    WHERE(" roleid = #{roleid, jdbcType=VARCHAR}");
                }
                ORDER_BY("id desc");
            }}.toString();
        }
    }
}

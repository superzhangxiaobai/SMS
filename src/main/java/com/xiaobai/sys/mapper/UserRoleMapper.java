package com.xiaobai.sys.mapper;

import com.alibaba.druid.util.StringUtils;
import com.xiaobai.sys.base.BaseMapper;
import com.xiaobai.sys.base.SysParam;
import com.xiaobai.sys.entity.UserRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

@Mapper
public interface UserRoleMapper extends BaseMapper<UserRole> {
    @SelectProvider(type = DaoProvider.class, method = "find")
    List<UserRole> getAll(SysParam param);
    class DaoProvider {
        private static final String tablename = "t_user_role";
        public String find(SysParam param) {
            String sql= new SQL() {{
                SELECT("*");
                FROM(tablename);
                if (!StringUtils.isEmpty(param.getUserid())) {
                    WHERE(" userid = #{userid, jdbcType=VARCHAR}");
                }
                if (!StringUtils.isEmpty(param.getRoleid())) {
                    WHERE(" roleid = #{roleid, jdbcType=VARCHAR}");
                }
                ORDER_BY("id desc");
            }}.toString();
            return sql;
        }
    }
}

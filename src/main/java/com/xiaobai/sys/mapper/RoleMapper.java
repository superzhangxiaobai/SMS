package com.xiaobai.sys.mapper;

import com.xiaobai.sys.base.BaseMapper;
import com.xiaobai.sys.base.SysParam;
import com.xiaobai.sys.entity.RoleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
@Mapper
public interface RoleMapper extends BaseMapper<RoleInfo> {
    @SelectProvider(type = DaoProvider.class, method = "find")
    List<RoleInfo> getAll(SysParam param);

    class DaoProvider {
        private static final String tablename = "t_role";
        public String find(SysParam param) {
            return new SQL() {{
                SELECT("*");
                FROM(tablename);
                if (param.getStatus() != null) {
                    WHERE(" status = #{status, jdbcType=INTEGER}");
                }
                ORDER_BY("id desc");
            }}.toString();
        }
    }
}

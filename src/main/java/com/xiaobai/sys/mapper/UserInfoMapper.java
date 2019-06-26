package com.xiaobai.sys.mapper;

import com.alibaba.druid.util.StringUtils;
import com.xiaobai.sys.base.BaseMapper;
import com.xiaobai.sys.base.SysParam;
import com.xiaobai.sys.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

import static com.alibaba.druid.sql.dialect.mysql.ast.MySqlIndexHint.Option.ORDER_BY;

@Mapper
public interface UserInfoMapper extends BaseMapper<UserInfo> {
	@Select("select * from t_user userinfo where loginname=#{loginname,jdbcType=VARCHAR}")
	UserInfo getUserLoginInfo(String loginname);
	@SelectProvider(type = DaoProvider.class, method = "find")
	List<UserInfo> getAll(SysParam param);
	class DaoProvider {
		private static final String tablename = "t_user";

		public String find(SysParam param) {
			return new SQL() {{
				SELECT("*");
				FROM(tablename);
				if (param.getId() != null) {
					WHERE(" id = #{id, jdbcType=INTEGER}");
				}
				if (param.getStatus() != null) {
					WHERE(" status = #{status, jdbcType=INTEGER}");
				}
				if (!StringUtils.isEmpty(param.getUsername())) {
					WHERE(" username like concat('%',#{username},'%')");
				}
				ORDER_BY("id desc");
			}}.toString();
		}
	}
}

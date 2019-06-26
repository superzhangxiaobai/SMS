package com.xiaobai.sys.mapper;

import com.xiaobai.sys.base.BaseDaoProvider;
import com.xiaobai.sys.base.BaseMapper;
import com.xiaobai.sys.base.SysParam;
import com.xiaobai.sys.entity.RoleInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;
@Mapper
public interface RoleMapper extends BaseMapper<RoleInfo> {
    @SelectProvider(type = BaseDaoProvider.class, method = "find")
    List<RoleInfo> getAll(SysParam param);
}

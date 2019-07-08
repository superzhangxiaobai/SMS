package com.xiaobai.sys.base;


import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;

/**
 * 基础CRUD操作接口
 * Mapper
 */
public interface BaseMapper<T>extends tk.mybatis.mapper.common.BaseMapper<T> {
    @SelectProvider(type = BaseDaoProvider.class, method = "find")
    List<T> getAll(SysParam param);

    @SelectProvider(type = BaseDaoProvider.class, method = "count")
    Integer getCount(SysParam param);
}

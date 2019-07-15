package com.xiaobai.sys.base;


import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.SelectProvider;
import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

import java.util.List;
import java.util.Map;

/**
 * 基础CRUD操作接口
 * Mapper
 */
public interface BaseMapper<T>extends tk.mybatis.mapper.common.BaseMapper<T> {
    /**
     * 系统模块使用, 未改成通用版
     * @param param
     * @return
     */
    @SelectProvider(type = BaseDaoProvider.class, method = "find")
    List<T> getAll(SysParam param);
    @SelectProvider(type = BaseDaoProvider.class, method = "count")
    Integer getCount(SysParam param);

    /**
     * 参数为Map,分页
     * @param param
     * @return
     */
    @SelectProvider(type = BaseDaoProvider.class, method = "findByMapToPage")
    List<Map<String,Object>> getAllMap(Map<String,Object> param);
    /**
     * 参数为Map,不分页
     * @param param
     * @return
     */
    @SelectProvider(type = BaseDaoProvider.class, method = "findByMap")
    List<Map<String,Object>> getAllList(Map<String,Object> param);
    /**
     * 参数为Map,count
     * @param param
     * @return
     */
    @SelectProvider(type = BaseDaoProvider.class, method = "countByMap")
    Integer getAllCount(Map<String,Object> param);

}

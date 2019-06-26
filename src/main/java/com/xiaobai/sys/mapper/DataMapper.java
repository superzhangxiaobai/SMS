package com.xiaobai.sys.mapper;

import com.xiaobai.sys.entity.DbUser;
import org.apache.ibatis.annotations.Mapper;

/*
 * Mybatis数据映射，数据库sql见: resource/mybatis/db.sql
 */
@Mapper
public interface DataMapper {
	DbUser getUserLoginInfo(String username);
}

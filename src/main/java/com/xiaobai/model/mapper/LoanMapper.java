package com.xiaobai.model.mapper;


import com.xiaobai.model.entity.Loan;
import com.xiaobai.sys.base.BaseDaoProvider;
import com.xiaobai.sys.base.BaseMapper;
import com.xiaobai.sys.base.SysParam;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

@Mapper
public interface LoanMapper extends BaseMapper<Loan> {
    @SelectProvider(type = BaseDaoProvider.class, method = "find")
    List<Loan> getAll(SysParam param);
}

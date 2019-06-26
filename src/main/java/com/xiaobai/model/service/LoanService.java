package com.xiaobai.model.service;

import com.xiaobai.model.entity.Loan;
import com.xiaobai.model.mapper.LoanMapper;
import com.xiaobai.sys.base.BaseMapper;
import com.xiaobai.sys.base.BaseService;
import com.xiaobai.sys.base.SysParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LoanService extends BaseService<Loan> {
    @Autowired
    private LoanMapper mapper;
    @Override
    public BaseMapper<Loan> getMapper() {
        return mapper;
    }
    public Map<String,Object> getAll(SysParam param) {
        param.setTablename("t_loan");
        Map<String,Object> result=new HashMap<>();
        List<Loan> list = mapper.getAll(param);
        result.put("data",list);
        //可加入分页, 总数等数据
        return result;
    }
}

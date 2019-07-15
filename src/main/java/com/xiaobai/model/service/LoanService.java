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
    public List<Map<String,Object>> getAll(Map<String,Object> param) {
        param.put("TABLE_NAME","t_loan");
        param.put("status",Integer.parseInt(param.get("status").toString()));
        List<Map<String, Object>> list = mapper.getAllList(param);
        return list;
    }

    public Map<String,Object> getAllMap(Map<String,Object> param) {
        param.put("TABLE_NAME","t_loan");
        param.put("COLUMNS","*");
        param.put("INNER_JOIN","t_user on t_user.id=t_loan.userid");
        param.put("t_loan.status",Integer.parseInt(param.get("status").toString()));
        param.remove("status");
        List<Map<String, Object>> list = mapper.getAllMap(param);
        Map<String,Object> result=new HashMap<>();
        result.put("data",list);
        result.put("total",mapper.getAllCount(param));
        return result;
    }
}

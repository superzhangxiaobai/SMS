package com.xiaobai.model.service;

import com.xiaobai.model.entity.Sign;
import com.xiaobai.model.mapper.SignMapper;
import com.xiaobai.sys.base.BaseMapper;
import com.xiaobai.sys.base.BaseService;
import com.xiaobai.sys.base.SysParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@Service
public class SignService extends BaseService<Sign> {
    @Autowired
    private SignMapper mapper;
    @Override
    public BaseMapper<Sign> getMapper() {
        return mapper;
    }

    public Map<String,Object> getAll(SysParam param) {
        param.setTablename("t_sign");
        Map<String,Object> result=new HashMap<>();
        List<Sign> list = mapper.getAll(param);
        result.put("data",list);
        result.put("total",mapper.getCount(param));
        //可加入分页, 总数等数据
        return result;
    }
}

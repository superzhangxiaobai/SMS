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

    public List<Map<String,Object>> getAll(Map<String,Object> param) {
        param.put("TABLE_NAME","t_sign");
        param.put("status",Integer.parseInt(param.get("status").toString()));
        List<Map<String, Object>> list = mapper.getAllList(param);
        return list;
    }

    public Map<String,Object> getAllMap(Map<String,Object> param) {
        param.put("TABLE_NAME","t_sign");
        param.put("COLUMNS","*");
        param.put("INNER_JOIN","t_user on t_user.id=t_sign.userid");
        param.put("t_sign.status",Integer.parseInt(param.get("status").toString()));
        param.remove("status");
        List<Map<String, Object>> list = mapper.getAllMap(param);
        Map<String,Object> result=new HashMap<>();
        result.put("data",list);
        result.put("total",mapper.getAllCount(param));
        return result;
    }
}

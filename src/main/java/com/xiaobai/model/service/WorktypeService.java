package com.xiaobai.model.service;

import com.xiaobai.model.entity.Worktype;
import com.xiaobai.model.mapper.WorktypeMapper;
import com.xiaobai.sys.base.BaseMapper;
import com.xiaobai.sys.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WorktypeService extends BaseService<Worktype> {
    @Autowired
    private WorktypeMapper mapper;
    @Override
    public BaseMapper<Worktype> getMapper() {
        return mapper;
    }

    public Map<String,Object> getAllMap(Map<String,Object> param) {
        param.put("TABLE_NAME","t_worktype");
        param.put("status",Integer.parseInt(param.get("status").toString()));
        List<Map<String, Object>> list = mapper.getAllMap(param);
        Map<String,Object> result=new HashMap<>();
        result.put("data",list);
        result.put("total",mapper.getAllCount(param));
        return result;
    }
    public List<Map<String,Object>> getAll(Map<String,Object> param) {
        param.put("TABLE_NAME","t_worktype");
        param.put("status",Integer.parseInt(param.get("status").toString()));
        List<Map<String, Object>> list = mapper.getAllList(param);
        return list;
    }
}

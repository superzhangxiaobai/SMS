package com.xiaobai.model.service;

import com.xiaobai.model.entity.Project;
import com.xiaobai.model.mapper.ProjectMapper;
import com.xiaobai.sys.base.BaseMapper;
import com.xiaobai.sys.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProjectService extends BaseService<Project> {
    @Autowired
    private ProjectMapper mapper;
    @Override
    public BaseMapper<Project> getMapper() {
        return mapper;
    }

    public Map<String,Object> getAllMap(Map<String,Object> param) {
        param.put("TABLE_NAME","t_project");
        param.put("LEFT_JOIN","t_worktype on t_worktype.id = t_project.worktypeid");
        param.put("t_project.status",Integer.parseInt(param.get("status").toString()));
        param.remove("status");
        List<Map<String, Object>> list = mapper.getAllMap(param);
        Map<String,Object> result=new HashMap<>();
        result.put("data",list);
        result.put("total",mapper.getAllCount(param));
        return result;
    }

    public List<Map<String,Object>> getAll(Map<String,Object> param) {
        param.put("TABLE_NAME","t_project");
        param.put("status",Integer.parseInt(param.get("status").toString()));
        List<Map<String, Object>> list = mapper.getAllList(param);
        return list;
    }
}

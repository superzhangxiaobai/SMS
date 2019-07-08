package com.xiaobai.sys.base;

import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseService<T> {
    public abstract BaseMapper<T> getMapper();
    @Transactional(readOnly = false)
    public Integer insert(final T entity) {
        return getMapper().insertSelective(entity);
    }
    @Transactional(readOnly = false)
    public Integer update(final T entity) {
        return getMapper().updateByPrimaryKeySelective(entity);
    }
    @Transactional(readOnly = false)
    public Integer delete(final T entity) {
       return getMapper().delete(entity);
    }
    @Transactional(readOnly = true)
    public List<T> selectAll() {
        return getMapper().selectAll();
    }
    @Transactional(readOnly = true)
    public T selectOne(T t) {
        return getMapper().selectOne(t);
    }

    /**
     * 通用型新增删除, 但必须要有id
     * @param entity
     * @return
     */
    @Transactional(readOnly = false)
    public Map<String,Object> addOrUpdate(final T entity){
        Map<String,Object> result=new HashMap<>();
        result.put("status",-1);
        try {
            Class clazz = entity.getClass();
            Method method = clazz.getMethod("getId");
            int line=0;
            if(method.invoke(entity)==null){
                line=insert(entity);
                result.put("status",line);
            }else {
                line=update(entity);
                result.put("status",line);
            }
            result.put("data",entity);
            result.put("msg",line>0?"操作成功":"操作失败");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return result;
    }

}

package com.xiaobai.sys.base;

import com.xiaobai.sys.base.BaseMapper;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class BaseService<T> {
    public abstract BaseMapper<T> getMapper();
    @Transactional(readOnly = false)
    public void insertSelective(final T entity) {
        getMapper().insertSelective(entity);
    }
    @Transactional(readOnly = false)
    public void update(final T entity) {
        getMapper().updateByPrimaryKeySelective(entity);
    }
    @Transactional(readOnly = false)
    public void delete(final T entity) {
        getMapper().delete(entity);
    }
    @Transactional(readOnly = true)
    public List<T> selectAll() {
        return getMapper().selectAll();
    }
}

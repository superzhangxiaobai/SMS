package com.xiaobai.sys.base;

import org.apache.ibatis.jdbc.SQL;


public class BaseDaoProvider {
    public static String find(SysParam param) {
        String tablename = param.getTablename();//表名
        String sql= new SQL() {{
            SELECT("*");
            FROM(tablename);
            if(param.getId()!=null){
                WHERE(" id = #{id, jdbcType=INTEGER}");
            }
            if(param.getPid()!=null){
                WHERE(" pid = #{pid, jdbcType=INTEGER}");
            }
            if(param.getStatus()!=null){
                WHERE(" status = #{status, jdbcType=INTEGER}");
            }
            if(param.getIsEnable()!=null){
                WHERE(" isEnable = #{isEnable, jdbcType=INTEGER}");
            }
            //ORDER_BY("id desc ");
        }}.toString();
        sql+="limit "+((param.getPageNo()-1)*param.getPageSize())+","+param.getPageSize();
        return sql;
    }
    public static String count(SysParam param) {
        String tablename = param.getTablename();//表名
        String sql= new SQL() {{
            SELECT("count(*)");
            FROM(tablename);
            if(param.getId()!=null){
                WHERE(" id = #{id, jdbcType=INTEGER}");
            }
            if(param.getPid()!=null){
                WHERE(" pid = #{pid, jdbcType=INTEGER}");
            }
            if(param.getStatus()!=null){
                WHERE(" status = #{status, jdbcType=INTEGER}");
            }
            if(param.getIsEnable()!=null){
                WHERE(" isEnable = #{isEnable, jdbcType=INTEGER}");
            }
            //ORDER_BY("id desc ");
        }}.toString();
        sql+="limit "+((param.getPageNo()-1)*param.getPageSize())+","+param.getPageSize();
        return sql;
    }
}

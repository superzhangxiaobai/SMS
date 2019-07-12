package com.xiaobai.sys.base;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;


public class BaseDaoProvider {
    /**
     * 如果多表联查, inner
     * @param param
     * @return
     */
    public static String findByMap(Map<String,Object> param){
        String tablename = param.get("TABLE_NAME").toString();//表名
        param.remove("TABLE_NAME");
        String columns =param.get("COLUMNS")==null?"*":param.get("COLUMNS").toString();//表名
        param.remove("COLUMNS");
        String sql= new SQL() {{
            SELECT(columns);
            FROM(tablename);
            for(String key : param.keySet()){
                String value = param.get(key).toString();
                if(key.equals("LEFT_JOIN")){
                    LEFT_OUTER_JOIN(value);
                }else if(key.equals("RIGHT_JOIN")){
                    RIGHT_OUTER_JOIN(value);
                }else if(key.equals("INNER_JOIN")){
                    INNER_JOIN(value);
                }else{
                    WHERE(key+" = "+value);
                }
            }
        }}.toString();
        System.out.println(sql);
        return sql;
    }
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
        sql+=" limit "+((param.getPageNo()-1)*param.getPageSize())+","+param.getPageSize();
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

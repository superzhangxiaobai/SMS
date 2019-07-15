package com.xiaobai.sys.base;

import org.apache.ibatis.jdbc.SQL;
import tk.mybatis.mapper.annotation.Order;

import java.util.Map;


public class BaseDaoProvider {
    /**
     * 如果多表联查, inner
     * @param param
     * @return
     */
    public static String findByMapToPage(Map<String,Object> param){
        Integer pageNo=param.get("pageNo")==null?1:Integer.parseInt(param.get("pageNo").toString());
        Integer pageSize=param.get("pageSize")==null?15:Integer.parseInt(param.get("pageSize").toString());
        String sql= createSql(param);
        sql+="\nLIMIT "+(pageNo-1)*pageSize+","+pageSize;
        System.out.println(sql);
        return sql;
    }
    public static String findByMap(Map<String,Object> param){
        String sql= createSql(param);
        System.out.println(sql);
        return sql;
    }
    public static String countByMap(Map<String,Object> param){
        param.put("COLUMNS","count(*)");
        String sql= createSql(param);
        System.out.println(sql);
        return sql;
    }
    private static String createSql(Map<String,Object> param){
        String tablename = param.get("TABLE_NAME").toString();//表名
        String columns =param.get("COLUMNS")==null?"*":param.get("COLUMNS").toString();//表名
        String sql= new SQL() {{
            SELECT(columns);
            FROM(tablename);
            param.forEach((key,item)->{
                String value = item.toString();
                if(key.equals("LEFT_JOIN")){
                    LEFT_OUTER_JOIN(value);
                }else if(key.equals("RIGHT_JOIN")){
                    RIGHT_OUTER_JOIN(value);
                }else if(key.equals("INNER_JOIN")){
                    INNER_JOIN(value);
                }else if(key.equals("ORDER_BY")){
                    ORDER_BY(value);
                }else if(!"TABLE_NAME,pageNo,pageSize,COLUMNS,ORDER_BY".contains(key)&&value!=""){
                    if(item instanceof Integer)//外键使用=, 后台传入
                        WHERE(key+" = "+value);
                    else if(item instanceof String)
                        WHERE(key+" like '%"+value+"%'");
                }
            });
        }}.toString();
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
        }}.toString();
        sql+="\nLIMIT "+((param.getPageNo()-1)*param.getPageSize())+","+param.getPageSize();
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
        }}.toString();
        return sql;
    }
}

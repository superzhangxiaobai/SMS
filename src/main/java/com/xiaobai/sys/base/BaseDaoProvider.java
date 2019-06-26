package com.xiaobai.sys.base;

import org.apache.ibatis.jdbc.SQL;

public class BaseDaoProvider {
    public static String find(SysParam param) {
        String tablename = param.getTablename();//表名
        return new SQL() {{
            SELECT("*");
            FROM(tablename);
            if (param.getStatus() != null) {
                WHERE(" status = #{status, jdbcType=INTEGER}");
            }
            ORDER_BY("id desc");
        }}.toString();
    }
}

package com.xiaobai.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import tk.mybatis.spring.annotation.MapperScan;

import java.io.IOException;

/*sqlserver
 * 数据库相关配置
 */
//@Configuration
//@MapperScan(basePackages = "com.xiaobai.sys.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class DataSourceConfig {
	@Bean(name = "dataSource")
	@ConfigurationProperties(prefix="spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "sqlSessionFactory")
	public SqlSessionFactory  sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource){
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		SqlSessionFactory factory=null;
		try {//不设置xml位置, 使用注解配置
//			bean.setMapperLocations(
//					new PathMatchingResourcePatternResolver().getResources("classpath:mybatis/xml/*.xml"));
			factory = bean.getObject();
		} catch (IOException e) {
			System.out.println("数据库连接失败");
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return factory;
	}

	@Bean(name = "transactionManager")
	public DataSourceTransactionManager transactionManager(@Qualifier("dataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "sqlSessionTemplate")

	public SqlSessionTemplate sqlSessionTemplate(
			@Qualifier("sqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory);
	}
}


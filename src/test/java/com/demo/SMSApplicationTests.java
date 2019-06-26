package com.demo;

import com.xiaobai.sys.entity.UserInfo;
import com.xiaobai.config.DataSourceConfig;
import com.xiaobai.sys.mapper.UserInfoMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SMSApplicationTests {
    @Autowired
    private DataSourceConfig dataSource;
    @Autowired
    private UserInfoMapper dataMapper;
	@Test
	public void contextLoads() {
        UserInfo admin = dataMapper.getUserLoginInfo("admin");
        System.out.println(admin.getUsername()+"===================");
    }

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
    }
    
    @Test
    public void testList()throws Exception{
        Connection connection = dataSource.dataSource().getConnection();
        System.out.println("==================="+connection);
        connection.close();
    }
}

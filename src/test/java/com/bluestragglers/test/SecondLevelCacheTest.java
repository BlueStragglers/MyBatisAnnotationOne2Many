package com.bluestragglers.test;

import com.bluestragglers.dao.IAccountDao;
import com.bluestragglers.dao.IUserDao;
import com.bluestragglers.domain.Account;
import com.bluestragglers.domain.User;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

public class SecondLevelCacheTest{

    private InputStream in;
    private SqlSessionFactory factory;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
    }

    @After
    public void destroy() throws Exception {
        in.close();
    }

    @Test
    public void testFindOne() {
        SqlSession session = factory.openSession();
        IUserDao userDao = session.getMapper(IUserDao.class);
        User user = userDao.findById(51);
        System.out.println("user = " + user);
//        释放缓存
        session.close();

//        再次创建缓存
        SqlSession session1 = factory.openSession();
        IUserDao userDao1 = session1.getMapper(IUserDao.class);
        User user1 = userDao1.findById(51);
        System.out.println("user1 = " + user1);
        session1.close();
    }
}

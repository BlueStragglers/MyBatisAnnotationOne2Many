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

public class AnnotationCRUDTest {
    private InputStream in;
    private SqlSessionFactory factory;
    private SqlSession session;
//    private IAccountDao accountDao;
    private IUserDao userDao;

    @Before
    public void init() throws Exception{
        in = Resources.getResourceAsStream("SqlMapConfig.xml");
        factory = new SqlSessionFactoryBuilder().build(in);
        session = factory.openSession();
        userDao = session.getMapper(IUserDao.class);
//        accountDao = session.getMapper(IAccountDao.class);
    }

    @After
    public void destroy() throws Exception {
        session.commit();
        session.close();
        in.close();
    }

//    @Test
//    public void testFindAll() {
//        List<Account> accounts = accountDao.findAll();
//        for (Account account : accounts) {
//            System.out.println("--- 账户信息 ---");
//            System.out.println("account = " + account);
//            System.out.println(account.getUser());
//        }
//    }


    @Test
    public void testFindAll() {
        List<User> users = userDao.findAll();
        for (User user : users) {
            System.out.println("--- 用户信息 ---");
            System.out.println("user = " + user);
        }
    }

}

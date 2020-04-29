package com.test.ssm.method2;

import com.test.ssm.method2.dao.MessageDao;
import com.test.ssm.method2.model.Message;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
@Slf4j
public class TestMybatisCache {
    @Autowired
    private SqlSessionFactory sqlSessionFactory;

    @Test
    public void testMybatisFirstCache() {
        // 一级缓存默认开启
        SqlSession sqlSession = sqlSessionFactory.openSession();
//        String statement = "com.test.ssm.method2.dao.MessageDao.selectByPrimaryKey";
        MessageDao messageDao = sqlSession.getMapper(MessageDao.class);
        // 第一次查询，会发出sql语句，并将查询的结果放入缓存中
        Message message = messageDao.selectByPrimaryKey(2);
        log.info("message1 = {}", message);
        // 修改数据
        messageDao.updateByPrimaryKey(2);
        sqlSession.commit();
//        int result = messageDao.updateByPrimaryKey(2);
        Message message2 = messageDao.selectByPrimaryKey(2);
        log.info("message2 = {}", message2);
        sqlSession.close();
    }

    @Test
    public void testMybatisSecondCache() {
        // 二级缓存默认不开启，需要去手动开启
        SqlSession sqlSession1 = sqlSessionFactory.openSession();
        SqlSession sqlSession2 = sqlSessionFactory.openSession();
        SqlSession sqlSession3 = sqlSessionFactory.openSession();
//        String statement = "com.test.ssm.method2.dao.MessageDao.selectByPrimaryKey";
        MessageDao messageDao1 = sqlSession1.getMapper(MessageDao.class);
        MessageDao messageDao2 = sqlSession2.getMapper(MessageDao.class);
        MessageDao messageDao3 = sqlSession3.getMapper(MessageDao.class);

        // 第一次查询，会发出sql语句，并将查询的结果放入缓存中
        Message message = messageDao1.selectByPrimaryKey(2);
        log.info("message1 = {}", message);
        sqlSession1.close();

        // 修改数据，如果这里的 flushCache 属性值为 false ，那么会造成脏读，默认这个属性是为true的
        // 一般下执行完commit操作都需要刷新缓存，flushCache=true表示刷新缓存，这样可以避免数据库脏读。所以我们不用设置，默认即可
        messageDao3.updateByPrimaryKey(2);
        sqlSession3.commit();

        // 第二次查询，即使sqlSession1已经关闭了，这次查询依然不发出sql语句
        Message message2 = messageDao2.selectByPrimaryKey(2);
        log.info("message2 = {}", message2);
        sqlSession2.close();
    }
}

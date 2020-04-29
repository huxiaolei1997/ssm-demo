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
        sqlSession.close();
        // sqlSession 关闭了，那么一级缓存自然也就失效了，但是如果开启了二级缓存，如果是调用了同一个mapper里面的select方法，如果二级缓存
        // 里面有数据，还是会从二级缓存里面取数据
        // 二级缓存的原理和一级缓存原理一样，第一次查询，会将数据放入缓存中，然后第二次查询则会直接去缓存中取。
        // 但是一级缓存是基于 sqlSession 的，而 二级缓存是基于 mapper文件的namespace的，
        // 也就是说多个sqlSession可以共享一个mapper中的二级缓存区域，并且如果两个mapper的namespace相同，
        // 即使是两个mapper，那么这两个mapper中执行sql查询到的数据也将存在相同的二级缓存区域中
        sqlSession = sqlSessionFactory.openSession();
        messageDao = sqlSession.getMapper(MessageDao.class);
        // 修改数据
//        messageDao.updateByPrimaryKey(2);
//        sqlSession.commit();
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

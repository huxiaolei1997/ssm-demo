package com.test.ssm.method2.serviceImpl;

import com.test.ssm.method2.dao.MessageDao;
import com.test.ssm.method2.model.Message;
import com.test.ssm.method2.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 用途描述
 *
 * @author 胡晓磊
 * @version $Id: DemoServiceImpl, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2018年11月27日 14:13 胡晓磊 Exp $
 */
@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private MessageDao messageDao;

    @Override
    public String hello(String name) {
        System.out.println("hello, " + name);
        Message message = messageDao.selectByPrimaryKey(9);
        return "hello, " + name + ", content" + message.getContent();
    }

    @Override
    public Message selectByPrimaryKey(Integer id) {
        return messageDao.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKey(Integer id) {
        return messageDao.updateByPrimaryKey(id);
    }
}

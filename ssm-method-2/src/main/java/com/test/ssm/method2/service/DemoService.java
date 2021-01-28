package com.test.ssm.method2.service;

import com.test.ssm.method2.model.Message;

/**
 * 用途描述
 *
 * @author 胡晓磊
 * @version $Id: DemoService, v0.1
 * @company 杭州网络科技有限公司
 * @date 2018年11月27日 14:13 胡晓磊 Exp $
 */
public interface DemoService {
    String hello(String name);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Integer id);
}

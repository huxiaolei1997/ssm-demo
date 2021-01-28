package com.test.ssm.method1.dao;

import com.test.ssm.method1.model.Message;

/**
 * 用途描述
 *
 * @author 胡晓磊
 * @version $Id: MessageDao, v0.1
 * @company 杭州网络科技有限公司
 * @date 2018年11月27日 16:08 胡晓磊 Exp $
 */
public interface MessageDao {
    Message selectByPrimaryKey(Integer id);
}

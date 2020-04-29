package com.test.ssm.method2.dao;

import com.test.ssm.method2.model.Message;
import org.apache.ibatis.plugin.Intercepts;

import java.util.List;

/**
 * 用途描述
 *
 * @author 胡晓磊
 * @version $Id: MessageDao, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2018年11月27日 16:08 胡晓磊 Exp $
 */
public interface MessageDao {
    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(Integer id);

    List<Message> selectByFromUserId(Integer fromUserId);
}

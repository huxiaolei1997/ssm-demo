package com.test.ssm.method2.model;

import java.sql.Timestamp;

/**
 * 用途描述
 *
 * @author 胡晓磊
 * @version $Id: Message, v0.1
 * @company 杭州信牛网络科技有限公司
 * @date 2018年11月27日 16:08 胡晓磊 Exp $
 */
public class Message {
    // 消息id
    private long id;

    // 发信者id
    private int fromUserId;

    // 收信者id
    private int toUserId;

    // 消息主体内容
    private String content;

    // 消息类型 （0 表示普通消息，1 表示验证消息）
    private int messageType;

    // 发送时间
    private Timestamp sendTime;

    /**
     * Getter method for property <tt>id</tt>.
     *
     * @return property value of id
     */
    public long getId() {
        return id;
    }

    /**
     * Setter method for property <tt>id</tt>.
     *
     * @param id value to be assigned to property id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter method for property <tt>fromUserId</tt>.
     *
     * @return property value of fromUserId
     */
    public int getFromUserId() {
        return fromUserId;
    }

    /**
     * Setter method for property <tt>fromUserId</tt>.
     *
     * @param fromUserId value to be assigned to property fromUserId
     */
    public void setFromUserId(int fromUserId) {
        this.fromUserId = fromUserId;
    }

    /**
     * Getter method for property <tt>toUserId</tt>.
     *
     * @return property value of toUserId
     */
    public int getToUserId() {
        return toUserId;
    }

    /**
     * Setter method for property <tt>toUserId</tt>.
     *
     * @param toUserId value to be assigned to property toUserId
     */
    public void setToUserId(int toUserId) {
        this.toUserId = toUserId;
    }

    /**
     * Getter method for property <tt>content</tt>.
     *
     * @return property value of content
     */
    public String getContent() {
        return content;
    }

    /**
     * Setter method for property <tt>content</tt>.
     *
     * @param content value to be assigned to property content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Getter method for property <tt>messageType</tt>.
     *
     * @return property value of messageType
     */
    public int getMessageType() {
        return messageType;
    }

    /**
     * Setter method for property <tt>messageType</tt>.
     *
     * @param messageType value to be assigned to property messageType
     */
    public void setMessageType(int messageType) {
        this.messageType = messageType;
    }

    /**
     * Getter method for property <tt>sendTime</tt>.
     *
     * @return property value of sendTime
     */
    public Timestamp getSendTime() {
        return sendTime;
    }

    /**
     * Setter method for property <tt>sendTime</tt>.
     *
     * @param sendTime value to be assigned to property sendTime
     */
    public void setSendTime(Timestamp sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return "{" +
                "\"id\":" + id +
                ", \"from_user_id\":" + fromUserId +
                ", \"to_user_id\":" + toUserId +
                ", \"content\":\"" + content + "\"" +
                ", \"message_type\":" + messageType +
                ", \"send_time\":" + sendTime.getTime() +
                '}';
    }
}

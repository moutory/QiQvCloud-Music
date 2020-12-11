package com.qiqv.music.service;

import com.qiqv.music.pojo.Consumer;

import java.security.NoSuchAlgorithmException;
import java.util.List;

public interface ConsumerService {
    /**
     *  添加用户
     */
    boolean addConsumer(Consumer consumer) throws NoSuchAlgorithmException;

    /**
     * 更新用户信息
     * @param consumer
     * @return
     */
    boolean updateConsumer(Consumer consumer);

    /**
     * 删除用户
     * @param consumerId
     * @return
     */
    boolean deleteConsumerById(Integer consumerId);

    /**
     * 根据id主键查询用户
     * @param id
     * @return
     */
    Consumer queryConsumerById(Integer id);

    /**
     * 查询所有用户
     * @return
     */
    List<Consumer> getAllConsumer();

    /**
     * 根据用户名称模糊查询用户信息
     * @param consumerName
     * @return
     */
    List<Consumer> queryConsumerByName(String consumerName);

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    Consumer consumerLogin(String username,String password);


}

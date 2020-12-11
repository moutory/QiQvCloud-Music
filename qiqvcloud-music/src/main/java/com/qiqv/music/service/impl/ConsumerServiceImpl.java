package com.qiqv.music.service.impl;

import com.qiqv.music.dao.ConsumerMapper;
import com.qiqv.music.pojo.Consumer;
import com.qiqv.music.pojo.ConsumerExample;
import com.qiqv.music.service.ConsumerService;
import com.qiqv.music.utils.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private ConsumerMapper consumerMapper;

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean addConsumer(Consumer consumer) throws NoSuchAlgorithmException {
        consumer.setPassword(MD5Utils.getEnCryptionStrByMD5(consumer.getPassword()));
        return consumerMapper.insertSelective(consumer)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean updateConsumer(Consumer consumer) {
        return consumerMapper.updateByPrimaryKeySelective(consumer)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    @Override
    public boolean deleteConsumerById(Integer consumerId) {
        return consumerMapper.deleteByPrimaryKey(consumerId)>0;
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Consumer queryConsumerById(Integer id) {
        return consumerMapper.selectByPrimaryKey(id);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Consumer> getAllConsumer() {
        ConsumerExample consumerExample = new ConsumerExample();
        return consumerMapper.selectByExample(consumerExample);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public List<Consumer> queryConsumerByName(String consumerName) {
        ConsumerExample consumerExample = new ConsumerExample();
        ConsumerExample.Criteria criteria = consumerExample.createCriteria();
        criteria.andUsernameLike("%"+consumerName+"%");
        return consumerMapper.selectByExample(consumerExample);
    }

    /**
     * 用户登陆校验
     * @param username
     * @param password
     * @return
     */
    @Transactional(propagation = Propagation.SUPPORTS)
    @Override
    public Consumer consumerLogin(String username, String password) {
        ConsumerExample consumerExample = new ConsumerExample();
        ConsumerExample.Criteria criteria = consumerExample.createCriteria();
        criteria.andUsernameEqualTo(username);
        criteria.andPasswordEqualTo(password);
        List<Consumer> consumers = consumerMapper.selectByExample(consumerExample);
        if(consumers!=null && !consumers.isEmpty()){
            return consumers.get(0);
        }
        return null;
    }
}

package com.qiqv.music.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * 使用redisTemplate封装的工具类
 */
@Component
public class RedisOperator {

    @Autowired
    private RedisTemplate redisTemplate;

    // ==============   key-value  ====================================
    /**
     *  设置一对key-value
     * @param key
     * @param value
     */
    public void setValue(String key, String value){
        this.redisTemplate.opsForValue().set(key,value);
    }

    /**
     * 设置一对有效期为timeout 的 key-value
     *  过期时间单位为秒
     * @param key
     * @param value
     * @param timeout
     */
    public void setValue(String key, String value, long timeout){
        this.redisTemplate.opsForValue().set(key,value,timeout, TimeUnit.SECONDS);
    }

    /**
     * 获取某个key的value
     * @param key
     * @return
     */
    public String getValue(String key){
        return (String) this.redisTemplate.opsForValue().get(key);
    }

    /**
     * 删除某个key
     * @param key
     */
    public void delKey(String key){
        this.redisTemplate.delete(key);
    }

    /**
     * 设置某个Key的过期时间，单位为秒(SECOND)
     *  如果key不存在，则新建
     * @param key
     * @param timeout
     */
    public void expire(String key, long timeout){
        this.redisTemplate.expire(key,timeout, TimeUnit.SECONDS);
    }

    /**
     * 获得满足格式的所有key
     * @param pattern
     * @return
     */
    public Set<String> getKeys(String pattern){
        return this.redisTemplate.keys(pattern);
    }

    /**
     * 对某个key增加一次： incr key
     * @param key
     */
    public long incrKey(String key, long stepLength){
        return this.redisTemplate.opsForValue().increment(key,stepLength);
    }


    // ===============   hash  ===============================
    /**
     * 刪除数据格式为hash一个或者多个value
     * @param key
     * @param fields
     */
    public void hdel(String key, Object... fields){
        this.redisTemplate.opsForHash().delete(key,fields);
    }

    /**
     *  获取hash中指定key对应一对key-value
     * @param key
     * @param field
     * @return
     */
    public String hget(String key, String field){
        return (String)this.redisTemplate.opsForHash().get(key,field);
    }

    /**
     * 获取hash中指定key的所有key-value键值对
     * @param key
     * @return
     */
    public Map<Object, Object> hgetAll(String key){
        return this.redisTemplate.opsForHash().entries(key);
    }

    /**
     * 设置hash字典表中的一对key-value
     * @param key
     * @param field
     * @param value
     */
    public void hset(String key, String field, Object value){
        this.redisTemplate.opsForHash().put(key,field,value);
    }

    // =================  List   ==========================

    /**
     * 将一个value push 到List表的表头
     * @param key
     * @param value
     */
    public void lpush(String key, String value){
        this.redisTemplate.opsForList().leftPush(key,value);
    }

    /**
     * 将List表表头的元素pop出来
     * @param key
     */
    public String lpop(String key){
        return (String) this.redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 将一个value push 到List的表尾
     * @param key
     * @param value
     */
    public void rpush(String key, String value){
        this.redisTemplate.opsForList().rightPush(key,value);
    }

    /**
     * 将List表表尾的数据pop出来
     * @param key
     */
    public String rpop(String key){
        return (String)this.redisTemplate.opsForList().rightPop(key);
    }


}

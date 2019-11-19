package com.hawkeye.util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
@SuppressWarnings("all")
public class RedisUtil {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 将字符串值 value 关联到 key 。
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * 获取所有keys
     */
    public  Set<String> getKyes(){
        Set<String> set = stringRedisTemplate.keys("*");
        return set;
    }

    /**
     * 返回 key 所关联的字符串值。
     *
     * @param key 键
     * @return 值 string
     */
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    /**
     * 获取键过期时间
     *
     * @param key      键
     * @param timeUnit 时间单位
     * @return the long
     */
    public long ttl(String key, TimeUnit timeUnit) {
        return stringRedisTemplate.getExpire(key, timeUnit);
    }

    /**
     * 设置键过期时间
     *
     * @param key      键
     * @param timeout  过期时间
     * @param timeUnit 时间单位
     * @return boolean boolean
     */
    public boolean expire(String key, long timeout, TimeUnit timeUnit) {
        return stringRedisTemplate.expire(key, timeout, timeUnit);
    }

    /**
     * 获取旧值并且更新值
     *
     * @param key   键
     * @param value 新值
     * @return 旧值
     */
    public String getSet(String key, String value) {
        return stringRedisTemplate.opsForValue().getAndSet(key, value);
    }

    /**
     * 删除
     *
     * @param key 键
     * @return the boolean
     */
    public boolean del(String key) {
        return stringRedisTemplate.delete(key);
    }

    /**
     * Hset  将哈希表 key 中的域 field 的值设为 value 。
     *
     * @param key   键
     * @param field 域
     * @param value 值
     */
    public void hset(String key, String field, Object value) {
        stringRedisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * Hget 返回哈希表 key 中给定域 field 的值。
     *
     * @param key   键
     * @param field 域
     * @return 值
     */
    public String hget(String key, String field) {
        return (String) stringRedisTemplate.opsForHash().get(key, field);
    }

    /**
     * 获取hashkey对应的所有键值
     * @param key 键
     * @return  对应的多个值
     */
    public Map<Object, Object> hmget(String key){
        return stringRedisTemplate.opsForHash().entries(key);
    }


    /**
     * 判断键是否存在
     * @param key
     * @return
     */
    public Boolean hasKey(String key){
        return stringRedisTemplate.hasKey(key);
    }

    /**
     *
     * @param key
     */
    public Set keys(String key){
        Set set = stringRedisTemplate.keys(key+"*");
        return set;
    }
//    /**
//     *  在redis中以list方式存储   以集合方式向右边添加元素。
//     * @param key 键
//     * @param list 集合
//     */
//    public  void lrightPushAll(String key, List list){
//        stringRedisTemplate.opsForList().rightPushAll(key,list);
//    }
//
//    /**
//     * 查询list中指定范围的内容
//     * @return
//     */
//    public List<String> getList(String key){
//        return stringRedisTemplate.opsForList().range(key,0,stringRedisTemplate.opsForList().size(key)-1);
//    }



}

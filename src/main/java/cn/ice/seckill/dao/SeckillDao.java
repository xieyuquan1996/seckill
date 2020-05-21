package cn.ice.seckill.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class SeckillDao {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


}

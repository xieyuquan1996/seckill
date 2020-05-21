package cn.ice.seckill.dao;

import cn.ice.seckill.entities.SeckillInfo;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

@Component
public class GoodDao {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 判断用户是否参与了某个商品的秒杀
     * @param seckillInfo
     * @return
     */
    public boolean isJoinSeckill(@NotNull SeckillInfo seckillInfo){
        if (StringUtils.isEmpty(querySeckill(seckillInfo))){
            return false;
        }
        return true;
    }

    public String querySeckill(@NotNull SeckillInfo seckillInfo){
        return stringRedisTemplate.opsForValue().get("seckill:" + seckillInfo.getGoodsId() + ":" + seckillInfo.getUserId());
    }

    /**
     * 判断商品是否是秒杀商品
     * @param goodId
     * @return
     */
    public boolean seckillGoodState(String goodId){
        if (StringUtils.isEmpty(stringRedisTemplate.opsForHash().hasKey("seckillGoods", goodId))){
            return false;
        }
        return true;
    }

    /**
     * 参与秒杀
     * @param goodId
     * @param userId
     * @return
     */
    public Boolean joinSeckill(String goodId, String userId){
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>();
        // 指定 lua 脚本
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("/static/joinSeckill.lua")));
        // 指定返回类型
        redisScript.setResultType(Boolean.class);
        List<String> keyList = new ArrayList<>();
        keyList.add(userId);
        keyList.add(goodId);
        // 参数一：redisScript，参数二：key列表，参数三：arg（可多个）
        return stringRedisTemplate.execute(redisScript, keyList);
    }

    public Boolean decrease(String goodsId, String userId){
        DefaultRedisScript<Boolean> redisScript = new DefaultRedisScript<>();
        ClassPathResource classPathResource = new ClassPathResource("/static/seckill.lua");
        // 指定 lua 脚本
        redisScript.setScriptSource(new ResourceScriptSource(classPathResource));
        // 指定返回类型
        redisScript.setResultType(Boolean.class);
        List<String> keyList = new ArrayList<>();
        keyList.add(userId);
        keyList.add(goodsId);
        // 参数一：redisScript，参数二：key列表，参数三：arg（可多个）
        Boolean result = stringRedisTemplate.execute(redisScript, keyList);
        if (result){
            logger.info("秒杀成功:userId:"+userId +";goodsId:" + goodsId);
        }else {
            logger.info("秒杀失败:userId:"+userId +";goodsId:" + goodsId);
        }
        return result;
    }

}

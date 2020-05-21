package cn.ice.seckill.action;

import cn.ice.seckill.dao.GoodDao;
import cn.ice.seckill.entities.SeckillInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GoodAction {
    @Autowired
    private GoodDao goodDao;

    /**
     * 判断商品是否是秒杀商品
     * @param goodId
     * @return
     */
    public boolean seckillGoodState(String goodId){
        return goodDao.seckillGoodState(goodId);
    }
    public Boolean seckillGoodState(SeckillInfo seckillInfo){
        return seckillGoodState(seckillInfo.getGoodsId());
    }

    /**
     * 参与秒杀
     * @param goodId
     * @param userId
     * @return
     */
    public Boolean joinSeckill(String goodId, String userId){
        return goodDao.joinSeckill(goodId, userId);
    }
    public Boolean joinSeckill(SeckillInfo seckillInfo){
        return joinSeckill(seckillInfo.getGoodsId(), seckillInfo.getUserId());
    }

    /**
     * 判断该用户是否已经参与了秒杀
     * @param goodId
     * @param userId
     * @return
     */
    public boolean joinSeckillState(String goodId, String userId){
        return  joinSeckillState(new SeckillInfo(goodId, userId));
    }
    public Boolean joinSeckillState(SeckillInfo seckillInfo){
        return goodDao.isJoinSeckill(seckillInfo);
    }
}

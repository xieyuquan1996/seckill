package cn.ice.seckill.service.responsibilitychain;

import cn.ice.seckill.action.GoodAction;
import cn.ice.seckill.entities.SeckillInfo;
import cn.ice.seckill.entities.SeckillResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SeckillGoodStateHandler extends AbstractSeckillHandler {

    @Autowired
    private GoodAction goodAction;

    /**
     * 判断该商品是不是秒杀的商品
     * @param seckillInfo
     * @param result
     */
    @Override
    public void handler(SeckillInfo seckillInfo, SeckillResult result) {
        if (!goodAction.seckillGoodState(seckillInfo)){
            result.setCode(2);
            result.setMessage("该商品不是秒杀商品");
        }
    }
}

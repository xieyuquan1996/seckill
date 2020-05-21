package cn.ice.seckill.service.responsibilitychain;

import cn.ice.seckill.action.GoodAction;
import cn.ice.seckill.entities.SeckillInfo;
import cn.ice.seckill.entities.SeckillResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class InventoryHandler extends AbstractSeckillHandler {

    @Autowired
    private GoodAction goodAction;

    /**
     * 检验库存
     * @param seckillInfo
     * @param result
     */
    @Override
    public void handler(SeckillInfo seckillInfo, SeckillResult result) {
        if (!goodAction.joinSeckill(seckillInfo)){
            result.setCode(3);
            result.setMessage("秒杀活动已结束");
        }
    }
}

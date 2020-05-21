package cn.ice.seckill.service.responsibilitychain;

import cn.ice.seckill.action.GoodAction;
import cn.ice.seckill.entities.SeckillInfo;
import cn.ice.seckill.entities.SeckillResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class JoinSeckillHandler extends AbstractSeckillHandler {

    @Autowired
    private GoodAction goodAction;

    /**
     * 判断用户是否参加了秒杀
     * @param seckillInfo
     * @param result
     */
    @Override
    public void handler(SeckillInfo seckillInfo, SeckillResult result) {
        if (goodAction.joinSeckillState(seckillInfo)){
            result.setCode(1);
            result.setMessage("您已经参与了秒杀");
        }
    }
}

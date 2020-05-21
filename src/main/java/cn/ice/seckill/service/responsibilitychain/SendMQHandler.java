package cn.ice.seckill.service.responsibilitychain;

import cn.ice.seckill.action.MQAction;
import cn.ice.seckill.entities.SeckillInfo;
import cn.ice.seckill.entities.SeckillResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SendMQHandler extends AbstractSeckillHandler {

    @Autowired
    private MQAction mqAction;

    /**
     * 发送到消息队列
     * @param seckillInfo
     * @param result
     */
    @Override
    public void handler(SeckillInfo seckillInfo, SeckillResult result) {
        mqAction.sendMQ(seckillInfo);
        result.setCode(4);
        result.setMessage("您已经成功秒杀");
    }
}

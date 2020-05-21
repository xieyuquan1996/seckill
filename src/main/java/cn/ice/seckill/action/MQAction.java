package cn.ice.seckill.action;

import cn.ice.seckill.entities.SeckillInfo;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MQAction {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void sendMQ(String goodId, String userId){
        SeckillInfo seckillInfo = new SeckillInfo(goodId, userId);
        amqpTemplate.convertAndSend("seckill", seckillInfo);
    }

    public void sendMQ(SeckillInfo seckillInfo){
        sendMQ(seckillInfo.getGoodsId(), seckillInfo.getUserId());
    }
}

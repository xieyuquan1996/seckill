package cn.ice.seckill.receivemq;


import cn.ice.seckill.dao.GoodDao;
import cn.ice.seckill.entities.SeckillInfo;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
@RabbitListener(queues = "seckill")
public class SeckillMessageReceive {
    @Autowired
    private GoodDao goodDao;

    @RabbitHandler
    public void process(SeckillInfo seckillInfo) {
        goodDao.decrease(seckillInfo.getGoodsId(), seckillInfo.getUserId());
    }

}


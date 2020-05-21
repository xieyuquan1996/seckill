package cn.ice.seckill.service;

import cn.ice.seckill.dao.GoodDao;
import cn.ice.seckill.entities.SeckillInfo;
import cn.ice.seckill.entities.SeckillResult;
import cn.ice.seckill.service.responsibilitychain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeckillService {
    @Autowired
    private JoinSeckillHandler joinSeckillHandler;

    @Autowired
    private SeckillGoodStateHandler seckillGoodStateHandler;

    @Autowired
    private InventoryHandler inventoryHandler;

    @Autowired
    private SendMQHandler sendMQHandler;

    @Autowired
    private GoodDao goodDao;
    /**
     * 参与秒杀
     * @param goodId
     * @param userId
     * @return
     */
    public SeckillResult joinSeckill(String goodId, String userId){
        // 用来存责任链中的结果
        SeckillResult result = new SeckillResult();
        // 责任链模式
        SeckillHandlerBuilder builder = new SeckillHandlerBuilder();
        builder.addHandler(seckillGoodStateHandler)
                .addHandler(joinSeckillHandler)
                .addHandler(inventoryHandler)
                .addHandler(sendMQHandler);
        builder.build().doHandler(new SeckillInfo(goodId, userId), result);
        return result;
    }

    public SeckillResult querySeckill(String goodId, String userId){
        switch (goodDao.querySeckill(new SeckillInfo(goodId, userId))){
            case "0":
                return new SeckillResult(0, "排队中");
            case "1":
                return new SeckillResult(1, "秒杀成功");
            default:
                return new SeckillResult(2, "秒杀失败");
        }
    }
}

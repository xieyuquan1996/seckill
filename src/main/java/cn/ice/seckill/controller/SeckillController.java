package cn.ice.seckill.controller;

import cn.ice.seckill.entities.SeckillResult;
import cn.ice.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SeckillController {
    @Autowired
    private SeckillService seckillService;

    /**
     * 参与秒杀
     * @param goodId
     * @param userId
     * @return
     */
    @GetMapping("/seckill/{goodId}")
    public SeckillResult seckill(@PathVariable("goodId") String goodId, @RequestParam("userId") String userId){
        return seckillService.joinSeckill(goodId, userId);
    }
    @GetMapping("/seckill/query/{goodId}")
    public SeckillResult querySeckill(@PathVariable("goodId") String goodId, @RequestParam("userId") String userId){
        return seckillService.querySeckill(goodId, userId);
    }
}

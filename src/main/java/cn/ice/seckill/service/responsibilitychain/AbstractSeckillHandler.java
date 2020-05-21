package cn.ice.seckill.service.responsibilitychain;

import cn.ice.seckill.entities.SeckillInfo;
import cn.ice.seckill.entities.SeckillResult;
import org.springframework.util.ObjectUtils;

public abstract class AbstractSeckillHandler{
    protected AbstractSeckillHandler next;

    /**
     * 设置下一个处理的节点
     * @param next
     */
    public final void setNext(AbstractSeckillHandler next) {
        this.next = next;
    }

    /**
     * 验证是否进入下一个节点
     * @param result
     * @return
     */
    protected Boolean valid(SeckillResult result) {
        return ObjectUtils.isEmpty(result.getMessage());
    }

    /**
     * 节点的处理逻辑
     * @param seckillInfo
     * @param result
     */
    protected abstract void handler(SeckillInfo seckillInfo, SeckillResult result);

    /**
     * 处理每个handler
     * @param seckillInfo
     * @param result
     */
    public final void doHandler(SeckillInfo seckillInfo, SeckillResult result){
        handler(seckillInfo, result);
        if (valid(result)){
            doNext(seckillInfo, result);
        }
    }

    /**
     * 走下一个节点的handler
     * @param seckillInfo
     * @param result
     */
    private void doNext(SeckillInfo seckillInfo, SeckillResult result){
        if (ObjectUtils.isEmpty(this.next)){
            return;
        }
        this.next.doHandler(seckillInfo, result);
    }
}

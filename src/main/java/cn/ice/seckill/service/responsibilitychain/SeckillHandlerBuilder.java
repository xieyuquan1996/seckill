package cn.ice.seckill.service.responsibilitychain;

public class SeckillHandlerBuilder {
    private AbstractSeckillHandler head;
    private AbstractSeckillHandler tail;

    public SeckillHandlerBuilder addHandler(AbstractSeckillHandler abstractSeckillHandler){
        if (this.head == null){
            this.head = this.tail = abstractSeckillHandler;
            return this;
        }
        this.tail.setNext(abstractSeckillHandler);
        this.tail = abstractSeckillHandler;
        return this;
    }

    public AbstractSeckillHandler build(){
        return this.head;
    }
}

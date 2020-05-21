package cn.ice.seckill.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig  {
    @Bean
    public Exchange bootExchange(){
        return new TopicExchange("BOOT-EXCHANGE-1", true, false);
    }

    @Bean
    public Queue seckillQueue() {
        return new Queue("seckill", true);
    }

}

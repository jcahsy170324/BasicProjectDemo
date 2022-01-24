package com.lg.bsp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName RabbitmqConfig
 * @Description Rabbitmq的配置类
 * @Author jincheng
 * @Date 2022/1/21 14:13
 * @Version 1.0
 **/
@Configuration
public class RabbitmqConfig {

    @Bean
    protected Queue queue(){
        Queue queue = new Queue("myQueue");
        return queue;
    }

    @Bean
    public Queue createQueue1(){
        return new Queue("myfanout1");
    }

    @Bean
    public Queue createQueue2(){
        return new Queue("myfanout2");
    }

    @Bean
    public FanoutExchange getFanoutExchange() {
        return new FanoutExchange("amq.fanout");
    }

    @Bean
    public Binding createBinding(Queue createQueue1,FanoutExchange getFanoutExchange) {
        return BindingBuilder.bind(createQueue1).to(getFanoutExchange);
    }

    @Bean
    public Binding createBinding2(Queue createQueue2,FanoutExchange getFanoutExchange){
        return BindingBuilder.bind(createQueue2).to(getFanoutExchange);
    }
}

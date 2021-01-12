package com.eric.rabbitmq.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author by wangjunf
 * @Description direct queue
 * @Date 2021/1/7 23:29
 */
@Configuration
public class RabbitmqConfig {

    /**
     * 创建direct queue并命名
     *
     * @return
     */
    @Bean
    public Queue directQueue() {

        /**
         * durable:是否持久化,默认是false,持久化队列：会被存储在磁盘上，当消息代理重启时仍然存在，暂存队列：当前连接有效
         * exclusive:默认也是false，只能被当前创建的连接使用，而且当连接关闭后队列即被删除。此参考优先级高于durable
         * autoDelete:是否自动删除，当没有生产者或者消费者使用此队列，该队列会自动删除。
         * public Queue(String name, boolean durable, boolean exclusive, boolean autoDelete)
         *
         * 一般设置一下队列的持久化就好,其余两个就是默认false
         */
        return new Queue("direct.queue", true);
    }

    /**
     * direct exchange
     *
     * @return
     */
    @Bean
    DirectExchange directExchange() {
        return new DirectExchange("direct.exchange", true, false);
    }

    /**
     * 将队列绑定到交换机上，并设置用于匹配的键：direct_routing
     *
     * @return
     */
    @Bean
    Binding binding() {
        return BindingBuilder.bind(directQueue()).to(directExchange()).with("direct.routing");
    }

}

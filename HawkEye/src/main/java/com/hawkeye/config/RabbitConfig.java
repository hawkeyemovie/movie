package com.hawkeye.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//错误的 import com.rabbitmq.client.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class RabbitConfig {

    @Value("${spring.rabbitmq.host}")
    private String host;

    @Value("${spring.rabbitmq.port}")
    private int port;

    @Value("${spring.rabbitmq.username}")
    private String username;

    @Value("${spring.rabbitmq.password}")
    private String password;

    /**
     *消息交换器
     */
    public static final String EXCHANGE_A = "my-mq-exchange_A";

    /**
     * 队列A
     */
    public static final String QUEUE_A = "QUEUE_A";

    /**
     * 交换器B
     */
//    public static final String EXCHANGE_B = "my-mq-exchange_B";


    /**
     * 队列B
     */
    public static final String QUEUE_B = "QUEUE_B";
    public static final String QUEUE_C = "QUEUE_C";

    /**
     * 路由键A
     */
    public static final String ROUTINGKEY_A = "spring-boot-routingKey_A";

    /**
     * 路由键B
     */
    public static final String ROUTINGKEY_B = "spring-boot-routingKey_B";
    public static final String ROUTINGKEY_C = "spring-boot-routingKey_C";


    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(host, port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost("/");
        connectionFactory.setPublisherConfirms(true);


        return connectionFactory;
    }

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    // 必须是prototype类型
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate template = new RabbitTemplate(connectionFactory());
        return template;
    }

    /**
     * * 针对消费者配置 *
     * 1. 设置交换机类型 *
     * 2. 将队列绑定到交换机
     *
     * FanoutExchange:
     * 将消息分发到所有的绑定队列，无routingkey的概念
     *
     * HeadersExchange ：通过添加属性key-value匹配
     * DirectExchange:按照routingkey分发到指定队列
     * TopicExchange:多关键字匹配
     *
     * 如果需要使用的其他的交换器类型，spring中都已提供实现，所有的交换器均实现org.springframework.amqp.core.AbstractExchange接口。
     常用交换器类型如下：

     Direct(DirectExchange)：direct 类型的行为是"先匹配, 再投送".
     即在绑定时设定一个 routing_key, 消息的routing_key完全匹配时, 才会被交换器投送到绑定的队列中去。

     Topic(TopicExchange)：按规则转发消息（最灵活）。

     Headers(HeadersExchange)：设置header attribute参数类型的交换机。

     Fanout(FanoutExchange)：转发消息到所有绑定队列。

     *
     */
    @Bean
    public DirectExchange ExchangeA() {
        return new DirectExchange(EXCHANGE_A);
    }
    @Bean
    public DirectExchange ExchangeB() {
        return new DirectExchange(EXCHANGE_A);
    }



    /**
     * 获取队列A
     *
     * @return
     */
    @Bean
    public Queue queueA() {
        return new Queue(QUEUE_A, true); // 队列持久
    }

    /**
     * 获取队列B
     *
     * @return
     */
    @Bean
    public Queue queueB() {
        return new Queue(QUEUE_B, true); // 队列持久
    }/**

     /*
     *获取队列C
     * @return
     */
    @Bean
    public Queue queueC() {
        return new Queue(QUEUE_C, true); // 队列持久
    }



    /**
     * 一个交换机可以绑定多个消息队列，也就是消息通过一个交换机，可以分发到不同的队列当中去。
     *
     * @return
     */
    @Bean
    public Binding bindingA() {
        return BindingBuilder.bind(queueA()).to(ExchangeA()).with(RabbitConfig.ROUTINGKEY_A);
    }

    @Bean
    public Binding bindingB() {
        return BindingBuilder.bind(queueB()).to(ExchangeA()).with(RabbitConfig.ROUTINGKEY_B);
    }

    @Bean
    public Binding bindingC() { return BindingBuilder.bind(queueC()).to(ExchangeA()).with(RabbitConfig.ROUTINGKEY_C); }

}

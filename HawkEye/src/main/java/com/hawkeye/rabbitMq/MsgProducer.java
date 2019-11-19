package com.hawkeye.rabbitMq;

import java.util.UUID;

import com.hawkeye.config.RabbitConfig;
import org.springframework.amqp.core.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.rabbit.core.RabbitTemplate.ReturnCallback;

@Component
public class MsgProducer implements RabbitTemplate.ConfirmCallback , ReturnCallback{

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // 由于rabbitTemplate的scope属性设置为ConfigurableBeanFactory.SCOPE_PROTOTYPE，所以不能自动注入
    private RabbitTemplate rabbitTemplate;

    /** * 构造方法注入rabbitTemplate */
    @Autowired
    public MsgProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
        // rabbitTemplate如果为单例的话，那回调就是最后设置的内容

        /**
         * ConfirmCallback接口用于实现消息发送到RabbitMQ交换器后接收ack回调。
         * ReturnCallback接口用于实现消息发送到RabbitMQ交换器，但无相应队列与交换器绑定时的回调。
         */


        rabbitTemplate.setReturnCallback(this);
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        System.out.println(message.getMessageProperties().getAppId() + " 发送失败");

        System.out.println("消息主体 message : "+message);
        System.out.println("消息主体 message : "+replyCode);
        System.out.println("描述："+replyText);
        System.out.println("消息使用的交换器 exchange : "+exchange);
        System.out.println("消息使用的路由键 routing : "+routingKey);


    }

    /**
     * rabbitTemplate.send(message);   //发消息，参数类型为org.springframework.amqp.core.Message
     rabbitTemplate.convertAndSend(object); //转换并发送消息。 将参数对象转换为org.springframework.amqp.core.Message后发送
     rabbitTemplate.convertSendAndReceive(message) //转换并发送消息,且等待消息者返回响应消息。

     * @param content
     */

    public void sendMsgA(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());

        System.out.println("开始发送消息c: " + content.toLowerCase() + " ,correlationId= " + correlationId);
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A, RabbitConfig.ROUTINGKEY_A, content, correlationId);
        System.out.println("结束发送消息 : " + content.toLowerCase());
        System.out.println("消费者响应A:   消息处理完成");

        //logger.info(" 发送消息TO A:" + content);
        // 把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        //rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A, RabbitConfig.ROUTINGKEY_A, content, correlationId);
    }

    public void sendMsgB(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());

        System.out.println("开始发送消息 : " + content.toLowerCase() + " ,correlationId= " + correlationId);
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A, RabbitConfig.ROUTINGKEY_B, content, correlationId);
        System.out.println("结束发送消息 : " + content.toLowerCase());
        System.out.println("消费者响应B :   消息处理完成");

        //logger.info(" 发送消息TO A:" + content);
        // 把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        //rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A, RabbitConfig.ROUTINGKEY_A, content, correlationId);
    }

    public void sendMsgC(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());

        System.out.println("开始发送消息 : " + content.toLowerCase() + " ,correlationId= " + correlationId);
        rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A, RabbitConfig.ROUTINGKEY_C, content, correlationId);
        System.out.println("结束发送消息 : " + content.toLowerCase());
        System.out.println("消费者响应C :   消息处理完成");

        //logger.info(" 发送消息TO A:" + content);
        // 把消息放入ROUTINGKEY_A对应的队列当中去，对应的是队列A
        //rabbitTemplate.convertAndSend(RabbitConfig.EXCHANGE_A, RabbitConfig.ROUTINGKEY_A, content, correlationId);
    }

    /** * 回调 */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info(" 回调id:" + correlationData);
        if (ack) {
            logger.info("消息成功消费");
        } else {
            logger.info("消息消费失败:" + cause);
        }



    }

}

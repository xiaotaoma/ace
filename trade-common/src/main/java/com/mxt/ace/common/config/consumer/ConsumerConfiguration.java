package com.mxt.ace.common.config.consumer;

import com.mxt.ace.common.service.consumer.ConsumerListener;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.exception.MQClientException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConsumerConfiguration {

    @Value("${rocketmq.producer.namesrv.addr}")
    private String nameServerAdd;

    @Bean
    public DefaultMQPushConsumer consumer() throws MQClientException {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumerGroupName");
        consumer.setNamesrvAddr(nameServerAdd);
        consumer.setInstanceName("consumer");
        consumer.subscribe("topic", "*");
        consumer.registerMessageListener(new ConsumerListener());
        consumer.start();
        return consumer;
    }
}

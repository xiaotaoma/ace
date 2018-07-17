package com.mxt.ace.common.config;

import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AceMqProducer {
    private static final Logger logger = LoggerFactory.getLogger(AceMqProducer.class);
    @Value("${rocketmq.producer.group.name}")
    private String groupName;
    @Value("${rocketmq.producer.namesrv.addr}")
    private String nameServerAdd;
    @Value("${rocketmq.producer.message.max}")
    private int maxMessageSize;
    @Value("${rocketmq.producer.message.send.timeout}")
    private int sendMsgTimeout;

    @Bean
    public DefaultMQProducer producer() throws MQClientException {
        DefaultMQProducer producer = new DefaultMQProducer();
        producer.setProducerGroup(groupName);
        producer.setNamesrvAddr(nameServerAdd);
        producer.setMaxMessageSize(maxMessageSize);//最大消息
        producer.setSendMsgTimeout(sendMsgTimeout);//发送消息超时时间
        producer.start();
        return producer;
    }





}

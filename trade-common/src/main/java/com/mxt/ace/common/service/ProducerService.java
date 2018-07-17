package com.mxt.ace.common.service;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.client.producer.SendStatus;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    private DefaultMQProducer producer;

    public void sendMsg(String msg, String topic) throws InterruptedException, RemotingException, MQClientException, MQBrokerException {
        Message message = new Message();
        message.setBody(msg.getBytes());
        message.setTopic(topic);
        SendResult sendResult = producer.send(message);
        SendStatus sendStatus = sendResult.getSendStatus();
    }
}

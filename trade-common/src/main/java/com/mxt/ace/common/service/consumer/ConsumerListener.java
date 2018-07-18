package com.mxt.ace.common.service.consumer;

import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

public class ConsumerListener implements MessageListenerConcurrently {

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        for (int i = 0; i < list.size(); i++) {
            MessageExt messageExt = list.get(i);
            byte[] body = messageExt.getBody();
            System.out.println(new String(body));
        }
        return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
    }
}

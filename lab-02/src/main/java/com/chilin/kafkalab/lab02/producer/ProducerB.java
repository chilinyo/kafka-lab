package com.chilin.kafkalab.lab02.producer;

import com.chilin.kafkalab.lab02.message.MessageB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

/**
 * @author yangzhilin
 * @date 4/6/21
 */
@Component
public class ProducerB {

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public ListenableFuture<SendResult<Object, Object>> asyncSend(Integer id) {
        MessageB messageB = new MessageB();
        messageB.setId(id);
        return kafkaTemplate.send(MessageB.TOPIC, messageB);
    }

}

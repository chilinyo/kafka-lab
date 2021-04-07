package com.chilin.kafkalab.lab03.producer;

import com.chilin.kafkalab.lab03.message.MessageC;
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
public class ProducerC {

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public ListenableFuture<SendResult<Object, Object>> asyncSend(Integer id) {
        MessageC messageC = new MessageC();
        messageC.setId(id);
        return kafkaTemplate.send(MessageC.TOPIC, messageC);
    }

}

package com.chilin.kafkalab.lab04.producer;

import com.chilin.kafkalab.lab04.message.MessageD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

/**
 * @author yangzhilin
 * @date 4/6/21
 */
@Component
public class ProducerD {

    @Autowired
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public ListenableFuture<SendResult<Object, Object>> asyncSend(Integer id) {
        MessageD messageD = new MessageD();
        messageD.setId(id);
        return kafkaTemplate.send(MessageD.TOPIC, messageD);
    }

    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {
        MessageD messageD = new MessageD();
        messageD.setId(id);
        return kafkaTemplate.send(MessageD.TOPIC, messageD).get();
    }

}

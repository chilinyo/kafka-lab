package com.chilin.kafkalab.lab01.producer;

import com.chilin.kafkalab.lab01.message.MessageA;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;

import javax.annotation.Resource;
import java.util.concurrent.ExecutionException;

/**
 * @author yangzhilin
 * @date 4/6/21
 */
@Component
public class ProducerA {


    @Resource
    private KafkaTemplate<Object, Object> kafkaTemplate;

    public SendResult syncSend(Integer id) throws ExecutionException, InterruptedException {
        MessageA messageA = new MessageA();
        messageA.setId(id);
        return kafkaTemplate.send(MessageA.TOPIC, messageA).get();
    }

    public ListenableFuture<SendResult<Object, Object>> asyncSend(Integer id) {
        MessageA messageA = new MessageA();
        messageA.setId(id);
        return kafkaTemplate.send(MessageA.TOPIC, messageA);
    }



}

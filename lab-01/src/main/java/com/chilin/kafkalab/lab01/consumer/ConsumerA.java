package com.chilin.kafkalab.lab01.consumer;

import com.chilin.kafkalab.lab01.message.MessageA;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author yangzhilin
 * @date 4/6/21
 */
@Component
@Slf4j
public class ConsumerA {

    @KafkaListener(topics = MessageA.TOPIC, groupId = "lab01-A-consumer-group-" + MessageA.TOPIC)
    public void onMessage(MessageA messageA) {
        log.info("[ConsumerA] >>>>> [Thread id:{}, Message:{}]", Thread.currentThread().getId(), messageA);
    }

}

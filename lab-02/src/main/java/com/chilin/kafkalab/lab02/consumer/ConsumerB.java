package com.chilin.kafkalab.lab02.consumer;

import com.chilin.kafkalab.lab02.message.MessageB;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @author yangzhilin
 * @date 4/6/21
 */
@Component
@Slf4j
public class ConsumerB {

    @KafkaListener(topics = MessageB.TOPIC, groupId = "lab02-B-consumer-group-" + MessageB.TOPIC)
    public void onMessage(MessageB messageB) {
        log.info("[ConsumerB] >>>>> 消息内容: {}", messageB);
    }

}

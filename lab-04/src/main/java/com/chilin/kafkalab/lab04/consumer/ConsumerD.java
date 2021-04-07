package com.chilin.kafkalab.lab04.consumer;

import com.chilin.kafkalab.lab04.message.MessageD;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yangzhilin
 * @date 4/6/21
 */
@Component
@Slf4j
public class ConsumerD {

    @KafkaListener(topics = MessageD.TOPIC, groupId = "lab04-C-consumer-group-" + MessageD.TOPIC)
    public void onMessage(MessageD message) {

        log.info("[ConsumerD] >>>>> 消息内容: {}", message);

        throw new RuntimeException("模拟抛错");

    }

}

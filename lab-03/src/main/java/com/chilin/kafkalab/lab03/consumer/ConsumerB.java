package com.chilin.kafkalab.lab03.consumer;

import com.chilin.kafkalab.lab03.message.MessageC;
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
public class ConsumerB {

    @KafkaListener(topics = MessageC.TOPIC, groupId = "lab03-C-consumer-group-" + MessageC.TOPIC)
    public void onMessage(List<MessageC> messageList) {
        for (MessageC messageC : messageList) {
            log.info("[ConsumerC] >>>>> 消息内容: {}", messageC);
        }
    }

}

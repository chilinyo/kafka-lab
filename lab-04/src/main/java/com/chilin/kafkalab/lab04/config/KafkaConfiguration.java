package com.chilin.kafkalab.lab04.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.ConsumerRecordRecoverer;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.ErrorHandler;
import org.springframework.kafka.listener.SeekToCurrentErrorHandler;
import org.springframework.util.backoff.BackOff;
import org.springframework.util.backoff.FixedBackOff;

/**
 * @author yangzhilin
 * @date 4/7/21
 */
@Configuration
public class KafkaConfiguration {


    @Bean
    @Primary
    public ErrorHandler kafkaErrorHandler(KafkaTemplate<?, ?> kafkaTemplate) {

        // 如果超过最大重试次数也无法处理消息，将加入 Dead Letter Queue，topic 由 xxx 变成 xxx.DLT
        ConsumerRecordRecoverer recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate);

        // 配置重试3次，每次间隔10秒
        BackOff backOff = new FixedBackOff(10 * 1000L, 3L);

        return new SeekToCurrentErrorHandler(recoverer, backOff);
    }
}

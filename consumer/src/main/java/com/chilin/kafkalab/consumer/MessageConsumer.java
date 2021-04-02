package com.chilin.kafkalab.consumer;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

/**
 * @author yangzhilin
 * @date 4/2/21
 */
public class MessageConsumer {

    private static Consumer<String, String> createConsumer() {
        // Producer 属性
        Properties properties = new Properties();
        // broker 地址
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        // 消费者分组
        properties.put("group.id", "groupA");
        // 设置消费者分组最初的消费进度为 earliest
        properties.put("auto.offset.reset", "earliest");
        // 自动 commit 消费
        properties.put("enable.auto.commit", true);
        // 自动 commit 频率
        properties.put("auto.commit.interval.ms", "1000");

        // key value 的序列化方式
        properties.put("key.deserializer", StringDeserializer.class.getName());
        properties.put("value.deserializer", StringDeserializer.class.getName());

        return new KafkaConsumer<>(properties);
    }

    public static void main(String[] args) {
        Consumer<String, String> consumer = createConsumer();

        // 订阅topic
        consumer.subscribe(Collections.singleton("testtopic"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofSeconds(10));

            records.forEach(record -> {
                System.out.println(String.format("[MessageConsumer] >>>>> readMessage: %s -> %s", record.key(), record.value()));
            });

        }
    }
}

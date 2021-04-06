package com.chilin.kafkalab.lab01.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @author yangzhilin
 * @date 4/2/21
 */
public class MessageProducer {

    private static Producer<String, String> createProducer() {
        // Producer 属性
        Properties properties = new Properties();
        // broker 地址
        properties.put("bootstrap.servers", "127.0.0.1:9092");
        // acks: 0-无需答应 1-leader答应 all-所有 leader 和 follower 答应
        properties.put("acks", "1");
        // 失败重试次数
        properties.put("retries", "3");
        // key value 的序列化方式
        properties.put("key.serializer", StringSerializer.class.getName());
        properties.put("value.serializer", StringSerializer.class.getName());

        return new KafkaProducer<>(properties);

    }


    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Producer<String, String> producer = createProducer();
        ProducerRecord<String, String> producerRecord = new ProducerRecord<>("testtopic", "key1", "value1");
        Future<RecordMetadata> send = producer.send(producerRecord);
        RecordMetadata recordMetadata = send.get();
        System.out.println(String.format("[MessageProducer] >>>>> message send to %s, partition: %s, offset: %s", recordMetadata.topic(), recordMetadata.partition(), recordMetadata.offset()));
    }
}

package com.chilin.kafkalab.lab01.producer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;


/**
 * @author yangzhilin
 * @date 4/6/21
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class ProducerATest {

    @Autowired
    private ProducerA producerA;

    @Test
    public void syncSend() throws ExecutionException, InterruptedException {

        int id = (int) (System.currentTimeMillis() / 1000);
        SendResult sendResult = producerA.syncSend(id);

        log.info("[ProducerATest] >>>>> 发送消息: {}", sendResult);

        new CountDownLatch(1).await();

    }

    @Test
    public void asyncSend() throws InterruptedException {
        int id = (int) (System.currentTimeMillis() / 1000);
        producerA.asyncSend(id).addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                log.info("[ProducerATest] >>>>> 消息id: {}, 发送失败: {}", id, throwable);
            }

            @Override
            public void onSuccess(SendResult<Object, Object> objectObjectSendResult) {
                log.info("[ProducerATest] >>>>> 消息id: {}, 发送成功: {}", id, objectObjectSendResult);
            }
        });

        new CountDownLatch(1).await();
    }
}
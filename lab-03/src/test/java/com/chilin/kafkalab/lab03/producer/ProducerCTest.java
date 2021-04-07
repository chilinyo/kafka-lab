package com.chilin.kafkalab.lab03.producer;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.concurrent.ListenableFutureCallback;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author yangzhilin
 * @date 4/7/21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
@Slf4j
class ProducerCTest {
    @Autowired
    private ProducerC producerC;

    @Test
    public void asyncSend() throws InterruptedException {

        log.info("[ProducerBTest] >>>>> start to send...");
        for (int i = 0; i < 10; i++) {
            int id = (int) (System.currentTimeMillis() / 1000);
            producerC.asyncSend(i).addCallback(new ListenableFutureCallback<SendResult<Object, Object>>() {
                @Override
                public void onFailure(Throwable throwable) {
                    log.info("[ProducerATest] >>>>> 消息id: {}, 发送失败: {}", id, throwable);
                }

                @Override
                public void onSuccess(SendResult<Object, Object> objectObjectSendResult) {
                    log.info("[ProducerATest] >>>>> 消息id: {}, 发送成功: {}", id, objectObjectSendResult);
                }
            });

            TimeUnit.SECONDS.sleep(2);

        }

        TimeUnit.SECONDS.sleep(10);
    }
}
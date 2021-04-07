package com.chilin.kafkalab.lab04.producer;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author yangzhilin
 * @date 4/7/21
 */
@SpringBootTest
@RunWith(SpringRunner.class)
class ProducerDTest {

    @Autowired
    private ProducerD producerD;

    @Test
    public void syncSend() throws ExecutionException, InterruptedException {
        Integer id = 1;
        SendResult sendResult = producerD.syncSend(id);


        System.out.println("[ProducerDTest] >>>>> 发送信息: " + sendResult.toString());




    }
}
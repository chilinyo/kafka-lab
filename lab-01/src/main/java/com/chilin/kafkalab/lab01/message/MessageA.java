package com.chilin.kafkalab.lab01.message;

import lombok.Data;

/**
 * @author yangzhilin
 * @date 4/6/21
 */

@Data
public class MessageA {

    public static final String TOPIC = "TOPIC_A";

    private Integer id;


}

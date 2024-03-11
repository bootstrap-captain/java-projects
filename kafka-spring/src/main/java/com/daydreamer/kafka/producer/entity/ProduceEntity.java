package com.daydreamer.kafka.producer.entity;

import lombok.Data;

@Data
public class ProduceEntity {
    private String topicName;
    private String key;
    private String value;
    private Integer partition;
}

package com.citi.daydreamer.controller;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/producer")
@RequiredArgsConstructor
public class ProducerController {

    private final KafkaTemplate<String, String> kafkaTemplate;


    @GetMapping("/send")
    public void send(String topicName, String key, String value) {
        ProducerRecord<String, String> record = new ProducerRecord<>(topicName, key, value);
        kafkaTemplate.send(record);
    }
}

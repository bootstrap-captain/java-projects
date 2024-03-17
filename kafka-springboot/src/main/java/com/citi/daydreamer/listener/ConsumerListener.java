package com.citi.daydreamer.listener;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;

@Configuration
@Slf4j
public class ConsumerListener {

    private final String citiGroupId = "erick_001";

    /**
     * topics: 可以监听多个topic
     * groupId: 覆盖配置文件中的groupId
     * concurrency： consumer group中创建几个consumer
     * @param <T>
     */
    @KafkaListener(topics = {"nike"}, containerFactory = "kafkaListenerContainerFactory", groupId = citiGroupId, concurrency = "3")
    public <T> void consumerTopic(ConsumerRecord<String, String> record, Acknowledgment ack) {
        try {
            log.info("receive msg");
            parse(record);
        } catch (Exception e) {
            log.error("message process failed", e);
        } finally {
            long start = System.currentTimeMillis();
            ack.acknowledge();
            log.info("ack times={}", System.currentTimeMillis() - start);
        }
    }

    private void parse(ConsumerRecord<String, String> record) {
        String value = record.value();
        log.info("msg={}", value);
    }
}

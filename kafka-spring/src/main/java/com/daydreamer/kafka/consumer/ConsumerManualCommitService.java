package com.daydreamer.kafka.consumer;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Properties;

@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerManualCommitService {

    private final ConsumerCustomizedConfig consumerCustomizedConfig;

    /**
     * 同步提交
     *
     * @param groupId
     * @param topicName
     */
    public void syncConsume(String groupId, String topicName) {
        Properties properties = consumerCustomizedConfig.getManualCommit(groupId);
        KafkaConsumer<String, Object> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Lists.newArrayList(topicName));

        /*消费数据*/
        while (true) {
            // 间隔几秒拉取一次
            ConsumerRecords<String, Object> results = consumer.poll(Duration.ofSeconds(3));
            log.info("{} ===== begin to poll msg", consumer);
            for (ConsumerRecord<String, Object> record : results) {
                log.info("{} receive msg sync===== consumerRecord={}", consumer, record);
                consumer.commitSync();
            }
        }
    }

    /**
     * 异步提交
     *
     * @param groupId
     * @param topicName
     */
    public void asyncConsume(String groupId, String topicName) {
        Properties properties = consumerCustomizedConfig.getManualCommit(groupId);
        KafkaConsumer<String, Object> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Lists.newArrayList(topicName));

        /*消费数据*/
        while (true) {
            // 间隔几秒拉取一次
            ConsumerRecords<String, Object> results = consumer.poll(Duration.ofSeconds(3));
            log.info("{} ===== begin to poll msg", consumer);
            for (ConsumerRecord<String, Object> record : results) {
                log.info("{} receive msg async ===== consumerRecord={}", consumer, record);
                consumer.commitAsync();
            }
        }
    }
}

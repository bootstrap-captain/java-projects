package com.daydreamer.kafka.consumer;

import com.google.common.collect.Lists;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * 自动提交Offset
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ConsumerAutoCommitService {

    private final ConsumerCustomizedConfig consumerCustomizedConfig;

    public void checkOffset(){
        String offset = "_consumer_offsets";
        String groupId = "admin_user";
        Properties properties = consumerCustomizedConfig.getAutoCommitProperties(groupId);
        KafkaConsumer<String, Object> consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(Lists.newArrayList(offset));
        while (true){
            ConsumerRecords<String, Object> records = consumer.poll(Duration.ofSeconds(5));
            for (ConsumerRecord<String, Object> record : records) {
                log.info("{} receive msg ===== offset={}", consumer, record);
            }
        }
    }

    /**
     * 订阅指定的topicName
     *
     * @param topicName
     */
    public void consumeTopic(String groupId, String topicName) {
        Properties properties = consumerCustomizedConfig.getAutoCommitProperties(groupId);
        KafkaConsumer<String, Object> consumer = new KafkaConsumer<>(properties);

        consumer.subscribe(Lists.newArrayList(topicName));
        parseMessage(consumer);
    }

    /**
     * 订阅某个topic的某个分区
     *
     * @param topicName
     * @param partitionNo
     */
    public void consumeTopicPartition(String groupId, String topicName, Integer partitionNo) {
        Properties properties = consumerCustomizedConfig.getAutoCommitProperties(groupId);
        KafkaConsumer<String, Object> consumer = new KafkaConsumer<>(properties);

        consumer.assign(Lists.newArrayList(new TopicPartition(topicName, partitionNo)));
        parseMessage(consumer);
    }

    /**
     * 多个消费者来消费同一个topic
     * @param groupId
     * @param consumerNumbers
     * @param topicName
     */
    public void consumeWithConsumerGroup(String groupId, int consumerNumbers, String topicName) {
        for (int i = 0; i < consumerNumbers; i++) {
            new Thread(() -> consumeTopic(groupId, topicName)).start();

            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 不断拉数据
     */
    private void parseMessage(KafkaConsumer<String, Object> consumer) {
        /*消费数据*/
        while (true) {
            // 间隔几秒拉取一次
            ConsumerRecords<String, Object> results = consumer.poll(Duration.ofSeconds(3));
            log.info("{} ===== begin to poll msg", consumer);
            for (ConsumerRecord<String, Object> record : results) {
                log.info("{} receive msg ===== consumerRecord={}", consumer, record);
            }
        }
    }

}

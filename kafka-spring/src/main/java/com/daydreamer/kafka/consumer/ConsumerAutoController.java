package com.daydreamer.kafka.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consume/auto")
@RequiredArgsConstructor
public class ConsumerAutoController {

    private final ConsumerAutoCommitService consumerAutoCommitService;

    @GetMapping("/byTopic")
    public void consumeTopics(String groupId, String topicName) {
        consumerAutoCommitService.consumeTopic(groupId, topicName);
    }

    @GetMapping("/byTopicPartition")
    public void consumeTopicPartition(String groupId, String topicName, Integer partitionNo) {
        consumerAutoCommitService.consumeTopicPartition(groupId, topicName, partitionNo);
    }

    @GetMapping("/byTopic/mulit/consumer")
    public void consumeTopicByMultiConsumer(String groupId, int consumerNumber, String topicName) {
        consumerAutoCommitService.consumeWithConsumerGroup(groupId, consumerNumber, topicName);
    }

    @GetMapping("/checkOffset")
    public void checkOffset(){
        consumerAutoCommitService.checkOffset();
    }
}

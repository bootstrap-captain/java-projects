package com.daydreamer.kafka.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.*;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Slf4j
public class AdminService {

    private final AdminClient adminClient;


    public void deleteTopic(String... topicNames) {
        adminClient.deleteTopics(Arrays.stream(topicNames).toList());
    }

    public void createTopic(String topicName) {
        List<NewTopic> topics = new ArrayList<>();
        /*参数1： topicName
         * 参数2： 分区数：分区数小于服务器数
         * 参数3： 副本数：副本数应该是服务器数-1*/
        NewTopic topic = new NewTopic(topicName, 1, (short) 2);
        topics.add(topic);
        CreateTopicsResult createTopicsResult = adminClient.createTopics(topics);
        log.info(createTopicsResult.toString());
    }

    public Set<String> listTopics() {
        try {
            return adminClient.listTopics().names().get();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package com.citi.daydreamer.admin;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.TopicDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaAdmin;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class AminService {

    @Autowired
    private KafkaAdmin kafkaAdmin;

    /**
     * 具体来查看某些topic
     *
     * @param topicName
     * @return
     */
    public Map<String, TopicDescription> describeTopics(String topicName) {
        Map<String, TopicDescription> result = kafkaAdmin.describeTopics(topicName);
        log.info(result.toString());
        return result;
    }

    /**
     * 查看admin的参数
     * @return
     */
    public Map<String, Object> getAdminConfiguration() {
        Map<String, Object> result = kafkaAdmin.getConfigurationProperties();
        log.info(result.toString());
        return result;
    }

}

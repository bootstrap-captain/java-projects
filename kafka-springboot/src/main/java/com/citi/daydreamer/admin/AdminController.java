package com.citi.daydreamer.admin;

import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.TopicDescription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {

    @Autowired
    private AminService adminService;

    @GetMapping("/describe/topics")
    public Map<String, TopicDescription> describeTopics(String topicName) {
        Map<String, TopicDescription> result = adminService.describeTopics(topicName);
        return result;
    }

    @GetMapping("/config")
    public Map<String,Object> getConfig(){
        return adminService.getAdminConfiguration();
    }
}

package com.daydreamer.kafka.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RequestMapping("/admin")
@RestController
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/listTopics")
    public Set<String> listTopics() {
        return adminService.listTopics();
    }

    @GetMapping("/createTopics")
    public void createTopics(String topicName) {
        adminService.createTopic(topicName);
    }

    @GetMapping("/deleteTopics")
    public void deleteTopics(String topicNames) {
        adminService.deleteTopic(topicNames);
    }
}

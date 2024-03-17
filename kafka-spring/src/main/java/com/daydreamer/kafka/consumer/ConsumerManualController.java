package com.daydreamer.kafka.consumer;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consume/auto")
@RequiredArgsConstructor
public class ConsumerManualController {

    private final ConsumerManualCommitService commitService;
    @GetMapping("/manual/async")
    public void asyncCommit(String groupId, String topicName){
        commitService.asyncConsume(groupId,topicName);
    }

    @GetMapping("/manual/sync")
    public void syncCommit(String groupId, String topicName){
        commitService.syncConsume(groupId,topicName);
    }
}

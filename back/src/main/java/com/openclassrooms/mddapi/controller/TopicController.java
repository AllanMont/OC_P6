package com.openclassrooms.mddapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.service.TopicService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class TopicController {
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @PostMapping("/topics")
    public ResponseEntity create(@RequestBody TopicDto topic) {
        Topic newTopic = new Topic();
        newTopic.setName(topic.getName());
        newTopic.setDescription(topic.getDescription());

        topicService.create(newTopic);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

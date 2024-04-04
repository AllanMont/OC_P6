package com.openclassrooms.mddapi.controller;

import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.model.Topic;
import com.openclassrooms.mddapi.service.TopicService;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/topics")
public class TopicController {
    private final TopicService topicService;

    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public List<Topic> getAllTopics(@RequestParam(required = false) String name) {
        return topicService.getAllTopics(name);
    }

    @GetMapping("/{id}")
    public Topic getTopicById(@PathVariable Integer id) {
        return topicService.getTopicById(id);
    }


    @PostMapping
    public ResponseEntity create(@RequestBody TopicDto topic) {
        Topic newTopic = new Topic();
        newTopic.setName(topic.getName());
        newTopic.setDescription(topic.getDescription());

        topicService.create(newTopic);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    
}

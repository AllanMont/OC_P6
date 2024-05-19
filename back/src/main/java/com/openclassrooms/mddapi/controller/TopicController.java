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


/**
 * Controller class for handling topic-related HTTP requests.
 */
@RestController
@RequestMapping("/topics")
public class TopicController {

    private final TopicService topicService;

    /**
     * Constructor for TopicController.
     * @param topicService The TopicService instance to be injected.
     */
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    /**
     * Endpoint to retrieve all topics.
     * @return List of all topics.
     */
    @GetMapping
    public List<Topic> getAllTopics() {
        return topicService.getAllTopics();
    }

    /**
     * Endpoint to retrieve a topic by ID.
     * @param id The ID of the topic to retrieve.
     * @return The topic with the specified ID.
     */
    @GetMapping("/{id}")
    public Topic getTopicById(@PathVariable Integer id) {
        return topicService.getTopicById(id);
    }

    /**
     * Endpoint to create a new topic.
     * @param topic The topic DTO containing the details of the new topic.
     * @return ResponseEntity with HTTP status 201 (CREATED) if successful, or 500 (INTERNAL SERVER ERROR) if an error occurs.
     */
    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody TopicDto topic) {
        Topic newTopic = new Topic();
        newTopic.setName(topic.getName());
        newTopic.setDescription(topic.getDescription());

        topicService.create(newTopic);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}

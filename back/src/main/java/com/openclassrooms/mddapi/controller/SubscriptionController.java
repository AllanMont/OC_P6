package com.openclassrooms.mddapi.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.SubscriptionId;
import com.openclassrooms.mddapi.service.SubscriptionService;
import com.openclassrooms.mddapi.service.UserService;
import com.openclassrooms.mddapi.service.TopicService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {
    private final SubscriptionService subscriptionService;
    private final UserService userService;
    private final TopicService topicService;

    public SubscriptionController(SubscriptionService subscriptionService, UserService userService, TopicService topicService) {
        this.subscriptionService = subscriptionService;
        this.userService = userService;
        this.topicService = topicService;
    }

    @GetMapping()
    public Boolean isSubscriptionExist(@RequestParam Integer userId, @RequestParam Integer topicId) {
        return subscriptionService.getSubscriptionByUserIdAndTopicId(userId, topicId) != null;
    }
    
    @PostMapping
    public ResponseEntity<HttpStatus> create(@RequestBody SubscriptionDto subscriptionDto) {
    SubscriptionId subscriptionId = new SubscriptionId(
        subscriptionDto.getUserId(),
        subscriptionDto.getTopicId()
    );

    if (subscriptionService.getSubscriptionByUserIdAndTopicId(subscriptionDto.getUserId(), subscriptionDto.getTopicId()) != null) {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }

    if(userService.getUserById(subscriptionDto.getUserId()) == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    else if(topicService.getTopicById(subscriptionDto.getTopicId()) == null) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
    

    Subscription newSubscription = new Subscription();
    newSubscription.setId(subscriptionId);
    newSubscription.setCreatedAt(LocalDateTime.now());

    subscriptionService.create(newSubscription);

    return ResponseEntity.status(HttpStatus.CREATED).build();
}

    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@RequestBody SubscriptionDto subscriptionDto) {
        SubscriptionId subscriptionId = new SubscriptionId(
            subscriptionDto.getUserId(),
            subscriptionDto.getTopicId()
        );

        if (subscriptionService.getSubscriptionByUserIdAndTopicId(subscriptionDto.getUserId(), subscriptionDto.getTopicId()) == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Subscription newSubscription = new Subscription();
        newSubscription.setId(subscriptionId);
    
        subscriptionService.delete(newSubscription);

        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

package com.openclassrooms.mddapi.controller;

import java.time.LocalDateTime;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.openclassrooms.mddapi.dto.SubscriptionDto;
import com.openclassrooms.mddapi.dto.TopicDto;
import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.SubscriptionId;
import com.openclassrooms.mddapi.service.SubscriptionService;
import com.openclassrooms.mddapi.service.UserService;
import com.openclassrooms.mddapi.service.TopicService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Controller class for handling subscription-related HTTP requests.
 */
@RestController
@RequestMapping("/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;
    private final UserService userService;
    private final TopicService topicService;

    /**
     * Constructor for SubscriptionController.
     * @param subscriptionService The SubscriptionService instance to be injected.
     * @param userService The UserService instance to be injected.
     * @param topicService The TopicService instance to be injected.
     */
    public SubscriptionController(SubscriptionService subscriptionService, UserService userService, TopicService topicService) {
        this.subscriptionService = subscriptionService;
        this.userService = userService;
        this.topicService = topicService;
    }

    /**
     * Endpoint to check if a subscription exists for a user and topic.
     * @param topicId The ID of the topic.
     * @param authentication The authentication object containing user details.
     * @return true if the subscription exists, false otherwise.
     */
    @GetMapping()
    public Boolean isSubscriptionExist(@RequestParam Integer topicId, Authentication authentication) {
        Integer userId = userService.getUserIdByName(authentication);
        
        return subscriptionService.getSubscriptionByUserIdAndTopicId(userId, topicId) != null;
    }

    /**
     * Endpoint to subscribe to a topic.
     * @param topicId The ID of the topic to subscribe to.
     * @param authentication The authentication object containing user details.
     * @return ResponseEntity with HTTP status 201 (CREATED) if successful, or an appropriate error status if unsuccessful.
     */
    @PostMapping("/subscribe")
    public ResponseEntity<HttpStatus> subscribe(@RequestBody Integer topicId, Authentication authentication) {
        Integer userId = userService.getUserIdByName(authentication);

        SubscriptionId subscriptionId = new SubscriptionId(userId, topicId);

        if (subscriptionService.getSubscriptionByUserIdAndTopicId(userId, topicId) != null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        if(userService.getUserById(userId) == null || topicService.getTopicById(topicId) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        Subscription newSubscription = new Subscription();
        newSubscription.setId(subscriptionId);
        newSubscription.setCreatedAt(LocalDateTime.now());

        subscriptionService.create(newSubscription);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    /**
     * Endpoint to delete a subscription.
     * @param idSubscription The ID of the subscription to delete.
     * @param authentication The authentication object containing user details.
     * @return ResponseEntity with HTTP status 200 (OK) if successful, or an appropriate error status if unsuccessful.
     */
    @DeleteMapping
    public ResponseEntity<HttpStatus> delete(@RequestParam Integer idSubscription, Authentication authentication) {
        Integer userId = userService.getUserIdByName(authentication);

        SubscriptionId subscriptionId = new SubscriptionId(userId, idSubscription);

        if (subscriptionService.getSubscriptionByUserIdAndTopicId(userId, idSubscription) == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        Subscription newSubscription = new Subscription();
        newSubscription.setId(subscriptionId);
    
        subscriptionService.delete(newSubscription);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    /**
     * Endpoint to retrieve all subscriptions for a user.
     * @param authentication The authentication object containing user details.
     * @return ResponseEntity with the list of subscriptions if successful, or an appropriate error status if unsuccessful.
     */
    @GetMapping("/user")
    public ResponseEntity<?> getSubscriptionsByUserId(Authentication authentication) {
        Integer userId = userService.getUserIdByName(authentication);
        List<Subscription> subscriptions = subscriptionService.getSubscriptionsByUserId(userId);

        return ResponseEntity.ok(subscriptions);
    }

    /**
     * Endpoint to retrieve the title of a topic by ID.
     * @param topicId The ID of the topic.
     * @return ResponseEntity with the topic title if successful, or an appropriate error status if unsuccessful.
     */
    @GetMapping("/topic")
    public ResponseEntity<?> getTitleTopicById(@RequestParam Integer topicId) {
        return ResponseEntity.ok(topicService.getTopicById(topicId));
    }
}

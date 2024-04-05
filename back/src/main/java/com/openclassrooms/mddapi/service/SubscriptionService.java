package com.openclassrooms.mddapi.service;

import org.springframework.stereotype.Service;

import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.SubscriptionId;
import com.openclassrooms.mddapi.repository.SubscriptionRepository;

@Service
public class SubscriptionService {
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public void create(Subscription subscription) {
        subscriptionRepository.save(subscription);
    }

    public void delete(Subscription subscription) {
        subscriptionRepository.delete(subscription);
    }

    public Subscription getSubscriptionByUserIdAndTopicId(Integer userId, Integer topicId) {
        return subscriptionRepository.findByIdUserIdAndIdTopicId(userId, topicId);
    }
}

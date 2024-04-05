package com.openclassrooms.mddapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.openclassrooms.mddapi.model.Subscription;
import com.openclassrooms.mddapi.model.SubscriptionId;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, SubscriptionId> {

    Subscription findByIdUserIdAndIdTopicId(Integer userId, Integer topicId);
}
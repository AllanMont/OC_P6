package com.openclassrooms.mddapi.model;

import java.time.LocalDateTime;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "subscriptions")
public class Subscription {
    
    @EmbeddedId
    private SubscriptionId id;

    private LocalDateTime createdAt;
}

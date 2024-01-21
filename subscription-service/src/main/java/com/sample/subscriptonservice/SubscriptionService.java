package com.sample.subscriptonservice;

import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
public interface SubscriptionService {
    Subscription saveSubscription(Subscription subscription);

    ResponseDto getSubscription(String subscription);

    List<Subscription> getSubscriptions();
}
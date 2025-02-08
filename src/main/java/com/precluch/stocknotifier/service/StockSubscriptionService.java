package com.precluch.stocknotifier.service;

import com.precluch.stocknotifier.model.StockSubscription;
import com.precluch.stocknotifier.repository.StockSubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StockSubscriptionService {

    private final StockSubscriptionRepository subscriptionRepository;

    @Autowired
    public StockSubscriptionService(StockSubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    @Transactional
    public void subscribe(String userId, String stockTicker) {
        // Check if subscription already exists
        if (!subscriptionRepository.existsByUserIdAndStockTicker(userId, stockTicker)) {
            StockSubscription subscription = new StockSubscription();
            subscription.setUserId(userId);
            subscription.setStockTicker(stockTicker);
            subscriptionRepository.save(subscription);
        }
    }

    @Transactional
    public void unsubscribe(String userId, String stockTicker) {
        subscriptionRepository.deleteByUserIdAndStockTicker(userId, stockTicker);
    }

    public List<StockSubscription> getSubscriptionsForUser(String userId) {
        return subscriptionRepository.findByUserId(userId);
    }

    public List<StockSubscription> getSubscribersForStock(String stockTicker) {
        return subscriptionRepository.findByStockTicker(stockTicker);
    }
}
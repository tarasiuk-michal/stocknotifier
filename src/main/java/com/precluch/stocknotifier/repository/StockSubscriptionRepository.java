package com.precluch.stocknotifier.repository;

import com.precluch.stocknotifier.model.StockSubscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StockSubscriptionRepository extends JpaRepository<StockSubscription, Long> {

    // Custom query to check if a subscription already exists
    boolean existsByUserIdAndStockTicker(String userId, String stockTicker);

    // Find all subscriptions for a specific user
    List<StockSubscription> findByUserId(String userId);

    // Find all subscribers for a specific stock ticker
    List<StockSubscription> findByStockTicker(String stockTicker);

    // Delete a specific subscription
    void deleteByUserIdAndStockTicker(String userId, String stockTicker);
}
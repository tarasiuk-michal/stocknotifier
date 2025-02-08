package com.precluch.stocknotifier.service;

import com.precluch.stocknotifier.model.StockSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PriceChangeProcessor {

    private static final double PRICE_CHANGE_THRESHOLD = 2.0; // 2% change threshold
    private final Map<String, Double> lastKnownPrices = new HashMap<>();

    private final StockSubscriptionService subscriptionService;
    private final NotificationService notificationService;

    @Autowired
    public PriceChangeProcessor(StockSubscriptionService subscriptionService,
                                NotificationService notificationService) {
        this.subscriptionService = subscriptionService;
        this.notificationService = notificationService;
    }

    @KafkaListener(topics = "stock-prices", groupId = "price-change-group")
    public void processStockPrice(String message) {
        String[] parts = message.split(":");
        String ticker = parts[0];
        double currentPrice = Double.parseDouble(parts[1]);

        if (lastKnownPrices.containsKey(ticker)) {
            double previousPrice = lastKnownPrices.get(ticker);
            double percentageChange = ((currentPrice - previousPrice) / previousPrice) * 100;

            if (Math.abs(percentageChange) >= PRICE_CHANGE_THRESHOLD) {
                List<StockSubscription> subscribers = subscriptionService.getSubscribersForStock(ticker);
                notifySubscribers(subscribers, ticker, previousPrice, currentPrice);
            }
        }

        lastKnownPrices.put(ticker, currentPrice);
    }

    private void notifySubscribers(List<StockSubscription> subscribers,
                                   String ticker,
                                   double oldPrice,
                                   double newPrice) {
        for (StockSubscription subscription : subscribers) {
            String message = String.format(
                    "Alert for %s: %s price changed from %.2f to %.2f (%.2f%%)",
                    subscription.getUserId(),
                    ticker,
                    oldPrice,
                    newPrice,
                    ((newPrice - oldPrice) / oldPrice) * 100
            );
            notificationService.sendAlert(subscription.getUserId(), message);
        }
    }
}
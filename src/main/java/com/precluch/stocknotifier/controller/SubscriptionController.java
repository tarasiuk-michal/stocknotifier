package com.precluch.stocknotifier.controller;

import com.precluch.stocknotifier.service.StockSubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final StockSubscriptionService subscriptionService;

    @Autowired
    public SubscriptionController(StockSubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    @PostMapping("/subscribe")
    public String subscribe(@RequestParam String userId, @RequestParam String stockTicker) {
        subscriptionService.subscribe(userId, stockTicker);
        return "Subscribed successfully";
    }
}
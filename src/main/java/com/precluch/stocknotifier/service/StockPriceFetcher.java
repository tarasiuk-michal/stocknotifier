package com.precluch.stocknotifier.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class StockPriceFetcher {

    private static final String[] STOCK_TICKERS = {"AAPL", "GOOGL", "MSFT", "AMZN", "TSLA"};
    private final Random random = new Random();

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public StockPriceFetcher(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 5000) // Fetch prices every 5 seconds
    public void fetchAndPublishStockPrices() {
        for (String ticker : STOCK_TICKERS) {
            // Simulate price changes
            // TODO: replace with real API call
            double price = 100 + (random.nextDouble() * 50); // Random price between 100-150
            String message = String.format("%s:%.2f", ticker, price);

            kafkaTemplate.send("stock-prices", message);
        }
    }
}
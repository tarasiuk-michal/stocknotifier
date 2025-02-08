package com.precluch.stocknotifier.model;

public class StockPrice {
    private String ticker;
    private double price;
    private long timestamp;

    public StockPrice(String ticker, double price, long timestamp) {
        this.ticker = ticker;
        this.price = price;
        this.timestamp = timestamp;
    }

    public String getTicker() {
        return ticker;
    }

    public void setTicker(String ticker) {
        this.ticker = ticker;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
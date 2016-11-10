package com.example.serg.apartmentrentals.models;

/**
 * Created by sereg on 05.11.2016.
 */
public class Price {
    private String amount;

    private Converted converted;

    private String currency;

    public String getAmount() {
        return amount;
    }

    public Converted getConverted() {
        return converted;
    }

    public String getCurrency() {
        return currency;
    }
}

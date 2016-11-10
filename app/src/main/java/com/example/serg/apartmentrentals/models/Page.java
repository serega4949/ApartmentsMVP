package com.example.serg.apartmentrentals.models;

/**
 * Created by sereg on 05.11.2016.
 */
public class Page {
    private int limit;

    private int last;

    private int items;

    private int current;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getLast() {
        return last;
    }

    public void setLast(int last) {
        this.last = last;
    }

    public int getItems() {
        return items;
    }

    public void setItems(int items) {
        this.items = items;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getNext() {
        return current+1;
    }
}

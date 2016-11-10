package com.example.serg.apartmentrentals.responses;

import com.example.serg.apartmentrentals.models.Apartment;
import com.example.serg.apartmentrentals.models.Page;

import java.util.List;

/**
 * Created by sereg on 05.11.2016.
 */
public class ApartmensResponse {
    private String total;

    private Page page;

    private List<Apartment> apartments;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public Page getPage() {
        return page;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public List<Apartment> getApartments() {
        return apartments;
    }

    public void setApartments(List<Apartment> apartments) {
        this.apartments = apartments;
    }
}

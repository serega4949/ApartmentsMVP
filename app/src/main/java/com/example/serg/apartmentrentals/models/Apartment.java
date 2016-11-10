package com.example.serg.apartmentrentals.models;

/**
 * Created by sereg on 05.11.2016.
 */
public class Apartment {

    private String id;

    private Price price;

    private String last_time_up;

    private Location location;

    private String up_available_in;

    private String created_at;

    private Contact contact;

    private String photo;

    private String url;

    private String rent_type;

    public String getPhoto() {
        return photo;
    }

    public String getId() {
        return id;
    }

    public Price getPrice() {
        return price;
    }

    public String getLast_time_up() {
        return last_time_up;
    }

    public Location getLocation() {
        return location;
    }

    public String getUp_available_in() {
        return up_available_in;
    }

    public String getCreated_at() {
        return created_at;
    }

    public Contact getContact() {
        return contact;
    }

    public String getUrl() {
        return url;
    }

    public String getRent_type() {
        String result = null;

        switch (rent_type) {
            case "room":
                result = "комната";
                break;
            case "1_room":
                result = "1к";
                break;
            case "2_rooms":
                result = "2к";
                break;
            case "3_rooms":
                result = "3к";
                break;
            case "4_rooms":
                result = "4к";
                break;
            case "5_rooms":
                result = "5к";
                break;
            case "6_rooms":
                result = "6к";
                break;
        }

        return result;
    }
}

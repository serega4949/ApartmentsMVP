package com.example.serg.apartmentrentals.api;

import com.example.serg.apartmentrentals.responses.ApartmensResponse;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by sereg on 05.11.2016.
 */
public interface ApartmentsService {

    @GET("/apartments")
    Observable<ApartmensResponse> getApartments(@Query("page") int page);
}

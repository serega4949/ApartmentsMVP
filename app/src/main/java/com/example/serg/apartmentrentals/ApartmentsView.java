package com.example.serg.apartmentrentals;

import android.support.annotation.NonNull;

import com.example.serg.apartmentrentals.models.Apartment;
import com.example.serg.apartmentrentals.responses.ApartmensResponse;

import java.util.List;

/**
 * Created by sereg on 05.11.2016.
 */
public interface ApartmentsView extends LoadingView {

    void showApartments(@NonNull ApartmensResponse apartmensResponse);

    void showError();
}

package com.example.serg.apartmentrentals.ui;

import android.support.annotation.NonNull;

import com.example.serg.apartmentrentals.responses.ApartmensResponse;

/**
 * Created by sereg on 05.11.2016.
 */
public interface ApartmentsView extends LoadingView {

    void showApartments(@NonNull ApartmensResponse apartmensResponse);

    void showError();
}

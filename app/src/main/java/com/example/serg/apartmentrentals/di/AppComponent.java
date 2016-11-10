package com.example.serg.apartmentrentals.di;

import com.example.serg.apartmentrentals.ApartmentsActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by sereg on 08.11.2016.
 */

@Singleton
@Component(modules = {NetworkModule.class})
public interface AppComponent {
    void injectApartmentActivity(ApartmentsActivity activity);
}

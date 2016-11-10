package com.example.serg.apartmentrentals;

import android.app.Application;

import com.example.serg.apartmentrentals.di.AppComponent;
import com.example.serg.apartmentrentals.di.DaggerAppComponent;
import com.example.serg.apartmentrentals.di.NetworkModule;

/**
 * Created by sereg on 08.11.2016.
 */

public class App extends Application {

    private static AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        component = DaggerAppComponent.builder()
                .networkModule(new NetworkModule())
                .build();
    }

    public static AppComponent getComponent() {
        return component;
    }
}

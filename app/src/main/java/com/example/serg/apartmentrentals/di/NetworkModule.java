package com.example.serg.apartmentrentals.di;

import android.support.annotation.NonNull;

import com.example.serg.apartmentrentals.api.ApartmentsService;
import com.example.serg.apartmentrentals.api.Constants;
import com.example.serg.apartmentrentals.api.LoggingInterceptor;

import java.io.IOException;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sereg on 08.11.2016.
 */

@Module
public class NetworkModule {

    @Provides
    @Singleton
    public ApartmentsService provideApartmentsService(@NonNull Retrofit retrofit) {
        return retrofit.create(ApartmentsService.class);
    }

    @Provides
    @Singleton
    public Retrofit provideRetrofit(@NonNull OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public OkHttpClient provideHttpClient() {
        return new OkHttpClient.Builder()
                .addInterceptor(chain -> {
                    Request.Builder requestBuilder = chain.request().newBuilder();
                    requestBuilder.header("Content-Type", "application/json");
                    requestBuilder.header("Accept", "application/json");
                    return chain.proceed(requestBuilder.build());
                })
                .addInterceptor(LoggingInterceptor.create())
                .build();
    }
}

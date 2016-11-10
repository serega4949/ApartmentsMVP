package com.example.serg.apartmentrentals.api;

import android.support.annotation.NonNull;

import com.example.serg.apartmentrentals.BuildConfig;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by sereg on 05.11.2016.
 */
public class LoggingInterceptor implements Interceptor {
    private final Interceptor mLoggingInterceptor;

    private LoggingInterceptor() {
        mLoggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
    }

    @NonNull
    public static Interceptor create() {
        return new LoggingInterceptor();
    }

    @Override
    public Response intercept(Interceptor.Chain chain) throws IOException {
        return mLoggingInterceptor.intercept(chain);
    }

}

package com.example.serg.apartmentrentals.utils;

import android.support.annotation.NonNull;

import rx.Observable;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by sereg on 05.11.2016.
 */
public class RxUtils {
    private RxUtils() {
    }

    @NonNull
    public static <T> Observable.Transformer<T, T> async() {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread());
            }
        };
    }

    @NonNull
    public static <T> Observable.Transformer<T, T> async(@NonNull final Scheduler background,
                                                         @NonNull final Scheduler main) {
        return new Observable.Transformer<T, T>() {
            @Override
            public Observable<T> call(Observable<T> observable) {
                return observable
                        .subscribeOn(background)
                        .observeOn(main);
            }
        };
    }
}

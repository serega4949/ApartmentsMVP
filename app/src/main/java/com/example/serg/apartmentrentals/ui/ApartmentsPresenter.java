package com.example.serg.apartmentrentals.ui;

import android.support.annotation.NonNull;

import com.example.serg.apartmentrentals.api.ApartmentsService;
import com.example.serg.apartmentrentals.ui.ApartmentsView;
import com.example.serg.apartmentrentals.utils.RxUtils;

import rx.Subscription;

/**
 * Created by sereg on 05.11.2016.
 */
public class ApartmentsPresenter {

    private ApartmentsView view;

    @NonNull
    private Subscription subscription;

    @NonNull
    private final ApartmentsService apartmentsService;

    public ApartmentsPresenter(@NonNull ApartmentsView apartmentsView, ApartmentsService apartmentsService) {
        this.view = apartmentsView;
        this.apartmentsService = apartmentsService;
    }

    public void getApartments(int page) {
        subscription = apartmentsService
                .getApartments(page)
                .cache()
                .doOnSubscribe(view::showLoading)
                .doOnTerminate(view::hideLoading)
                .compose(RxUtils.async())
                .subscribe(view::showApartments, throwable -> view.showError());
    }

    public void unsubscribeUpdating() {
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }
}

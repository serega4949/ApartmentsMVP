package com.example.serg.apartmentrentals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import com.example.serg.apartmentrentals.api.ApartmentsService;
import com.example.serg.apartmentrentals.responses.ApartmensResponse;
import com.example.serg.apartmentrentals.utils.NetworkUtils;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ApartmentsActivity extends AppCompatActivity implements ApartmentsView, ErrorFragment.ErrorFragmentListener {

    public static final String APARTMENTS_LIST_FRAGMENT = "ApartmentsListFragment";

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    ApartmentsService apartmentsService;

    private ApartmentsPresenter presenter;

    private Fragment currentFragment;

    private LoadingDialog loadingDialog;


    public static void start(@NonNull Activity activity) {
        Intent intent = new Intent(activity, ApartmentsActivity.class);
        activity.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        App.getComponent().injectApartmentActivity(this);
        presenter = new ApartmentsPresenter(this, apartmentsService);

        if (savedInstanceState == null) {
            currentFragment = ApartmentsListFragment.newInstance(presenter);
            replaceFragment(currentFragment, ApartmentsListFragment.class.getName());
        } else {
            currentFragment = getSupportFragmentManager().getFragment(savedInstanceState, APARTMENTS_LIST_FRAGMENT);
            if (currentFragment instanceof ApartmentsListFragment) {
                ((ApartmentsListFragment) currentFragment).setApartmentsPresenter(presenter);
            }
        }
    }

    private void replaceFragment(Fragment fragment, String tag) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.containerFragment, fragment, tag)
                .commit();
    }

    @Override
    protected void onPause() {
        presenter.unsubscribeUpdating();
        super.onPause();
    }

    @Override
    public void showApartments(@NonNull ApartmensResponse apartmensResponse) {
        Log.e("mylog", "showApartments -> size == " + apartmensResponse.getApartments().size());
        ((ApartmentsListFragment) currentFragment).updateData(apartmensResponse);

    }

    @Override
    public void showError() {
        Log.e("mylog", "showError");
        if (currentFragment instanceof ApartmentsListFragment) {
            if (((ApartmentsListFragment) currentFragment).getApartmentsAdapter().getItemCount() == 0) {
                ((ApartmentsListFragment) currentFragment).cleanupRetainInstanceFragment();
                currentFragment = ErrorFragment.newInstance();
                replaceFragment(currentFragment, ErrorFragment.class.getName());
            } else {
                Snackbar.make(getWindow().getDecorView().getRootView(), R.string.text_warning_dont_have_internet, Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void showLoading() {
        Log.e("mylog", "showLoading");
        loadingDialog = LoadingDialog.startLoading(getSupportFragmentManager(), getString(R.string.title_dialog_loading));
    }

    @Override
    public void hideLoading() {
        Log.e("mylog", "hideLoading");
        loadingDialog.stopLoading();
        if (currentFragment instanceof ApartmentsListFragment) {
            ((ApartmentsListFragment) currentFragment).stopLoading();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (currentFragment != null) {
            getSupportFragmentManager().putFragment(outState, APARTMENTS_LIST_FRAGMENT, currentFragment);
        }
    }

    @Override
    public void onReload() {
        if(NetworkUtils.isOnline(this)) {
            currentFragment = ApartmentsListFragment.newInstance(presenter);
            replaceFragment(currentFragment, ApartmentsListFragment.class.getName());
        }
    }
}

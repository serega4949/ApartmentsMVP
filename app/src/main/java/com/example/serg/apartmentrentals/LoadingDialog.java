package com.example.serg.apartmentrentals;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LoadingDialog extends DialogFragment {

    @BindView(R.id.titleWaitDialog)
    TextView titleWaitDialog;

    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public static LoadingDialog startLoading(FragmentManager fragmentManager, String title) {
        LoadingDialog loadingDialog = new LoadingDialog();
        loadingDialog.setTitle(title);
        loadingDialog.show(fragmentManager, LoadingDialog.class.getName());
        return loadingDialog;
    }

    public void stopLoading() {
            dismiss();
    }


    public LoadingDialog() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        if (getDialog() == null) {
            // Returns mDialog
            // Tells DialogFragment to not use the fragment as a dialog, and so won't try to use mDialog
            setShowsDialog(false);
        }
        try {
            super.onActivityCreated(savedInstanceState);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        View view = inflater.inflate(R.layout.fragment_loading_dialog, container, false);
        ButterKnife.bind(this, view);
        titleWaitDialog.setText(title);
        return view;
    }
}

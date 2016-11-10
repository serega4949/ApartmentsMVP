package com.example.serg.apartmentrentals.ui.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.serg.apartmentrentals.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ErrorFragment extends Fragment {

    @BindView(R.id.btnReload)
    Button btnReload;

    private ErrorFragmentListener listener;

    public static ErrorFragment newInstance() {
        return new ErrorFragment();
    }

    public ErrorFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_error_dialog, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @OnClick(R.id.btnReload)
    public void onReload() {
        listener.onReload();
    }

    public interface ErrorFragmentListener {
        void onReload();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof ErrorFragmentListener) {
            listener = (ErrorFragmentListener) context;
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        listener = null;
    }
}

package com.example.serg.apartmentrentals.ui.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.serg.apartmentrentals.ui.ApartmentsPresenter;
import com.example.serg.apartmentrentals.R;
import com.example.serg.apartmentrentals.adapters.ApartmentsAdapter;
import com.example.serg.apartmentrentals.responses.ApartmensResponse;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


public class ApartmentsListFragment extends Fragment {

    @BindView(R.id.listApartments)
    RecyclerView rvApartments;

    private ApartmentsAdapter apartmentsAdapter;
    private ApartmentsPresenter presenter;
    private boolean isLoading = false;

    public void setApartmentsPresenter(ApartmentsPresenter apartmentsPresenter) {
        this.presenter = apartmentsPresenter;
    }

    public void stopLoading() {
        isLoading = false;
    }

    public ApartmentsAdapter getApartmentsAdapter() {
        return apartmentsAdapter;
    }

    public ApartmentsListFragment() {
        // Required empty public constructor
    }

    public static ApartmentsListFragment newInstance(ApartmentsPresenter apartmentsPresenter) {
        ApartmentsListFragment fragment = new ApartmentsListFragment();
        fragment.setApartmentsPresenter(apartmentsPresenter);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        apartmentsAdapter = new ApartmentsAdapter(new ArrayList<>());
        presenter.getApartments(1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View viev = inflater.inflate(R.layout.fragment_apartments_list, container, false);
        ButterKnife.bind(this, viev);

        rvApartments.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        rvApartments.setAdapter(apartmentsAdapter);
        rvApartments.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (apartmentsAdapter.getItemCount() > 0 & apartmentsAdapter.getPage() != null) {
                    int position = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                    int updatePosition = apartmentsAdapter.getItemCount()-1;
                    if ((position == updatePosition & !isLoading)
                            & apartmentsAdapter.getPage().getCurrent() != apartmentsAdapter.getPage().getLast()) {
                        isLoading = true;
                        presenter.getApartments(apartmentsAdapter.getPage().getNext());
                    }
                }
            }
        });
        return viev;
    }

    public void updateData(ApartmensResponse apartmensResponse) {
        apartmentsAdapter.addApartmetns(apartmensResponse.getApartments());
        apartmentsAdapter.setPage(apartmensResponse.getPage());
    }

    public void cleanupRetainInstanceFragment() {
        FragmentManager fm = getFragmentManager();
        fm.beginTransaction().remove(this).commit();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}

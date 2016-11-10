package com.example.serg.apartmentrentals.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.serg.apartmentrentals.R;
import com.example.serg.apartmentrentals.models.Apartment;
import com.example.serg.apartmentrentals.models.Page;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by sereg on 05.11.2016.
 */
public class ApartmentsAdapter extends RecyclerView.Adapter<ApartmentsAdapter.ViewHolder> {

    private List<Apartment> apartments;
    private Page page;

    private static Context context;

    public ApartmentsAdapter(List<Apartment> apartments) {
        this.apartments = apartments;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Page getPage() {
        return page;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_list_apartment, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(apartments.get(position));
    }


    @Override
    public int getItemCount() {
        return apartments.size();
    }

    public void addApartmetns(List<Apartment> apartmentList) {
        apartments.addAll(apartmentList);
        notifyDataSetChanged();
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.ivPhotoApartment)
        ImageView ivPhotoApartment;

        @BindView(R.id.priceUSD)
        TextView priceUSD;

        @BindView(R.id.priceBYN)
        TextView priceBYN;

        @BindView(R.id.priceBYR)
        TextView priceBYR;

        @BindView(R.id.countRooms)
        TextView countRooms;

        @BindView(R.id.address)
        TextView address;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Apartment apartment) {
            Picasso.with(context)
                    .load(apartment.getPhoto())
                    .into(ivPhotoApartment);

            priceUSD.setText(String.format("%d$", (int)Double.parseDouble(apartment.getPrice().getConverted().getUSD().getAmount())));
            priceBYN.setText(String.format("%s р.", apartment.getPrice().getConverted().getBYN().getAmount()));
            priceBYR.setText(String.format("%s р.", apartment.getPrice().getConverted().getBYR().getAmount()));

            countRooms.setText(String.format("%s", apartment.getRent_type()));
            address.setText(apartment.getLocation().getUser_address());

        }
    }
}

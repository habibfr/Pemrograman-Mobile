package com.habibfr.gojek_clone;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RestoAdapter extends RecyclerView.Adapter<RestoAdapter.ViewHolder> {

    Context context;
    ArrayList<Resto> restos;

    public RestoAdapter(Context context, ArrayList<Resto> restos) {
        this.context = context;
        this.restos = restos;
    }

    @NonNull
    @Override
    public RestoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_resto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RestoAdapter.ViewHolder holder, int position) {
        Resto currentResto = restos.get(position);
        holder.imgResto.setImageResource(currentResto.getCover());
        holder.titleResto.setText(currentResto.getTitle());
        holder.jenisResto.setText(currentResto.getJenis());
        holder.ratingResto.setText(currentResto.getRating());
    }

    @Override
    public int getItemCount() {
        return restos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgResto;
        TextView titleResto, jenisResto, ratingResto;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgResto = itemView.findViewById(R.id.imgResto);
            titleResto = itemView.findViewById(R.id.txtTitleResto);
            jenisResto = itemView.findViewById(R.id.txtJenisResto);
            ratingResto = itemView.findViewById(R.id.txtRating);
        }
    }
}

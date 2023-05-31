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
public class CardImageAdapter extends RecyclerView.Adapter<CardImageAdapter.ViewHolder> {

    Context context;
    ArrayList<CardImage> cardImageList;

    public CardImageAdapter(Context context, ArrayList<CardImage> cardImageList) {
        this.context = context;
        this.cardImageList = cardImageList;
    }


    @NonNull
    @Override
    public CardImageAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_image_card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardImageAdapter.ViewHolder holder, int position) {
        CardImage current = (CardImage) cardImageList.get(position);
        holder.ivBanner.setImageResource(current.getBanner());
        holder.txtTitle.setText(current.getTitle());
        holder.txtDesc.setText(current.getDesc());
    }

    @Override
    public int getItemCount() {
        return cardImageList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivBanner;
        TextView txtTitle;
        TextView txtDesc;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBanner = itemView.findViewById(R.id.ivBanner);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDesc = itemView.findViewById(R.id.txtDesc);
        }
    }


}

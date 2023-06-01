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


public class FilterAdapter extends RecyclerView.Adapter<FilterAdapter.ViewHolder> {

    Context context;
    ArrayList<Filter> filters;

    public FilterAdapter(Context context, ArrayList<Filter> filters) {
        this.context = context;
        this.filters = filters;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Filter currentFilter = filters.get(position);
        holder.imgIcon.setImageResource(currentFilter.getIcon());
        holder.txtName.setText(currentFilter.getName());
    }

    @Override
    public int getItemCount() {
        return filters.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgIcon;
        TextView txtName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgIcon = itemView.findViewById(R.id.imgIcon);
            txtName = itemView.findViewById(R.id.txtName);
        }
    }
}

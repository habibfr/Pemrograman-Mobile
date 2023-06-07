package com.habibfr.asynctask;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class OrderAdapter extends BaseAdapter {
    Context context;
    ArrayList<Order> items;


    public OrderAdapter(Context context, ArrayList<Order> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(items.get(i).getId());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(context).inflate(R.layout.item_order, viewGroup, false);
        Order currentItem = (Order) getItem(i);
        TextView txtId = view.findViewById(R.id.txtId);
        TextView txtName = view.findViewById(R.id.txtName);

        txtId.setText(currentItem.getId());
        txtName.setText(currentItem.getName());

        return view;
    }
}

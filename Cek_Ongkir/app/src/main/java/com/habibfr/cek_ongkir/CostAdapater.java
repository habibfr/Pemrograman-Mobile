package com.habibfr.cek_ongkir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CostAdapater extends BaseAdapter {

    Context context;
    ArrayList<Cost> listCost;

    public CostAdapater(Context context, ArrayList<Cost> listCost) {
        this.context = context;
        this.listCost = listCost;
    }

    @Override
    public int getCount() {
        return listCost.size();
    }

    @Override
    public Object getItem(int i) {
        return listCost.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.cost_item, viewGroup, false);
        Cost currentItem = (Cost) getItem(i);
        TextView txtService = view.findViewById(R.id.txtService);
        TextView txtValue = view.findViewById(R.id.txtValue);

        txtService.setText(currentItem.getService());
        txtValue.setText(currentItem.getValue());
        return view;
    }


}

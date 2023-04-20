package com.lab_7_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BarangAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Barang> items;

    public BarangAdapter(Context context, ArrayList<Barang> items) {
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
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        }

        Barang currentItem = (Barang) getItem(i);

        TextView txtKodeBarang = (TextView) view.findViewById(R.id.txtKodeBarang);
        TextView txtNamaBarang = (TextView) view.findViewById(R.id.txtNamaBarang);
        TextView txtHargaBarang = (TextView) view.findViewById(R.id.txtHargaBarang);

        txtKodeBarang.setText(currentItem.getId());
        txtNamaBarang.setText(currentItem.getNama());
        txtHargaBarang.setText("Rp ." + currentItem.getHarga());

        return view;
    }
}

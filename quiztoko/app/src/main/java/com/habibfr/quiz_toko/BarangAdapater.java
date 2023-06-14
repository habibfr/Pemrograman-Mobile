package com.habibfr.quiz_toko;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class BarangAdapater extends BaseAdapter {
    Context context;
    ArrayList<Barang> barangs;

    public BarangAdapater(Context context, ArrayList<Barang> barangs) {
        this.context = context;
        this.barangs = barangs;
    }

    @Override
    public int getCount() {
        return barangs.size();
    }

    @Override
    public Object getItem(int i) {
        return barangs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(barangs.get(i).getId());
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_barang, viewGroup, false);

        Barang currentBarang = (Barang) getItem(i);
        TextView txtNumber = view.findViewById(R.id.txtNumber);
        TextView txtNama = view.findViewById(R.id.txtNama);
        TextView txtStok = view.findViewById(R.id.txtStok);
        TextView txtHarga = view.findViewById(R.id.txtHarga);

        txtNumber.setText(String.valueOf(i + 1));
        txtNama.setText(currentBarang.getNama());
        txtStok.setText(currentBarang.getStok());
        txtHarga.setText(currentBarang.getHarga());
        return view;
    }
}

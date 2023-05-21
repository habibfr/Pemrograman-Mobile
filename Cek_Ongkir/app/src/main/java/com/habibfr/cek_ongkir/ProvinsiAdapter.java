package com.habibfr.cek_ongkir;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class ProvinsiAdapter extends BaseAdapter {
    Context context;
    ArrayList<Provinsi> listProvinsi;

    public ProvinsiAdapter() {
    }

    public ProvinsiAdapter(Context context, ArrayList<Provinsi> listProvinsi) {
        this.context = context;
        this.listProvinsi = listProvinsi;
    }

    @Override
    public int getCount() {
        return listProvinsi.size();
    }

    @Override
    public Object getItem(int i) {
        return listProvinsi.get(i);
    }

    @Override
    public long getItemId(int i) {
        return Long.parseLong(listProvinsi.get(i).province_id);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.list_provinsi, viewGroup, false);
        Provinsi currentItem = (Provinsi) getItem(i);
        TextView txtIdProvinsi = view.findViewById(R.id.txtIdProvinsi);
        TextView txtProvinsi = view.findViewById(R.id.txtProvinsi);

        txtIdProvinsi.setText(currentItem.getProvince_id());
        txtIdProvinsi.setText(currentItem.getProvince());
        return view;
    }

}

package com.uts_pbm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {

    private ArrayList<DataTrx> listData;
    private Context context;

    public CustomAdapter(ArrayList<DataTrx> listData, Context context) {
        this.listData = listData;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int i) {
        return listData.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.list_item, viewGroup, false);
        DataTrx currentItem = (DataTrx) getItem(i);
        TextView txtTanggal = view.findViewById(R.id.txtTgl);
        TextView txtUraian = view.findViewById(R.id.txtUraian);
        TextView txtHarga = view.findViewById(R.id.txtHarga);
        TextView txtJenis = view.findViewById(R.id.txtJenis);

        txtTanggal.setText(currentItem.getTangal());
        txtUraian.setText(currentItem.getUraian());
        txtHarga.setText(String.valueOf(currentItem.getHarga()));
        txtJenis.setText(currentItem.getJenis());


        Button btnDelete = view.findViewById(R.id.hapus);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listData.remove(i);
                notifyDataSetChanged();
            }
        });

        return view;
    }
}

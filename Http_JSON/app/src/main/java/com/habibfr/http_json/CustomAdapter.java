package com.habibfr.http_json;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class CustomAdapter extends BaseAdapter {

    Context context;
    ArrayList<DataItem> items;


    public CustomAdapter(Context context, ArrayList<DataItem> items) {
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
        return Long.parseLong(items.get(i).id);
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        view = LayoutInflater.from(context).inflate(R.layout.data_item, viewGroup, false);
        DataItem currentItem = (DataItem) getItem(i);
        TextView txtId = view.findViewById(R.id.txtId);
        TextView txtItem = view.findViewById(R.id.txtItem);
//        TextView txtTanggal = view.findViewById(R.id.txtTgl);
//        TextView txtUraian = view.findViewById(R.id.txtUraian);
//        TextView txtHarga = view.findViewById(R.id.txtHarga);

        txtId.setText(currentItem.getId());
        txtItem.setText(currentItem.getItem());



//        btnDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listTransaksi.remove(i);
//                Toast.makeText(view.getContext(), "Berhasil menghapus data!",
//                        Toast.LENGTH_LONG).show();
//                notifyDataSetChanged();
//            }
//        });

        return view;
    }
}

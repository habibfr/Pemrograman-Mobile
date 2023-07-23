package com.habibfr.aplikasi_sqlite.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.habibfr.aplikasi_sqlite.R;
import com.habibfr.aplikasi_sqlite.model.Data;

import java.util.List;

public class Adapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Data> items;

    public Adapter(Activity activity, List<Data> items) {
        this.activity = activity;
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
        return Integer.parseInt(items.get(i).getId());
        // 19
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(view == null){
            view = inflater.inflate(R.layout.list_row, null);
        }

        TextView id = (TextView) view.findViewById(R.id.txtId);
        TextView name = (TextView) view.findViewById(R.id.txtName);
        TextView address = (TextView) view.findViewById(R.id.txtAddress);

        Data data = items.get(i);

        id.setText(data.getId());
        name.setText(data.getName());
        address.setText(data.getAddress());

        return view;
    }
}

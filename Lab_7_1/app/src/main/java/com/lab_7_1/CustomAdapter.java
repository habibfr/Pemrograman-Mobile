package com.lab_7_1;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private int[] logos;
    private LayoutInflater layoutInflater;

    public CustomAdapter(Context context, int[] logos) {
        this.context = context;
        this.logos = logos;
        this.layoutInflater = (layoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return logos.length;
    }

    @Override
    public Object getItem(int i) {
        return logos[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.grid_view_item, null);
        ImageView icon = view.findViewById(R.id.icon);
        icon.setImageResource(logos[i]);
        return view;
    }
}

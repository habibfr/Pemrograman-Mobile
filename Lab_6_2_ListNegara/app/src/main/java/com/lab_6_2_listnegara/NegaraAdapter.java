package com.lab_6_2_listnegara;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class NegaraAdapter extends BaseAdapter {

    Context context;
    String negaraList[];
    int benderaList[];
    LayoutInflater inflater;


    public NegaraAdapter(Context context, String[] negaraList, int[] benderaList) {
        this.context = context;
        this.negaraList = negaraList;
        this.benderaList = benderaList;
        inflater = (LayoutInflater.from(context));
    }

    @Override
    public int getCount() {
        return benderaList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflater.inflate(R.layout.list_item,null);
        TextView txtNamaNegara = (TextView) view.findViewById(R.id.txtNamaNegara);
        ImageView imgBendera = (ImageView) view.findViewById(R.id.imgBendera);
        txtNamaNegara.setText(negaraList[i]);
        imgBendera.setImageResource(benderaList[i]);
        return view;
    }
}

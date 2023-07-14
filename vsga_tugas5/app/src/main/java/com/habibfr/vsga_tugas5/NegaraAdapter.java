package com.habibfr.vsga_tugas5;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class NegaraAdapter extends BaseAdapter {
    Context context;
    ArrayList<Negara> negaras;

    public NegaraAdapter(Context context, ArrayList<Negara> negaras) {
        this.context = context;
        this.negaras = negaras;
    }

    @Override
    public int getCount() {
        return negaras.size();
    }

    @Override
    public Object getItem(int i) {
        return negaras.get(i);
    }

    @Override
    public long getItemId(int i) {
        return negaras.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.item_negara, viewGroup, false);
        Negara currentNegara = (Negara) getItem(i);

        TextView txtNamaNegara = view.findViewById(R.id.txtNamaNegara);
        txtNamaNegara.setText(currentNegara.getNama());

        return view;
    }
}

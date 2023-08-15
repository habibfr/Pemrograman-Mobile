package com.habibfr.data_mhs_jmp.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.habibfr.data_mhs_jmp.R;
import com.habibfr.data_mhs_jmp.model.Mahasiswa;

import java.util.List;

public class MahasiswaAdapter extends BaseAdapter {

    private Activity activity;
    private LayoutInflater inflater;
    private List<Mahasiswa> mahasiswas;

    public MahasiswaAdapter(Activity activity, List<Mahasiswa> mahasiswas) {
        this.activity = activity;
        this.mahasiswas = mahasiswas;
    }

    @Override
    public int getCount() {
        return mahasiswas.size();
    }

    @Override
    public Object getItem(int i) {
        return mahasiswas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return mahasiswas.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(inflater == null){
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        if(view == null){
            view = inflater.inflate(R.layout.list_mahasiswa, null);
        }

        TextView id = (TextView) view.findViewById(R.id.idMhs);
        TextView name = (TextView) view.findViewById(R.id.txtNamaMhs);


        Mahasiswa data = mahasiswas.get(i);

        id.setText(String.valueOf(data.getId()));
        name.setText(data.getNama());
        return view;
    }
}

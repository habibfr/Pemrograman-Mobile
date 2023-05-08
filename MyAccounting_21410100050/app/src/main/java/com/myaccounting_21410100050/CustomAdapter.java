package com.myaccounting_21410100050;

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

    private ArrayList<DataTrx> listTransaksi;
    private Context context;

    public CustomAdapter(ArrayList<DataTrx> listTransaksi, Context context) {
        this.listTransaksi = listTransaksi;
        this.context = context;
    }

    @Override
    public int getCount() {
        return listTransaksi.size();
    }

    @Override
    public Object getItem(int i) {
        return listTransaksi.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = LayoutInflater.from(context).inflate(R.layout.list_transaksi, viewGroup, false);
        DataTrx currentItem = (DataTrx) getItem(i);
        TextView txtRound = view.findViewById(R.id.txtRound);
        TextView txtTanggal = view.findViewById(R.id.txtTgl);
        TextView txtUraian = view.findViewById(R.id.txtUraian);
        TextView txtHarga = view.findViewById(R.id.txtHarga);

        Date date = currentItem.getTglTransaksi();
        DateFormat dateFormat = new SimpleDateFormat("E, dd MMMM yyyy");
        String strDate = dateFormat.format(date);

        txtRound.setText("A");
        txtTanggal.setText(strDate);
        txtUraian.setText(currentItem.getUraian());

        if(currentItem.getDebit() == 0){
            txtRound.setText("K");
            txtHarga.setText(String.valueOf(currentItem.getKredit()));
        }else{
            txtRound.setText("D");
            txtHarga.setText(String.valueOf(currentItem.getDebit()));
        }

        Button btnDelete = view.findViewById(R.id.hapus);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listTransaksi.remove(i);
                Toast.makeText(view.getContext(), "Berhasil menghapus data!",
                        Toast.LENGTH_LONG).show();
                notifyDataSetChanged();
            }
        });

        return view;
    }
}

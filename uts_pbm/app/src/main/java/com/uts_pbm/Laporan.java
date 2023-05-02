package com.uts_pbm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.io.Serializable;

public class Laporan extends AppCompatActivity {

    TextView txtPemasukan, txtPengeluaran, txtSaldo;
    Button btnBack;
    private Integer pemasukan = 0, pengeluaran = 0, saldo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan);
        ArrayList<DataTrx> trx = (ArrayList<DataTrx>)getIntent().getSerializableExtra("trx");

         txtPengeluaran =(TextView) findViewById(R.id.txtPengeluaran);
         txtPemasukan =(TextView) findViewById(R.id.txtPemasukan);
         txtSaldo =(TextView) findViewById(R.id.txtSaldo);
        btnBack = (Button) findViewById(R.id.btnKembali);

//        System.out.println(mylist);
//
//        laporan.setText(mylist.get(0).getJenis());

        for (int i = 0; i < trx.size(); i++) {
            if(trx.get(i).getJenis().equalsIgnoreCase("Kredit")){
                pengeluaran += trx.get(i).getHarga();
            }else {
                pemasukan += trx.get(i).getHarga();
            }
        }

        saldo = pemasukan - pengeluaran;

        txtPemasukan.setText("Pemasukan : " + pemasukan.toString());
        txtPengeluaran.setText("Pengeluaran : " + pengeluaran.toString());
        txtSaldo.setText("Saldo Akhir : " + saldo);

        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });


    }
}
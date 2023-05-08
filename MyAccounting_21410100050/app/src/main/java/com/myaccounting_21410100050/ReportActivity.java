package com.myaccounting_21410100050;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class ReportActivity extends AppCompatActivity {
    TextView txtPemasukan, txtPengeluaran, txtSaldo;
    Button btnBack;
    private Integer pemasukan = 0, pengeluaran = 0, saldo = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);

        ArrayList<DataTrx> trx = (ArrayList<DataTrx>)getIntent().getSerializableExtra("trx");

        txtPengeluaran =(TextView) findViewById(R.id.txtKredit);
        txtPemasukan =(TextView) findViewById(R.id.txtDebit);
        txtSaldo =(TextView) findViewById(R.id.txtSaldo);
        btnBack = (Button) findViewById(R.id.btnKembali);

        for (int i = 0; i < trx.size(); i++) {
          pemasukan += trx.get(i).getDebit();
          pengeluaran += trx.get(i).getKredit();
        }

        saldo = pemasukan - pengeluaran;

        txtPemasukan.setText("Rp." + pemasukan.toString());
        txtPengeluaran.setText("Rp." + pengeluaran.toString());
        txtSaldo.setText("Rp." + saldo.toString());

        btnBack.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
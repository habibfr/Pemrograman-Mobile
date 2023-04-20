package com.uts_pbm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String[] options = new String[]{"Debit", "Kredit"};
    ListView lvTrx;
    EditText editHarga, editUraian, editTgl;
    Spinner editJenis;
    ArrayList<DataTrx> listTrx;

    Button btnTambah;
    Button hapus;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        btnTambah = findViewById(R.id.btnTambah);
//        hapus = findViewById(R);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        editTgl = findViewById(R.id.editTgl);
        editUraian = findViewById(R.id.editUraian);
        editHarga = findViewById(R.id.editHarga);

        listTrx = new ArrayList<DataTrx>();

        customAdapter = new CustomAdapter(listTrx, this);
        lvTrx = findViewById(R.id.lvTrx);
        lvTrx.setAdapter(customAdapter);

        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // on below line we are getting text from edit text
                String tgl = editTgl.getText().toString();
                String uraian = editUraian.getText().toString();
                int harga = Integer.parseInt(editHarga.getText().toString());
                String jenis = spinner.getSelectedItem().toString();

                // on below line we are checking if item is not empty
                if (!tgl.isEmpty() && !uraian.isEmpty()) {

                    // on below line we are adding item to our list.
                    listTrx.add(new DataTrx(tgl, uraian, jenis, harga));

                    // on below line we are notifying adapter
                    // that data in list is updated to
                    // update our list view.
                    customAdapter.notifyDataSetChanged();
                }
            }
        });
    }
}
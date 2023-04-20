package com.lab_7_2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<Barang> listsBarang;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        isiListBarang();

        BarangAdapter barangAdapter = new BarangAdapter(this, listsBarang);
        listView = findViewById(R.id.lvBarang);

        listView.setAdapter(barangAdapter);
    }

    private void isiListBarang(){
        listsBarang = new ArrayList<Barang>();

        listsBarang.add(new Barang("M001", "Buku", 4000));
        listsBarang.add(new Barang("M002", "Bolpoin", 3000));
        listsBarang.add(new Barang("M003", "Pensil", 2000));
        listsBarang.add(new Barang("M004", "Buku Gambar", 5000));
        listsBarang.add(new Barang("M005", "Penghapus", 1500));
        listsBarang.add(new Barang("M006", "Penggaris", 2500));
    }
}
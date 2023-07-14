package com.habibfr.vsga_tugas5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListviewActivity extends AppCompatActivity {

    ListView lvNegara;
    ArrayList<Negara> negaras = new ArrayList<>();
    NegaraAdapter negaraAdapter;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        lvNegara = findViewById(R.id.lvNegara);
        btnBack = findViewById(R.id.btnKembaliFromLv);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListviewActivity.this, KalkulatorActivity.class);

                startActivity(intent);
                finish();
            }
        });

        addNegara();

        negaraAdapter = new NegaraAdapter(ListviewActivity.this, negaras);
        negaraAdapter.notifyDataSetChanged();
        lvNegara.setAdapter(negaraAdapter);


    }

    public void addNegara() {
        negaras.add(new Negara(1, "Indonesia"));
        negaras.add(new Negara(2, "Jepang"));
        negaras.add(new Negara(3, "Malaysia"));
        negaras.add(new Negara(4, "Singapura"));
        negaras.add(new Negara(5, "England"));
        negaras.add(new Negara(6, "USA"));
        negaras.add(new Negara(7, "Belanda"));
        negaras.add(new Negara(7, "UK"));
        negaras.add(new Negara(7, "Jerman"));
    }
}
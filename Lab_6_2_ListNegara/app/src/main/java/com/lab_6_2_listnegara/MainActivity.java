package com.lab_6_2_listnegara;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    String[] namaNegara = {"Indonesia", "Mesir", "Spanyol", "England", "Jepang", "Korea Selatan", "Malaysia", "Belanda", "Portugal", "Amerika Serikat"};
    int[] imgBendera = {R.drawable.id, R.drawable.eg, R.drawable.es, R.drawable.gb, R.drawable.jp, R.drawable.kr, R.drawable.my, R.drawable.nl, R.drawable.pt, R.drawable.us};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.lVNegara);
        NegaraAdapter negaraAdapter = new NegaraAdapter(getApplicationContext(), namaNegara, imgBendera);
        listView.setAdapter(negaraAdapter);
    }
}
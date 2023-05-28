package com.habibfr.gojek_clone;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.GridView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationBarView.OnItemSelectedListener {
    BottomNavigationView bottomNavigationView;
    FragmentBeranda fragmentBeranda = new FragmentBeranda();
    FragmentPesanan fragmentPesanan = new FragmentPesanan();
    FragmentChat fragmentChat = new FragmentChat();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        bottomNavigationView.setOnItemSelectedListener(this);
        bottomNavigationView.setSelectedItemId(R.id.beranda);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        switch (item.getItemId()) {
            case R.id.beranda:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragmentBeranda).commit();
                return true;
            case R.id.pesanan:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragmentPesanan).commit();
                return true;
            case R.id.chat:
                getSupportFragmentManager().beginTransaction().replace(R.id.flFragment, fragmentChat).commit();
                return true;
        }
        return false;
    }


}
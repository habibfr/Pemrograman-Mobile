package com.habibfr.data_mhs_jmp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    TextView txtAbout, txtAuthor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Tentang Aplikasi");

        txtAbout = findViewById(R.id.txtAbout);
        txtAuthor = findViewById(R.id.txtAuthor);
        String about = "Aplikasi Kampusku adalah apalikasi yang digunakan untuk melakukan management data mahasiswa yaitu tambah, edit, dan hapus data mahasiswa";
        txtAbout.setText(about);

        txtAuthor.setText("by Habib Fatkhul Rohman");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }

}
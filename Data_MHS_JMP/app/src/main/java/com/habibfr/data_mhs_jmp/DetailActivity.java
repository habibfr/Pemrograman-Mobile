package com.habibfr.data_mhs_jmp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.habibfr.data_mhs_jmp.model.Mahasiswa;

import java.util.Objects;

public class DetailActivity extends AppCompatActivity {
    EditText viewId, viewNomor, viewNama, viewTanggal, viewJenisKelamin, viewAlamat;
    Button btnKembali;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setTitle("Detail Mahasiswa");

        viewId = findViewById(R.id.viewEditId);
        viewNomor = findViewById(R.id.viewEditNomor);
        viewNama = findViewById(R.id.viewEditNama);
        viewTanggal = findViewById(R.id.viewEditTanggal);
        viewJenisKelamin = findViewById(R.id.viewEditJenisKelamin);
        viewAlamat = findViewById(R.id.viewEditAlamat);
        btnKembali = findViewById(R.id.btnOkeView);

        Intent intent = getIntent();
        Mahasiswa mhs = (Mahasiswa) intent.getSerializableExtra("mahasiswa");

        System.out.println(mhs.getJenisKelamin());
//        Toast.makeText(this, mhs.getNama(), Toast.LENGTH_SHORT).show();
        viewId.setText(String.valueOf(mhs.getId()));
        viewNomor.setText(mhs.getNomor());
        viewNama.setText(mhs.getNama());
        viewTanggal.setText(mhs.getTglLahir());
        viewJenisKelamin.setText(mhs.getJenisKelamin());
        viewAlamat.setText(mhs.getAlamat());

        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



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
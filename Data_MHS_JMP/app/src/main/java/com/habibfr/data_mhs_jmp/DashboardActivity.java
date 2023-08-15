package com.habibfr.data_mhs_jmp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TableRow;
import android.widget.Toast;

import java.io.File;

public class DashboardActivity extends AppCompatActivity {
    public static final String FILENAME = "login";
    Toolbar tbToolbar;
    TableRow btnLihatData, btnLogout, btnTambahData, btnInformasi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle("Dashboard");

//        tbToolbar = findViewById(R.id.tbToolbar);
//        setSupportActionBar(tbToolbar);
//        getSupportActionBar().setDisplayShowTitleEnabled(true);
//        getSupportActionBar().setTitle("Aplikasi Catatan Proyek 1");

        btnLihatData = findViewById(R.id.btnLihatData);
        btnLogout = findViewById(R.id.btnLogout);
        btnTambahData = findViewById(R.id.btnTambahData);
        btnInformasi = findViewById(R.id.btnInformasi);

        btnLihatData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, ViewActivity.class);
                startActivity(intent);
            }

        });

        btnTambahData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, InputActivity.class);
                startActivity(intent);
            }

        });

        btnInformasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DashboardActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tampilkanDialogKonfirmasiLogout();
            }
        });
    }


    void hapusFile() {
        File file = new File(getFilesDir(), FILENAME);
        if (file.exists()) {
            file.delete();
        }
    }

    void tampilkanDialogKonfirmasiLogout() {
        new AlertDialog.Builder(this).setTitle("Logout").setMessage("Apakah Anda yakin ingin Logout?").setIcon(android.R.drawable.ic_dialog_alert).setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int whichButton) {
                hapusFile();
                Intent intent = new Intent(DashboardActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }).setNegativeButton(android.R.string.no, null).show();
    }
}
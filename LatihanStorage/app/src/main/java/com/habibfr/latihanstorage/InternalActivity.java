package com.habibfr.latihanstorage;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class InternalActivity extends AppCompatActivity {

    public static final String FILENAME = "testfile.txt";
    Button btnBacaFile, btnUbahFile, btnBuatFile, btnHapusFile;
    TextView txtHasil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internal);

        btnBacaFile = findViewById(R.id.btnBacaFile);
        btnUbahFile = findViewById(R.id.btnUbahFile);
        btnBuatFile = findViewById(R.id.btnBuatFile);
        btnHapusFile = findViewById(R.id.btnHapusFile);
        txtHasil = findViewById(R.id.txtBaca);

        btnBuatFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String isiFile = "isi file pertama kali dibuat! \n Habib ganteng contohnya.";
                File file = new File(getFilesDir(), FILENAME);

                FileOutputStream outputStream = null;
                try {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    outputStream = new FileOutputStream(file, true);
                    outputStream.write(isiFile.getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                    outputStream.close();
                    txtHasil.setText("berhasil buat file");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        btnBacaFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // baca file
                File file = new File(getFilesDir(), FILENAME);

                if (!file.exists()) {
                    txtHasil.setText("file belum ada!");
                }

                StringBuilder stringBuilder = new StringBuilder();

                try {
                    FileInputStream inputStream = new FileInputStream(file);
                    InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                    String line = bufferedReader.readLine();
                    while (line != null) {
                        stringBuilder.append(line);
                        line = bufferedReader.readLine();
                    }

                    bufferedReader.close();
                    inputStreamReader.close();
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String isiFile = stringBuilder.toString();
                txtHasil.setText(isiFile);
            }
        });

        btnUbahFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // ubah file
                File file = new File(getFilesDir(), FILENAME);
                if (!file.exists()) {
                    txtHasil.setText("file belum ada!");
                }

                try {
                    FileOutputStream outputStream = new FileOutputStream(file);
                    String newContent = "Ini isi file baru yang sudah diubah. \nHabib Fatkhul Rohman ganteng pake bangettt.";
                    outputStream.write(newContent.getBytes(StandardCharsets.UTF_8));
                    outputStream.flush();
                    outputStream.close();
                    txtHasil.setText("Berhasil mengubah isi file.");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnHapusFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // hapus file
                File file = new File(getFilesDir(), FILENAME);

                if (file.exists()) {
                    file.delete();
                    txtHasil.setText("berhasil hapus");
                } else {
                    txtHasil.setText("file belum ada!");
                }

            }
        });
    }
}
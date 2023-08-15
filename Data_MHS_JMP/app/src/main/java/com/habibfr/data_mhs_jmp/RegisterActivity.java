package com.habibfr.data_mhs_jmp;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;

public class RegisterActivity extends AppCompatActivity {
    private EditText editNamaLengkapRegister, editEmailRegister, editPasswordRegister;
    private Button btnSimpanRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Registration");

        editNamaLengkapRegister = findViewById(R.id.editNamaLengkapRegister);
        editEmailRegister = findViewById(R.id.editEmailRegister);
        editPasswordRegister = findViewById(R.id.editPasswordRegister);
        btnSimpanRegister = findViewById(R.id.btnSimpanRegister);

        btnSimpanRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isValidation()) {
                    simpanFileData();
                } else {
                    Toast.makeText(RegisterActivity.this, "Mohon Lengkapi Seluruh Data", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    boolean isValidation() {
        if (editEmailRegister.getText().toString().equals("") || editPasswordRegister.getText().toString().equals("") || editNamaLengkapRegister.getText().toString().equals("")) {
            return false;
        } else {
            return true;
        }
    }

    void simpanFileData() {
        String isiFile = editNamaLengkapRegister.getText().toString() + ";" + editPasswordRegister.getText().toString() + ";" + editEmailRegister.getText().toString();
        File file = new File(getFilesDir(), editEmailRegister.getText().toString());
        FileOutputStream outputStream = null;
        try {
            file.createNewFile();
            outputStream = new FileOutputStream(file, false);
            outputStream.write(isiFile.getBytes());
            outputStream.flush();
            outputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        Toast.makeText(this, "Register Berhasil", Toast.LENGTH_SHORT).show();
        onBackPressed();
    }
}
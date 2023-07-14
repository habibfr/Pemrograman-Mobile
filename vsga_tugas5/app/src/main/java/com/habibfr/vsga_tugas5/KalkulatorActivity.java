package com.habibfr.vsga_tugas5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class KalkulatorActivity extends AppCompatActivity {
    EditText editFirstNumber, editSecondNumber;
    Button btnAdd, btnMin, btnKali, btnBagi, btnBack, btnNext, btnClearHasil;
    TextView txtMsgError, editHasil;
    double angka1 = 0, angka2 = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kalkulator);

        editFirstNumber = findViewById(R.id.editFirstNumber);
        editSecondNumber = findViewById(R.id.editSecondNumber);
        editHasil = (TextView) findViewById(R.id.editHasil);
        btnAdd = findViewById(R.id.btnAdd);
        btnMin = findViewById(R.id.btnMin);
        btnKali = findViewById(R.id.btnKali);
        btnBagi = findViewById(R.id.btnBagi);
        btnBack = findViewById(R.id.btnBack);
        btnKali = findViewById(R.id.btnKali);
        btnNext = findViewById(R.id.btnNext);
        txtMsgError = findViewById(R.id.txtMsgError);
        btnClearHasil = findViewById(R.id.btnClearHasil);
        editHasil.setFocusable(false);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KalkulatorActivity.this, ListviewActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(KalkulatorActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg(editFirstNumber.getText().toString(), editSecondNumber.getText().toString());
                Double hasil = angka1 + angka2;
                editHasil.setText(hasil.toString());
                reset();
            }
        });
        btnMin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                msg(editFirstNumber.getText().toString(), editSecondNumber.getText().toString());
                Double hasil = angka1 - angka2;
                editHasil.setText(hasil.toString());
                reset();
            }
        });
        btnKali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                msg(editFirstNumber.getText().toString(), editSecondNumber.getText().toString());
                Double hasil = angka1 * angka2;
                editHasil.setText(hasil.toString());
                reset();
            }
        });
        btnBagi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                msg(editFirstNumber.getText().toString(), editSecondNumber.getText().toString());
                if (angka2 != 0) {
                    double a = angka1;
                    double b = angka2;
                    Double hasil = Double.valueOf(a / b);
                    editHasil.setText(hasil.toString());
                    reset();
                } else {
                    txtMsgError.setText("angka dua tidak boleh 0!");
                    txtMsgError.setVisibility(View.VISIBLE);
                }
            }
        });

        btnClearHasil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clear();
            }
        });


    }

    public void reset() {
        editFirstNumber.setText("");
        editSecondNumber.setText("");
    }

    public void clear(){
        editHasil.setText("");
    }

    public void msg(String fNumber, String lNumber) {
        txtMsgError.setVisibility(View.GONE);
        if (fNumber.isEmpty() || lNumber.isEmpty()) {
            txtMsgError.setVisibility(View.VISIBLE);
            txtMsgError.setText("Angka harus diisi!");
        } else {
            angka1 = Integer.parseInt(fNumber);
            angka2 = Integer.parseInt(lNumber);
        }
    }
}
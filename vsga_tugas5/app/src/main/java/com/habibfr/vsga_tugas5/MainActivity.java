package com.habibfr.vsga_tugas5;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.hardware.lights.Light;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText editName;
    Button btnTampil, btnNext;
    TextView txtYourname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editName);
        btnNext = findViewById(R.id.btnNext);
        btnTampil = findViewById(R.id.btnTampil);
        txtYourname = findViewById(R.id.txtYourname);

        btnTampil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editName.getText().toString().trim().isEmpty()){
                    txtYourname.setText("Nama tidak boleh kosong!");
                }
                txtYourname.setText("Hello " + editName.getText().toString());
                editName.setText("");
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, KalkulatorActivity.class);
                startActivity(intent);
                finish();
            }
        });


        

    }
}
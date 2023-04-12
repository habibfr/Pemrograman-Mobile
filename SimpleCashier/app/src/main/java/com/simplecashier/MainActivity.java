package com.simplecashier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText harga;
    private Integer nilai = 0;
    private Button batal, bayar, btnPindah, btnAbout;
    private Integer qtyTeh = 0, qtyJeruk = 0, qtyrawon = 0, qtyPecel = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        harga = (EditText) findViewById(R.id.harga);
        harga.setText(nilai.toString());

        batal =(Button) findViewById(R.id.batal);
        bayar =(Button) findViewById(R.id.bayar);
        btnAbout =(Button) findViewById(R.id.btnAbout);
        batal.setOnClickListener(this);
        bayar.setOnClickListener(this);
        btnAbout.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.bayar:
                Intent pindahContent = new Intent(MainActivity.this, MainActivity2.class);
                String str = harga.getText().toString();
                pindahContent.putExtra("message_key", str);

                String t = qtyTeh.toString();
                String j = qtyJeruk.toString();
                String p = qtyPecel.toString();
                String r = qtyrawon.toString();

                pindahContent.putExtra("teh", t);
                pindahContent.putExtra("jeruk", j);
                pindahContent.putExtra("pecel", p);
                pindahContent.putExtra("rawon", r);
                startActivity(pindahContent);
                break;
            case R.id.batal:
                Toast.makeText(MainActivity.this, "Dibatalkan ", Toast.LENGTH_SHORT).show();
                nilai = 0;
                qtyTeh = 0;
                qtyJeruk = 0;
                qtyPecel = 0;
                qtyrawon = 0;
                harga.setText(nilai.toString());
                break;

            case R.id.btnAbout:
                Intent intent =  new Intent(MainActivity.this, About.class);
                startActivity(intent);
                break;
        }
    }


    public void bayarTeh(View view){
        nilai = nilai + 3000;
        harga.setText(nilai.toString());
        qtyTeh += 1;
    }

    public void bayarJeruk(View view){
        nilai = nilai + 4000;
        harga.setText(nilai.toString());
        qtyJeruk += 1;
    }

    public void bayarPecel(View view){
        nilai = nilai + 10000;
        harga.setText(nilai.toString());
        qtyPecel += 1;
    }

    public void bayarRawon(View view){
        nilai = nilai + 12000;
        harga.setText(nilai.toString());
        qtyrawon += 1;
    }
}
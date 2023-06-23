package com.habibfr.kreasiakademik;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class SimpanData extends AppCompatActivity {

    //deklarasi variabel
    private EditText code_txtNIM, code_txtNama;
    private Button code_btnSimpan;
    private TextView code_txtStatus;

    //deklarasi variabel tambahan
    private String URL = "http://172.16.43.19/kreasi-mobile/insert.php";
    //Catatan: URL di atas tolong disesuaikan dengan IP4 kalian, cek di Command Prompt, ketik ipconfig

    //Stringrequest salah satu library volley utk menangkap data
    StringRequest stringRequest;
    RequestQueue requestQueue;

    //deklarasi variabel tambahan
    private String nim, nama; //variabel ini digunakan untuk menampung isi dari "code_txtNIM" dan "code_txtNama"

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simpan_data);

        code_txtNIM = (EditText) findViewById(R.id.txtNIM);
        code_txtNama = (EditText) findViewById(R.id.txtNama);
        code_btnSimpan = (Button) findViewById(R.id.btnSimpan);
        code_txtStatus = (TextView) findViewById(R.id.txtStatus);

        code_btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    //untuk memastikan data yang terkirim sudah terisi di EditText, maka EditText tidak boleh kosong
                    //tampung data dari EditText ke variabel
                    nim = code_txtNIM.getText().toString().trim();
                    nama = code_txtNama.getText().toString().trim();

                    //cek terlebih dahulu jika inputan kosong atau tidak
                    if (nim.equals("") || nama.equals("")) //jika inputan kosong
                    {
                        code_txtStatus.setText("Inputan tidak lengkap"); //cara 1 dengan TextView
                        //Toast.makeText(getApplicationContext(), "Inputan tidak lengkap", Toast.LENGTH_LONG).show(); //cara 2 dengan Toast
                    } else {
                        // instnce of class
                        stringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                System.out.println(response);
                                code_txtStatus.setText(response.toString());
                                //untuk mengecek masuk di method onResponse()
                                //code_txtStatus.setText("masuk di method onResponse()"); //cara 1 dengan TextView
                                //Toast.makeText(getApplicationContext(), "masuk di method onResponse()", Toast.LENGTH_LONG).show(); //cara 2 dengan Toast

                                //cek apakah response = "Tersimpan" atau "Gagal Simpan"
                                //jika response = "Tersimpan", akan muncul Toast Data Berhasil Disimpan
                                if (response.equals("Tersimpan"))
                                    //membuat Toast untuk pesan data tersimpan
                                    Toast.makeText(getApplicationContext(), "Data Berhasil Disimpan", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(getApplicationContext(), "Data Gagal Disimpan", Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                System.out.println(error.getMessage());
                                code_txtStatus.setText("ERROR : " + error.getMessage());
                            }
                        }){
                            @Nullable
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError {
                                //jika script masih ada warning, brarti blom di transfer menggunakan method POST
                                //oleh karena itu, dibutuhkan 3 baris script ini untuk mengirim data ke login.php
                                Map<String, String> data = new HashMap<>();
                                data.put("nim", nim);
                                data.put("nama", nama);
                                return data;
                            }
                        };

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println(e.getMessage());
                }

                requestQueue = Volley.newRequestQueue(getApplicationContext());
                requestQueue.add(stringRequest);

            }
        });
    }
}
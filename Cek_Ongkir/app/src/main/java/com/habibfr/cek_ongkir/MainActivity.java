package com.habibfr.cek_ongkir;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    Button buttonAsync;
    String url = "https://jsonplaceholder.typicode.com/todos/1";
    OkHttpClient client;
    TextView hasil;
    ListView lvProvinsi;
    ProgressBar loading;
    Spinner spinnerProvinsi;
    ProvinsiAdapter provinsiAdapter;
    ArrayList<Provinsi> listProvinsi = new ArrayList<>();
    ArrayList<String> spinnerArray = new ArrayList<>();
    ArrayAdapter<String> spinnerAdpater;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAsync = findViewById(R.id.buttonAsync);
        hasil = findViewById(R.id.hasil);
        lvProvinsi = findViewById(R.id.lvListProvinsi);
        loading = findViewById(R.id.loading);
        spinnerProvinsi = findViewById(R.id.spinnerProvinsi);

        String[] isi = {"select provinsi"};
        spinnerAdpater = new ArrayAdapter<>(
                getApplicationContext(),
                android.R.layout.simple_spinner_item,
                isi
        );
        spinnerProvinsi.setAdapter(spinnerAdpater);


        client = new OkHttpClient();

        try {
            loading.setVisibility(View.VISIBLE);
            requestAsync();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

//        spinnerProvinsi.setOnItemSelectedListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                try {
//                    loading.setVisibility(View.VISIBLE);
//                    requestAsync();
//                    String tutorialsName = adapterView.getItemAtPosition(i).toString();
//                    Toast.makeText(getApplicationContext(), "Selected: " + tutorialsName, Toast.LENGTH_LONG).show();
//                } catch (IOException e) {
//                    throw new RuntimeException(e);
//                }
//            }
//        });

        spinnerProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tutorialsName = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), "Selected: " + (i+1), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        buttonAsync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    loading.setVisibility(View.VISIBLE);
                    requestAsync();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void requestAsync() throws IOException {
//        OkHttpClient.Builder builder = new OkHttpClient.Builder();
//        builder.connectTimeout(5, TimeUnit.SECONDS);
//        builder.readTimeout(5, TimeUnit.SECONDS);
//        builder.writeTimeout(5, TimeUnit.SECONDS);
//        client = builder.build();

//        Request request = new Request.Builder()
//                //.url("https://publicobject.com/helloworld.txt")
//                .url("https://jsonplaceholder.typicode.com/todos/1")
//                .build();

        Request request = new Request.Builder()
                .url("https://api.rajaongkir.com/starter/province")
                .get()
                .addHeader("key", "2676bcf77d8b5739dd53e60afb2779bd")
                .build();

//        Response response = client.newCall(request).execute();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Log.d("debuging", "Request Failed." + e.getMessage());
                responseAsync("Request Failed." + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        String responseString = response.body().string();
                        Log.d("debuging", responseString);
                        responseAsync(responseString);
                    } else {
                        Log.d("debuging", "Error " + response);
                        responseAsync("Error " + response);
                    }
                } catch (IOException e) {
                    Log.d("debuging", "Exception caught : ", e);
                    responseAsync("Error " + e.getMessage());
                }
            }
        });
        Toast.makeText(this, "OkHTTP requestAsync", Toast.LENGTH_SHORT).show();
    }

    private void responseAsync(final String responseStr) {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject(responseStr);
                    JSONObject res = jsonObject.getJSONObject("rajaongkir");
                    JSONArray result = res.getJSONArray("results");
                    listProvinsi.clear();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject provObj = new JSONObject(result.getString(i));
                        String id = provObj.getString("province_id");
                        String provinsi = provObj.getString("province");
                        spinnerArray.add(provinsi);
                        listProvinsi.add(new Provinsi(id, provinsi));
                    }

                    if (listProvinsi.size() > 0) {
                        loading.setVisibility(View.GONE);
//                        provinsiAdapter = new ProvinsiAdapter(getApplicationContext(), listProvinsi);
//                        lvProvinsi.setAdapter(provinsiAdapter);
//                        provinsiAdapter.notifyDataSetChanged();

                        spinnerAdpater = new ArrayAdapter<>(
                                getApplicationContext(),
                                android.R.layout.simple_spinner_item,
                                spinnerArray
                        );
                        spinnerProvinsi.setAdapter(spinnerAdpater);
                    }
                    Toast.makeText(getApplicationContext(), "DONE", Toast.LENGTH_SHORT).show();
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}

package com.habibfr.http_json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    private static final String API_URL = "https://run.mocky.io/v3/c988eda9-0d05-453e-8ba0-53ce95b894ce";
    ListView lvDaftarItem;
    Button btnFetchData;
    ArrayList<DataItem> listItem = new ArrayList<>();
    CustomAdapter customAdapter;
    TextView loading;
    RequestQueue requestQueue;
    ProgressBar loadingBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvDaftarItem = findViewById(R.id.lvDaftarItem);
        btnFetchData = findViewById(R.id.btnFetch);
        loading = findViewById(R.id.loading);

        loadingBar = findViewById(R.id.loadingBar);
        requestQueue = Volley.newRequestQueue(this);

        btnFetchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadingBar.setVisibility(View.VISIBLE);
                fetchData();
            }
        });
    }


    public void fetchData() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, API_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {

                    JSONObject jsonObject = new JSONObject(response);
                    JSONArray jsonArray = jsonObject.getJSONArray("orders");
                    if (jsonArray.length() > 0) {
                        loadingBar.setVisibility(View.GONE);
                        listItem.clear();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject res = jsonArray.getJSONObject(i);
//                            System.out.println(res);
                            String id = res.getString("id");
                            String item = res.getString("item");

                            System.out.println(id + " " + item);
                            listItem.add(new DataItem(id, item));
                        }

                        customAdapter = new CustomAdapter(getApplicationContext(), listItem);
                        lvDaftarItem.setAdapter(customAdapter);
                        customAdapter.notifyDataSetChanged();
                    }
//                    System.out.println(jsonArray);
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        });

        requestQueue.add(stringRequest);
    }
}
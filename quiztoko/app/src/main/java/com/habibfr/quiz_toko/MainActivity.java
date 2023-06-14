package com.habibfr.quiz_toko;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String API_URL = "http://172.16.34.240/pbm/toko/view_barang.php";

    ListView lvBarang;
    ArrayList<Barang> barangs = new ArrayList<>();
    BarangAdapater barangAdapater;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvBarang = findViewById(R.id.lvBarang);
        Async a = new Async();
        a.execute("");
    }

    private class Async extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            String data = "";
            try {

                data = downloadUrl(API_URL);
            } catch (Exception e) {
                Log.d("Background Task", e.toString());
            }
            return data;
        }

        @Override
        protected void onPostExecute(String result) {
            System.out.println(result);

            try {
                JSONObject jsonObj = new JSONObject(result);
                JSONArray orders = jsonObj.getJSONArray("barangs");

                for (int i = 0; i < orders.length(); i++) {
                    JSONObject c = orders.getJSONObject(i);
                    String id = c.getString("id");
                    String name = c.getString("nama");
                    String harga = c.getString("harga");
                    String stok = c.getString("stok");
                    barangs.add(new Barang(id, name, stok, harga));
                }

                barangAdapater = new BarangAdapater(getApplicationContext(), barangs);
                barangAdapater.notifyDataSetChanged();
                lvBarang.setAdapter(barangAdapater);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String downloadUrl(String strUrl) throws IOException {
        String data = "";
        InputStream iStream = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(strUrl);
            urlConnection = (HttpURLConnection)
                    url.openConnection();
            urlConnection.connect();
            iStream = urlConnection.getInputStream();
            BufferedReader br = new BufferedReader(new
                    InputStreamReader(iStream));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            data = sb.toString();
            br.close();
        } catch (Exception e) {
            Log.d("Exception url", e.toString());
        } finally {
            assert iStream != null;
            iStream.close();
            urlConnection.disconnect();
        }
        return data;
    }


}
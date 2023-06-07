package com.habibfr.asynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

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
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    ArrayList<Order> ordersList = new ArrayList<>();
    ListView lvOrders;
    OrderAdapter orderAdapter;

    private static final String API_URL = "http://172.16.41.60/pbm/fetch_data.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvOrders = findViewById(R.id.lvOrders);

        Async a = new Async();
        a.execute("");

    }

    private class Async extends AsyncTask<String, Integer, String> {
        @Override
        protected String doInBackground(String... params) {
            String data = "";
            try {

                URL url = new URL(API_URL);

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");
                conn.connect();

                int responsecode = conn.getResponseCode();

                if (responsecode != 200) {
                    throw new RuntimeException("HttpResponseCode: " + responsecode);
                } else {
                    StringBuilder inline = new StringBuilder();
                    Scanner scanner = new Scanner(url.openStream());
                    while (scanner.hasNext()) {
                        inline.append(scanner.nextLine());
                    }
                    data = inline.toString();
                    scanner.close();
                }
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
                JSONArray orders = jsonObj.getJSONArray("orders");

                for (int i = 0; i < orders.length(); i++) {
                    JSONObject c = orders.getJSONObject(i);
                    String id = c.getString("id");
                    String name = c.getString("item");
                    ordersList.add(new Order(id, name));
                }

                orderAdapter = new OrderAdapter(getApplicationContext(), ordersList);
                orderAdapter.notifyDataSetChanged();
                lvOrders.setAdapter(orderAdapter);
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
//            System.out.println("why");
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
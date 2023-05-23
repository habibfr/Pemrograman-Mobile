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
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
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
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    Button buttonAsync, btnCek;
    OkHttpClient client;
    EditText editWeight;
    TextView hasil, txtIdProv, txtIdCity, txtIdProvDes, txtIdCityDes;
    ListView lvProvinsi, lvCost;
    ProgressBar loading;
    Spinner spinnerProvinsi, spinnerCity, spinnerProvinsiDes, spinnerCityDes;
    ProvinsiAdapter provinsiAdapter;
    ArrayList<Provinsi> listProvinsi = new ArrayList<>();
    ArrayList<String> dataSpinnerProvince = new ArrayList<>(), dataSpinnerCityDes = new ArrayList<>(), dataSpinnerCity = new ArrayList<>();
    ArrayAdapter<String> spinnerAdpater, listSpinnerCity, arryAdapterCityDes;

    ArrayList<City> listCity = new ArrayList<>(), listCityDes = new ArrayList<>();
    ArrayList<Cost> listCost = new ArrayList<>();
    CostAdapater costAdapater;
    String cityOrigin = "";
    String cityDestination = "";
    String kurirSelected = "";
    boolean isFunctionExecuted = false;
    RadioGroup radioKurir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonAsync = findViewById(R.id.buttonAsync);
        hasil = findViewById(R.id.hasil);
        lvProvinsi = findViewById(R.id.lvListProvinsi);
        loading = findViewById(R.id.loading);
        txtIdProv = findViewById(R.id.txtIdProv);
        txtIdCity = findViewById(R.id.txtIdCity);
        txtIdProvDes = findViewById(R.id.txtIdProvDes);
        txtIdCityDes = findViewById(R.id.txtIdCityDes);
        spinnerCity = findViewById(R.id.spinnerCity);
        spinnerProvinsi = findViewById(R.id.spinnerProvinsi);
        spinnerCityDes = findViewById(R.id.spinnerCityDes);
        spinnerProvinsiDes = findViewById(R.id.spinnerProvinsiDes);
        btnCek = findViewById(R.id.btnCek);
        editWeight = findViewById(R.id.editWeight);
        lvCost = findViewById(R.id.lvCost);
        radioKurir = findViewById(R.id.radioKurir);


        client = new OkHttpClient();

        spinnerProvinsi.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tutorialsName = adapterView.getItemAtPosition(i).toString();
                try {
                    txtIdProv.setText(String.valueOf((i + 1)));
                    requestCity();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String id = listCity.get(i).getCity_id();
                txtIdCity.setText(id);
                cityOrigin = id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerProvinsiDes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String tutorialsName = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(getApplicationContext(), "Selected: " + (i+1), Toast.LENGTH_LONG).show();
                try {
                    txtIdProvDes.setText(String.valueOf((i + 1)));
                    requestCityDes();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerCityDes.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                txtIdCityDes.setText("ID CTY : " + (i + 1));
                String id = listCityDes.get(i).getCity_id();
                txtIdCityDes.setText(id);
                cityDestination = id;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        radioKurir.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                RadioButton radioButton = findViewById(i);
                kurirSelected = radioButton.getText().toString().toLowerCase();
            }
        });

        if (!isFunctionExecuted) {
            try {
                requestProvince();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            isFunctionExecuted = true;
        }

        btnCek.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    loading.setVisibility(View.VISIBLE);
                    lvCost.setVisibility(View.GONE);
                    requestCost();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }


    public void requestProvince() throws IOException {
        Request request = new Request.Builder()
                .url("https://api.rajaongkir.com/starter/province")
                .get()
                .addHeader("key", "2676bcf77d8b5739dd53e60afb2779bd")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(getApplicationContext(), "Error...", Toast.LENGTH_SHORT).show();
                ResponProvince("Request Failed." + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        String responseString = response.body().string();
                        ResponProvince(responseString);
                    } else {
                        ResponProvince("Error " + response);
                        Toast.makeText(getApplicationContext(), "Error...", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    ResponProvince("Error " + e.getMessage());
                }
            }
        });
    }

    private void ResponProvince(final String responseStr) {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject(responseStr);
                    JSONObject res = jsonObject.getJSONObject("rajaongkir");
                    JSONArray result = res.getJSONArray("results");
                    listProvinsi.clear();
                    dataSpinnerCity.clear();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject provObj = new JSONObject(result.getString(i));
                        String id = provObj.getString("province_id");
                        String provinsi = provObj.getString("province");
                        dataSpinnerProvince.add(provinsi);
                        listProvinsi.add(new Provinsi(id, provinsi));
                    }

                    if (listProvinsi.size() > 0) {
                        loading.setVisibility(View.GONE);

                        spinnerAdpater = new ArrayAdapter<>(
                                getApplicationContext(),
                                android.R.layout.simple_spinner_item,
                                dataSpinnerProvince
                        );
                        spinnerProvinsi.setAdapter(spinnerAdpater);
                        spinnerProvinsiDes.setAdapter(spinnerAdpater);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void requestCity() throws IOException {
        Request request = new Request.Builder()
                .url("https://api.rajaongkir.com/starter/city?province=" + txtIdProv.getText())
                .get()
                .addHeader("key", "2676bcf77d8b5739dd53e60afb2779bd")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                ResponCity("Request Failed." + e.getMessage());
                Toast.makeText(getApplicationContext(), "Error...", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        String responseString = response.body().string();
                        ResponCity(responseString);
                    } else {
                        ResponCity("Error " + response);
                        Toast.makeText(getApplicationContext(), "Error...", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    ResponCity("Error " + e.getMessage());
                }
            }
        });
    }

    private void ResponCity(final String responseStr) {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject(responseStr);
                    JSONObject res = jsonObject.getJSONObject("rajaongkir");
                    JSONArray result = res.getJSONArray("results");
                    dataSpinnerCity.clear();
                    listCity.clear();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject cityObj = new JSONObject(result.getString(i));
                        String city_id = cityObj.getString("city_id");
                        String province_id = cityObj.getString("province_id");
                        String province = cityObj.getString("province");
                        String type = cityObj.getString("type");
                        String city_name = cityObj.getString("city_name");
                        String postal_code = cityObj.getString("postal_code");
                        listCity.add(new City(city_id, province_id, province, type, city_name, postal_code));
                        dataSpinnerCity.add(city_name);
                    }

                    if (dataSpinnerCity.size() > 0) {
                        loading.setVisibility(View.GONE);
                        listSpinnerCity = new ArrayAdapter<>(
                                getApplicationContext(),
                                android.R.layout.simple_spinner_item,
                                dataSpinnerCity
                        );
                        spinnerCity.setAdapter(listSpinnerCity);
                    }

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void requestCityDes() throws IOException {
        Request request = new Request.Builder()
                .url("https://api.rajaongkir.com/starter/city?province=" + txtIdProvDes.getText())
                .get()
                .addHeader("key", "2676bcf77d8b5739dd53e60afb2779bd")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                ResponCityDes("Request Failed." + e.getMessage());
                Toast.makeText(getApplicationContext(), "Error...", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        String responseString = response.body().string();
                        ResponCityDes(responseString);
                    } else {
                        ResponCityDes("Error " + response);
                        Toast.makeText(getApplicationContext(), "Error...", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    ResponCityDes("Error " + e.getMessage());
                }
            }
        });
    }

    private void ResponCityDes(final String responseStr) {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject(responseStr);
                    JSONObject res = jsonObject.getJSONObject("rajaongkir");
                    JSONArray result = res.getJSONArray("results");
                    dataSpinnerCityDes.clear();
                    listCityDes.clear();

                    for (int i = 0; i < result.length(); i++) {
                        JSONObject cityObj = new JSONObject(result.getString(i));
                        String city_id = cityObj.getString("city_id");
                        String province_id = cityObj.getString("province_id");
                        String province = cityObj.getString("province");
                        String type = cityObj.getString("type");
                        String city_name = cityObj.getString("city_name");
                        String postal_code = cityObj.getString("postal_code");
                        listCityDes.add(new City(city_id, province_id, province, type, city_name, postal_code));
                        dataSpinnerCityDes.add(city_name);
                    }

                    if (dataSpinnerCityDes.size() > 0) {
                        loading.setVisibility(View.GONE);
                        arryAdapterCityDes = new ArrayAdapter<>(
                                getApplicationContext(),
                                android.R.layout.simple_spinner_item,
                                dataSpinnerCityDes
                        );
                        spinnerCityDes.setAdapter(arryAdapterCityDes);
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    public void requestCost() throws IOException {
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        String weight = editWeight.getText().toString();
        weight = weight.isEmpty() ? "1000" : weight;
        kurirSelected = kurirSelected.isEmpty() ? "jne" : kurirSelected;
        RequestBody body = RequestBody.create("origin=" + cityOrigin + "&destination=" + cityDestination + "&weight=" + weight + "&courier=" + kurirSelected + "", mediaType);
        Request request = new Request.Builder()
                .url("https://api.rajaongkir.com/starter/cost")
                .post(body)
                .addHeader("key", "2676bcf77d8b5739dd53e60afb2779bd")
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                Toast.makeText(getApplicationContext(), "Error...", Toast.LENGTH_SHORT).show();
                ResponCost("Request Failed." + e.getMessage());
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try {
                    if (response.isSuccessful()) {
                        String responseString = response.body().string();
                        ResponCost(responseString);
                    } else {
                        ResponCityDes("Error " + response);
                        Toast.makeText(getApplicationContext(), "Error...", Toast.LENGTH_SHORT).show();
                    }
                } catch (IOException e) {
                    ResponCost("Error " + e.getMessage());
                }
            }
        });
    }

    private void ResponCost(final String responseStr) {
        runOnUiThread(new Runnable() {
            public void run() {
                try {
                    JSONObject jsonObject = new JSONObject(responseStr);
                    JSONObject res = jsonObject.getJSONObject("rajaongkir");
                    JSONArray result = res.getJSONArray("results");

                    listCost.clear();
                    for (int i = 0; i < result.length(); i++) {
                        JSONObject resultsObj = new JSONObject(result.getString(i));
                        String code = resultsObj.getString("code");
                        String name = resultsObj.getString("name");
                        JSONArray costs = resultsObj.getJSONArray("costs");

                        for (int j = 0; j < costs.length(); j++) {
                            JSONObject resCosts = new JSONObject(costs.getString(j));
                            JSONArray cost = resCosts.getJSONArray("cost");

                            for (int k = 0; k < cost.length(); k++) {
                                String service = resCosts.getString("service");
                                String description = resCosts.getString("description");

                                JSONObject resCost = new JSONObject(cost.getString(k));
                                String value = resCost.getString("value");
                                String etd = resCost.getString("etd");
                                String note = resCost.getString("note");
                                listCost.add(new Cost(service, description, value, etd));
                            }
                        }
                    }

                    if (listCost.size() > 0) {
                        loading.setVisibility(View.GONE);
                        lvCost.setVisibility(View.VISIBLE);
                        costAdapater = new CostAdapater(getApplicationContext(), listCost);
                        lvCost.setAdapter(costAdapater);
                        costAdapater.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }
}

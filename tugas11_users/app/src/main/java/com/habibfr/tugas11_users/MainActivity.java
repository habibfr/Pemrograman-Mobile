package com.habibfr.tugas11_users;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText editNameRegis, editUsernameRegis, editPasswordRegis, editUsernameLogin, editPasswordLogin;
    Button btnRegis, btnLogin;
    TextView txtStatus, txtStatusLogin;
    String status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editNameRegis = findViewById(R.id.editNamaRegis);
        editUsernameRegis = findViewById(R.id.editUsernameRegis);
        editPasswordRegis = findViewById(R.id.editPasswordRegis);
        editUsernameLogin = findViewById(R.id.editUsernameLogin);
        editPasswordLogin = findViewById(R.id.editPasswordLogin);
        btnRegis = findViewById(R.id.btnRegis);
        btnLogin = findViewById(R.id.btnLogin);
        txtStatus = findViewById(R.id.txtStatus);
        txtStatusLogin = findViewById(R.id.txtStatusLogin);

//        txtStatus.setText(status);
//        txtStatusLogin.setText(status);

        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query1 = editNameRegis.getText().toString();
                String query2 = editUsernameRegis.getText().toString();
                String query3 = editPasswordRegis.getText().toString();

                Uri.Builder builder = new Uri.Builder().appendQueryParameter("nama", query1).appendQueryParameter("username", query2).appendQueryParameter("password", query3);
                new KonektorT("http://192.168.43.37/pbm/users/add_user.php", builder).execute();
//                txtStatus.setText(status);
                txtStatusLogin.setVisibility(View.GONE);
                txtStatusLogin.setText("");
                txtStatus.setVisibility(View.VISIBLE);
            }

        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String query1 = editUsernameLogin.getText().toString();
                String query2 = editPasswordLogin.getText().toString();

                new KonektorT("http://192.168.43.37/pbm/users/cek_login.php", new Uri.Builder().appendQueryParameter("username", query1).appendQueryParameter("password", query2)).execute();


//                txtStatusLogin.setText(status);

                txtStatus.setVisibility(View.GONE);

                txtStatus.setText("");
                txtStatusLogin.setVisibility(View.VISIBLE);
            }
        });


    }

    public class KonektorT extends AsyncTask<String, String, String> {
        private static final int READ_TIMEOUT = 15000;
        private static final int CONNECTION_TIMEUT = 10000;
        Context context;
        ProgressDialog pdLoading = new ProgressDialog(MainActivity.this);
        HttpURLConnection conn;
        URL url = null;
        String situs;
        //        String p1, p2, p3;
        Uri.Builder builder;


        public KonektorT(String situs, Uri.Builder builder) {
            this.situs = situs;
            this.builder = builder;
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdLoading.setMessage("\tloading...");
            pdLoading.setCancelable(false);
            pdLoading.show();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                url = new URL(situs);

                System.out.println("try 1");
            } catch (MalformedURLException e) {
//            throw new RuntimeException(e);
                e.printStackTrace();
                System.out.println("gagal1");
                return e.getMessage();
            }

            try {
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(READ_TIMEOUT);
                conn.setConnectTimeout(CONNECTION_TIMEUT);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

//                Uri.Builder builder = new Uri.Builder().appendQueryParameter("nama", p1).appendQueryParameter("username", p2).appendQueryParameter("password", p3);
                String query = builder.build().getEncodedQuery();
                OutputStream outputStream = conn.getOutputStream();
                BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream, "UTF-8"));
                bufferedWriter.write(query);
                bufferedWriter.flush();
                bufferedWriter.close();
                outputStream.close();
                conn.connect();
                System.out.println("try 2");
                System.out.println("yourLink?" + query);

            } catch (IOException e) {
                System.out.println("gagal2");
                throw new RuntimeException(e);
            }

            try {
                int response_code = conn.getResponseCode();
                if (response_code != HttpURLConnection.HTTP_OK) {
                    System.out.println("gagl3");
                    return ("Connection error");
                } else {
                    InputStream input = conn.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }
                    System.out.println("try 3");
                    return (result.toString());
                }
            } catch (IOException e) {
                e.printStackTrace();
                return e.toString();
            } finally {
                conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String s) {
//            if (!s.isEmpty()) {
            pdLoading.dismiss();
            txtStatus.setText(s.toString());
            txtStatusLogin.setText(s.toString());
//                System.out.println(s);
//                status = s.toString();
//            }
        }
    }

}
package com.habibfr.webview_lab_8_3;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private EditText editTextSearch;
    private Button btnSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        editTextSearch = findViewById(R.id.editTextSearch);
        btnSearch = findViewById(R.id.btnSearch);

        webView.getSettings().setJavaScriptEnabled(true);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = editTextSearch.getText().toString();
                if (url.isEmpty()) {
                    webView.loadData("<p>Url yang anda masukan salah</p>", "text/html", "UTF-8");
                } else {
                    webView.loadUrl(url);
                }
            }
        });

        webView.setWebViewClient(new WebViewClient());


    }
}


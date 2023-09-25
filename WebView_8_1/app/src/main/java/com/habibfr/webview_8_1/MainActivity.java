package com.habibfr.webview_8_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        webView = findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
//        String data ="<h1>Hello Boys</h1><p>Selamat datang di WebView page</p> <button>click</button>";

//        String data="<h1>Halaman Web 000280</h1><p>Selamat datang di WebView page</p>";
//        String data1 = "<html>\n" +
//                "\n" +
//                "<head>\n" +
//                "  <meta charset=\"UTF-8\">\n" +
//                "  <title></title>\n" +
//                "</head>\n" +
//                "\n" +
//                "<body>\n" +
//                "  <div class=\"main\">\n" +
//                "\n" +
//                "    <h3>Click Counter</h3>\n" +
//                "    <button id=\"clickme\">Click me: 0</button>\n" +
//                "  </div>\n" +
//                "  <script>\n" +
//                "  \tvar button = document.getElementById(\"clickme\"),\n" +
//                "  count = 0;\n" +
//                "button.onclick = function() {\n" +
//                "  count += 1;\n" +
//                "  button.innerHTML = \"Click me: \" + count;\n" +
//                "};\n" +
//                "\n" +
//                "  </script>\n" +
//                "</body>\n" +
//                "\n" +
//                "</html>";

//        webView.loadData(data, "text/html", "UTF-8");
        webView.loadUrl("file:///android_asset/index.html");
//        webView.loadUrl("https://gate.dinamika.ac.id/");

        // this will enable the javascript.
//        webView.getSettings().setJavaScriptEnabled(true);

        // WebViewClient allows you to handle
        // onPageFinished and override Url loading.
//        webView.setWebViewClient(new WebViewClient());
    }
}
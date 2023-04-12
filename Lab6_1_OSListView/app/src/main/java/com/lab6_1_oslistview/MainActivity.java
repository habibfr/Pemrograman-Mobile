package com.lab6_1_oslistview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private static final String DAFTAR_OS[] = {"Windows", "Ubuntu", "Debian", "IOS", "Android"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.myListView);

        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, R.layout.activity_list_view_item, R.id.textView, DAFTAR_OS);

        listView.setAdapter(myAdapter);
    }
}
package com.lab_7_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    private GridView gvMy, selectedImage;
    private int[] logos = {
            R.drawable.ad,
            R.drawable.ae,
            R.drawable.af,
            R.drawable.ag,
            R.drawable.ai,
            R.drawable.al,
            R.drawable.am,
            R.drawable.ao,
            R.drawable.aq,
            R.drawable.ar,
            R.drawable.jk,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        gvMy = findViewById(R.id.gvMy);


        CustomAdapter customAdapter = new CustomAdapter(this, logos);
        gvMy.setAdapter(customAdapter);

        gvMy.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, view_activity.class);

                intent.putExtra("image", logos[i]);
                startActivity(intent);
            }
        });

    }
}
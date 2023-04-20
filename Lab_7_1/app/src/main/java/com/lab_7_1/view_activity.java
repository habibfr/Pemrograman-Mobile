package com.lab_7_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class view_activity extends AppCompatActivity {

    private ImageView selectedImage;
    private Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        selectedImage = findViewById(R.id.selectedImage);
        back = findViewById(R.id.back);

        Intent intent = getIntent();
        selectedImage.setImageResource(intent.getIntExtra("image", 0));

        back.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }
}
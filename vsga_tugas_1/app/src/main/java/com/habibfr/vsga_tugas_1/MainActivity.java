package com.habibfr.vsga_tugas_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.Constraints;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    int[] arrWarna = {R.color.yellow, R.color.purple_200, R.color.teal_700, R.color.purple_500, R.color.teal_200};
    int index = 0;
    int i = 4;
    View layoutConst;
    Button btnUbahWarna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnUbahWarna = findViewById(R.id.btnUbahWarna);
        layoutConst = findViewById(R.id.layoutConst);


        btnUbahWarna.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                index = 0;
                layoutConst.setBackgroundColor(getResources().getColor(arrWarna[index]));
                btnUbahWarna.setBackgroundColor(getResources().getColor(arrWarna[i]));
                index++;
                i--;

                if (index == arrWarna.length){
                    index = 0;
                    i = 4;
                }
            }
        });
    }
}
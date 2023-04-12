package com.simplecashier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class About extends AppCompatActivity implements View.OnClickListener {
    private TextView txtEmail, txtPhone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtPhone = (TextView) findViewById(R.id.txtPhone);

        txtEmail.setOnClickListener(this);
        txtPhone.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.txtEmail:
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[] {"21410100050@dinamika.ac.id"});
                email.putExtra(Intent.EXTRA_SUBJECT, "PBM");
                email.putExtra(Intent.EXTRA_TEXT, "Latihan Intent");
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an Email client :"));
                break;
            case R.id.txtPhone:
                String nomorHP = "081234825491";
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" +nomorHP));
                startActivity(intent);
                break;

        }
    }
}
package com.habibfr.tugas9_contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ActionActivity extends AppCompatActivity {

    Button btnBack;
    TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);


        btnBack = findViewById(R.id.btnBack);
        txtName = findViewById(R.id.txtName);

        Intent intent = getIntent();
        Contact contact = (Contact) intent.getSerializableExtra("contact");

        txtName.setText(contact.getName() + contact.getId() + contact.getNoHp());

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


    }
}
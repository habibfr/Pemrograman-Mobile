package com.habibfr.contact_management;

import static com.habibfr.contact_management.MainActivity.isTrue;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class TambahMemberActivity extends AppCompatActivity {

    Button btnAdd;
    EditText editName;
    DictionaryOpenHelper dictionaryOpenHelper;
    ArrayList<Contact> listContact = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tambah_member);

        btnAdd = findViewById(R.id.btnAdd);
        dictionaryOpenHelper = new DictionaryOpenHelper(TambahMemberActivity.this);
        editName = findViewById(R.id.editName);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editName.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter all the data..", Toast.LENGTH_SHORT).show();
                    return;
                }

                // on below line we are calling a method to add new
                // course to sqlite data and pass all our values to it.
                dictionaryOpenHelper.addContact(editName.getText().toString());

                // after adding the data we are displaying a toast message.
                Toast.makeText(getApplicationContext(), "Contact has been added.", Toast.LENGTH_SHORT).show();
                isTrue = false;
                finish();
            }
        });
    }
}
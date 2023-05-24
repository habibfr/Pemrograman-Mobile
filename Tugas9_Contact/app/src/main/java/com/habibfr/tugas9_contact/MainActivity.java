package com.habibfr.tugas9_contact;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lvContacts;
    GridView gvContacts;
    Button btnTambahContact;
    EditText editName, editNoHp;
    ContactHandler contactHandler;
    ContactAdapter contactAdapter;
    List<Contact> listContact = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvContacts = findViewById(R.id.lvContacs);
        gvContacts = findViewById(R.id.gvContacts);
        btnTambahContact = findViewById(R.id.btnTambahContact);
        editName = findViewById(R.id.editName);
        editNoHp = findViewById(R.id.editNoHp);

        contactHandler = new ContactHandler(MainActivity.this);
        listContact = contactHandler.getAllContacts();

        contactAdapter = new ContactAdapter(getApplicationContext(), listContact);
//        lvContacts.setAdapter(contactAdapter);
        gvContacts.setAdapter(contactAdapter);

        btnTambahContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editName.getText().toString();
                String noHp = editNoHp.getText().toString();

                if(name.trim().isEmpty() || noHp.trim().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Harap isi field dengan benar", Toast.LENGTH_LONG).show();
                return;
                }

                contactHandler.addContact(name, noHp);
                // after adding the data we are displaying a toast message.
                Toast.makeText(getApplicationContext(), "Contact has been added.", Toast.LENGTH_SHORT).show();

                listContact = contactHandler.getAllContacts();
                contactAdapter = new ContactAdapter(getApplicationContext(), listContact);
                contactAdapter.notifyDataSetChanged();
                gvContacts.setAdapter(contactAdapter);
            }
        });


        gvContacts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, ActionActivity.class);
                intent.putExtra("contact", (Serializable) listContact.get(i));
                startActivity(intent);
            }
        });

    }
}
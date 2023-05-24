package com.habibfr.contact_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    public static boolean isTrue = false;
    Button btnTambahMember;
    ListView lvMemberContact;
    DictionaryOpenHelper dictionaryOpenHelper;
    ArrayAdapter<String> itemsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.onRestart();
        setContentView(R.layout.activity_main);
        btnTambahMember = findViewById(R.id.btnTambahMember);
        lvMemberContact = findViewById(R.id.lvMemberContact);
        dictionaryOpenHelper = new DictionaryOpenHelper(getApplicationContext());
        btnTambahMember.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                isTrue = false;
                Intent intent = new Intent(getApplicationContext(), TambahMemberActivity.class);
                startActivity(intent);
            }
        });


//        if(!isTrue){
        ArrayList<Contact> isi = dictionaryOpenHelper.readCourses();
        System.out.println(isi.get(1).getName());
        String[] data = new String[isi.size()];

        for (int i = 0; i < isi.size(); i++) {
            data[i] = isi.get(i).getName();
        }

        itemsAdapter =
                new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, data);

        lvMemberContact.setAdapter(itemsAdapter);
        isTrue = true;
//        }


    }

}
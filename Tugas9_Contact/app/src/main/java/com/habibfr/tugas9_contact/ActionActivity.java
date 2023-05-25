package com.habibfr.tugas9_contact;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActionActivity extends AppCompatActivity {

    Button btnBack, btnUpdate, btnDelete;
    TextView txtName;
    EditText editName, editNoHp;
    ContactHandler contactHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_action);


        btnBack = findViewById(R.id.btnBack);
//        txtName = findViewById(R.id.txtName);
        btnUpdate = findViewById(R.id.btnUpdate);
        btnDelete = findViewById(R.id.btnHapus);
        editName = findViewById(R.id.editName);
        editNoHp = findViewById(R.id.editNoHp);

        contactHandler = new ContactHandler(this);

        Intent intent = getIntent();
        Contact contact = (Contact) intent.getSerializableExtra("contact");

        String id = String.valueOf(contact.getId());
        String name = contact.getName();
        String noHp = contact.getNoHp();
        editName.setText(name);
        editNoHp.setText(noHp);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogConfirmation(contact, "update");
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogConfirmation(contact, "delete");
            }
        });


    }

    private void dialogConfirmation(Contact contact, String action) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(false);
        //set pesan yang akan ditampilkan
        String message = (action.equals("delete")) ? "Menghapus" : "Mengupdate";
        builder.setMessage("Anda Yakin Ingin " + message + " ?");
        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (action.equals("delete")) {
                    contactHandler.deleteContact(contact);
                    finish();
                }

                if (action.equals("update")) {
                    String name = editName.getText().toString();
                    String noHp = editNoHp.getText().toString();

                    if (name.trim().isEmpty() || noHp.trim().isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Field tidak boleh kosong!", Toast.LENGTH_LONG).show();
                        return;
                    }

                    contact.setName(name);
                    contact.setNoHp(noHp);
                    contactHandler.updateContact(contact);
                    finish();
                }
            }
        });
        //set negative tombol jika menjawab tidak
        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //jika menekan tombol tidak, maka kalian akan tetap berada di activity saat ini
                dialog.cancel();
            }
        });
        AlertDialog alert = builder.create();
        alert.show();
    }

}
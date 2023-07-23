package com.habibfr.aplikasi_sqlite;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.habibfr.aplikasi_sqlite.helper.DbHelper;

public class AddEditActivity extends AppCompatActivity {
    EditText editId, editName, editAddress;
    Button btnSubmit, btnCancel;
    DbHelper SQLite = new DbHelper(this);
    String id, name, address;

    @Override
    public void onBackPressed() {
//        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                blank();
                this.finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit);

        editId = findViewById(R.id.editId);
        editName = findViewById(R.id.editName);
        editAddress = findViewById(R.id.editAddress);
        btnSubmit = findViewById(R.id.btnSubmit);
        btnCancel = findViewById(R.id.btnCancel);

        id = getIntent().getStringExtra(MainActivity.TAG_ID);
        name = getIntent().getStringExtra(MainActivity.TAG_NAME);
        address = getIntent().getStringExtra(MainActivity.TAG_ADDRESS);

        if (id == null || id.equals("")) {
            setTitle("Add Data");
        } else {
            setTitle("Edit Data");
            editId.setText(id);
            editName.setText(name);
            editAddress.setText(address);
        }


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (editId.getText().toString().equals("")) {
                        save();
                    } else {
                        edit();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                blank();
                finish();
            }
        });

    }

    private void blank() {
        editName.requestFocus();
        editId.setText(null);
        editName.setText(null);
        editAddress.setText(null);
    }

    private void save() {
        if (String.valueOf(editName.getText()).equals(null) || String.valueOf(editName.getText()).equals("") || String.valueOf(editAddress.getText()).equals(null) || String.valueOf(editName.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Please input name or address...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.insert(editName.getText().toString().trim(), editAddress.getText().toString().trim());
            blank();
            finish();
        }
    }

    private void edit() {
        if (String.valueOf(editName.getText()).equals(null) || String.valueOf(editName.getText()).equals("") || String.valueOf(editAddress.getText()).equals(null) || String.valueOf(editName.getText()).equals("")) {
            Toast.makeText(getApplicationContext(), "Please input name or address...", Toast.LENGTH_SHORT).show();
        } else {
            SQLite.update(Integer.parseInt(editId.getText().toString().trim()), editName.getText().toString().trim(), editAddress.getText().toString().trim());
            blank();
            finish();
        }

    }
}
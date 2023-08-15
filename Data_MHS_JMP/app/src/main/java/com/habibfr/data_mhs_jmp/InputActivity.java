package com.habibfr.data_mhs_jmp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.habibfr.data_mhs_jmp.helper.DbHelper;
import com.habibfr.data_mhs_jmp.model.Mahasiswa;

import java.util.Calendar;
import java.util.Objects;

public class InputActivity extends AppCompatActivity {

    private EditText editNomor, editNama, editTanggal, editJenisKelamin, editAlamat, editId;
    Button btnSimpanData;
    ActionBar actionBar;
    DbHelper dbHelper = new DbHelper(InputActivity.this);
    Mahasiswa mhs;
    TextView txtIdEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);
        actionBar = getSupportActionBar();


        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setTitle("Tambah Data Mahasiswa");

        editId = findViewById(R.id.editId);
        editNomor = findViewById(R.id.editNomor);
        editNama = findViewById(R.id.editNama);
        editTanggal = findViewById(R.id.editTanggal);
        editJenisKelamin = findViewById(R.id.editJenisKelamin);
        editAlamat = findViewById(R.id.editAlamat);
        btnSimpanData = findViewById(R.id.btnSimpanData);
        txtIdEdit = findViewById(R.id.txtIdEdit);

        Intent intent = getIntent();
        mhs = (Mahasiswa) intent.getSerializableExtra("mahasiswa");

        editId.setVisibility(View.GONE);
        txtIdEdit.setVisibility(View.GONE);
        if (mhs != null) {

            actionBar.setTitle("Edit Data Mahasiswa");
            editId.setVisibility(View.VISIBLE);
            txtIdEdit.setVisibility(View.VISIBLE);
            editId.setText(String.valueOf(mhs.getId()));
            editNomor.setText(mhs.getNomor());
            editNama.setText(mhs.getNama());
            editTanggal.setText(mhs.getTglLahir());
            editJenisKelamin.setText(mhs.getJenisKelamin());
            editAlamat.setText(mhs.getAlamat());
        }


//        if (mhs != null) {
//            if (String.valueOf(mhs.getId()) == null || id.equals("")) {
//                setTitle("Add Data");
//            }
//        } else {
//            setTitle("Edit Data");
//            editId.setText(id);
//            editName.setText(name);
//            editAddress.setText(address);
//        }


        editTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // on below line we are getting
                // the instance of our calendar.
                final Calendar c = Calendar.getInstance();

                // on below line we are getting
                // our day, month and year.
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);

                // on below line we are creating a variable for date picker dialog.
                DatePickerDialog datePickerDialog = new DatePickerDialog(
                        // on below line we are passing context.
                        InputActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                editTanggal.setText(i2 + "-" + (i1 + 1) + "-" + i);
                            }

                        },
                        // on below line we are passing year,
                        // month and day for selected date in our date picker.
                        year, month, day);
                // at last we are calling show to
                // display our date picker dialog.
                datePickerDialog.show();

            }
        });


        btnSimpanData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    if (mhs == null) {
                        save();
                    } else {
                        edit();
                    }
//                    save();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        });
    }

    private void save() {
        if (String.valueOf(editNomor.getText()).trim().isEmpty() || String.valueOf(editNama.getText()).trim().isEmpty() || String.valueOf(editTanggal.getText()).trim().isEmpty() || String.valueOf(editJenisKelamin.getText()).trim().isEmpty() || String.valueOf(editAlamat.getText()).trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please input all field...", Toast.LENGTH_SHORT).show();
        } else {
            String no = editNomor.getText().toString().trim();
            dbHelper.insert(no, editNama.getText().toString().trim(), editTanggal.getText().toString().trim(), editJenisKelamin.getText().toString().trim(), editAlamat.getText().toString().trim());
            blank();
            finish();
        }
    }

    private void edit() {
        if (String.valueOf(editNomor.getText()).trim().isEmpty() || String.valueOf(editNama.getText()).trim().isEmpty() || String.valueOf(editTanggal.getText()).trim().isEmpty() || String.valueOf(editJenisKelamin.getText()).trim().isEmpty() || String.valueOf(editAlamat.getText()).trim().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please input all field...", Toast.LENGTH_SHORT).show();
        } else {
            String no = editNomor.getText().toString().trim();
            int id = mhs.getId();
            dbHelper.update(id, no, editNama.getText().toString().trim(), editTanggal.getText().toString().trim(), editJenisKelamin.getText().toString().trim(), editAlamat.getText().toString().trim());
            blank();
            finish();
        }
    }

    private void blank() {
        editNomor.setText("");
        editNama.setText("");
        editTanggal.setText("");
        editJenisKelamin.setText("");
        editAlamat.setText("");
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }
}
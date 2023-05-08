package com.uts_pbm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    String[] options = new String[]{"Debit", "Kredit"};
    ListView lvTrx;
    EditText editHarga, editUraian, editTgl;
    Spinner editJenis;
    ArrayList<DataTrx> listTrx;

    Button btnTambah, btnReport;
    Button hapus;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Spinner spinner = findViewById(R.id.spinner);
        btnTambah = findViewById(R.id.btnTambah);
        btnReport = findViewById(R.id.btnReport);

//        hapus = findViewById(R);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, options);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        editTgl = findViewById(R.id.editTgl);
        editUraian = findViewById(R.id.editUraian);
        editHarga = findViewById(R.id.editHarga);


        listTrx = new ArrayList<DataTrx>();

        customAdapter = new CustomAdapter(listTrx, this);
        lvTrx = findViewById(R.id.lvTrx);
        lvTrx.setAdapter(customAdapter);

        editTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                        MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                                // on below line we are setting date to our text view.
                                editTgl.setText(i + "-" + (i1 + 1) + "-" + i2);
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


        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, Laporan.class);

                i.putExtra("trx", listTrx);
                startActivity(i);
            }
        });


    }

    public void reset(){
        editTgl.setText("");
        editHarga.setText("");
        editUraian.setText("");
    }
}
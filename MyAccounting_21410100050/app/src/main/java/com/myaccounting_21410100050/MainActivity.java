package com.myaccounting_21410100050;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ListView lvTransaksi;
    EditText editId, editTgl, editUraian, editDebit, editKredit;
    ArrayList<DataTrx> listTransaksi;
    Button btnTambah, btnReport;

    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnTambah = findViewById(R.id.btnTambah);
        btnReport = findViewById(R.id.btnReport);

        editId = findViewById(R.id.editId);
        editTgl = findViewById(R.id.editTgl);
        editUraian = findViewById(R.id.editKet);
        editDebit = findViewById(R.id.editDebit);
        editKredit = findViewById(R.id.editKredit);

        listTransaksi = new ArrayList<DataTrx>();

        customAdapter = new CustomAdapter(listTransaksi, this);
        lvTransaksi = findViewById(R.id.lvTransaksi);
        lvTransaksi.setAdapter(customAdapter);

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
                                editTgl.setText(i + "/" + (i1 + 1) + "/" + i2);
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


        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = editId.getText().toString();
                String tgl = editTgl.getText().toString();
                String uraian = editUraian.getText().toString();
                String debit = editDebit.getText().toString();
                String kredit = editKredit.getText().toString();


                Date date = null;
                try {
                    date = new SimpleDateFormat("yyyy/MM/dd").parse(tgl);
                } catch (ParseException e) {
                    throw new RuntimeException(e);
                }

                if (debit.isEmpty()) {
                    debit = "0";
                }

                if (kredit.isEmpty()) {
                    kredit = "0";
                }

                if (!id.isEmpty() && !tgl.isEmpty() && !uraian.isEmpty()) {
                    listTransaksi.add(new DataTrx(id, uraian, date, Integer.parseInt(debit), Integer.parseInt(kredit)));
                    Toast.makeText(MainActivity.this, "Berhasil menambahkan data!",
                            Toast.LENGTH_LONG).show();
                    customAdapter.notifyDataSetChanged();
                    reset();
                } else {
                    Toast.makeText(MainActivity.this, "Gagal menambahkan data!",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        btnReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ReportActivity.class);

                i.putExtra("trx", listTransaksi);
                startActivity(i);
            }
        });
    }
    public void reset(){
        editId.setText("");
        editTgl.setText("");
        editDebit.setText("");
        editKredit.setText("");
        editUraian.setText("");
    }
}
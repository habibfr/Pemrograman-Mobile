package com.habibfr.data_mhs_jmp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.habibfr.data_mhs_jmp.adapter.MahasiswaAdapter;
import com.habibfr.data_mhs_jmp.helper.DbHelper;
import com.habibfr.data_mhs_jmp.model.Mahasiswa;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class ViewActivity extends AppCompatActivity {
    private Toolbar tbInsertView;
    private ArrayList<Mahasiswa> mahasiswas = new ArrayList<>();
    private MahasiswaAdapter mahasiswaAdapter;
    private ListView lvMahasiswa;

    private DbHelper dbHelper = new DbHelper(this);

    public static final String COLUMN_ID = "id";
    public static final String COLUMN_NOMOR = "nomor";
    public static final String COLUMN_NAMA = "nama";
    public static final String COLUMN_ALAMAT = "alamat";
    public static final String COLUMN_TANGGAL_LAHIR = "tgl_lahir";
    public static final String COLUMN_JENIS_KELAMIN = "jenis_kelamin";

    private ExtendedFloatingActionButton fBtnAdd;
    AlertDialog.Builder dialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        tbInsertView = findViewById(R.id.tbInsertView);
        lvMahasiswa = findViewById(R.id.lvMhs);
        fBtnAdd = findViewById(R.id.fBtnAdd);

//        ActionBar actionBar = getSupportActionBar();
//        actionBar.setDisplayShowHomeEnabled(true);
//        actionBar.setDisplayHomeAsUpEnabled(true);
//        actionBar.setTitle("Data Mahasiswa");
        tbInsertView.setTitle("Data Mahasiswa");

        setSupportActionBar(tbInsertView);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        fBtnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ViewActivity.this, InputActivity.class);
                startActivity(intent);
            }
        });

        mahasiswaAdapter = new MahasiswaAdapter(ViewActivity.this, mahasiswas);
        lvMahasiswa.setAdapter(mahasiswaAdapter);
        getAllData();

        lvMahasiswa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                int idx = mahasiswas.get(i).getId();
                String nomor = mahasiswas.get(i).getNomor();
                String name = mahasiswas.get(i).getNama();
                String hbd = mahasiswas.get(i).getTglLahir();
                String jk = mahasiswas.get(i).getJenisKelamin();
                String alamat = mahasiswas.get(i).getAlamat();

                System.out.println(nomor + name + hbd + jk + alamat);
                final CharSequence[] dialogItem = {"Lihat Data", "Edit Data", "Hapus Data"};

                dialog = new AlertDialog.Builder(ViewActivity.this);
                dialog.setCancelable(true);
                dialog.setTitle("Pilih Aksi");
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(ViewActivity.this, DetailActivity.class);
                                Mahasiswa mhs = new Mahasiswa(idx, nomor, name, hbd, jk, alamat);
                                System.out.println("test" + mhs.getNomor());
//                                intent.putExtra(TAG_ID, idx);
//                                intent.putExtra(TAG_NAME, name);
//                                intent.putExtra(TAG_ADDRESS, alamat);
                                intent.putExtra("mahasiswa", mhs);
                                startActivity(intent);
                                break;
                            case 1:
//                                Toast.makeText(ViewActivity.this, "s" + i, Toast.LENGTH_SHORT).show();
                                Intent intent1 = new Intent(ViewActivity.this, InputActivity.class);
                                Mahasiswa mhs1 = new Mahasiswa(idx, nomor, name, hbd, jk, alamat);
//                                System.out.println("test" + mhs.getNomor());
//                                intent.putExtra(TAG_ID, idx);
//                                intent.putExtra(TAG_NAME, name);
//                                intent.putExtra(TAG_ADDRESS, alamat);
                                intent1.putExtra("mahasiswa", mhs1);
                                startActivity(intent1);
                                break;
                            case 2:
                                dbHelper.delete(idx);
                                mahasiswas.clear();
                                getAllData();
                                break;
                        }
                    }
                }).show();

//                return false;

            }
        });
    }

    public void getAllData() {
        ArrayList<HashMap<String, String>> row = dbHelper.getAllData();

        for (int i = 0; i < row.size(); i++) {
            int id = Integer.parseInt(row.get(i).get(COLUMN_ID));
            String nomor = row.get(i).get(COLUMN_NOMOR);
            String nama = row.get(i).get(COLUMN_NAMA);
            String hbd = row.get(i).get(COLUMN_TANGGAL_LAHIR);
            String jk = row.get(i).get(COLUMN_JENIS_KELAMIN);
            String alamat = row.get(i).get(COLUMN_ALAMAT);

            Mahasiswa data = new Mahasiswa(id, nomor, nama, hbd, jk, alamat);

//            data.setId(id);
//            data.setNama(nama);


            mahasiswas.add(data);
        }
        mahasiswaAdapter.notifyDataSetChanged();
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

    @Override
    protected void onResume() {
        super.onResume();
        mahasiswas.clear();
        getAllData();
    }
}
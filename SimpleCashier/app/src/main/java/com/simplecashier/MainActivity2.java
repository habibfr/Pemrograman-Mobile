package com.simplecashier;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;



public class MainActivity2 extends AppCompatActivity {

    private TextView receiver_msg, nt, nj, np, nr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        TextView tgl=findViewById(R.id.tgl);
        SimpleDateFormat sdf = new SimpleDateFormat("EEEE, dd MMMM yyyy 'at' HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        tgl.setText(currentDateandTime);

        receiver_msg = findViewById(R.id.received_value_id); // total
        nt =(TextView) findViewById(R.id.teh);
        nj = findViewById(R.id.jeruk);
        np = findViewById(R.id.pecel);
        nr = findViewById(R.id.rawon);
        // create the get Intent object
        Intent intent = getIntent();
        // receive the value by getStringExtra() method and
        // key must be same which is send by first activity
        String str = intent.getStringExtra("message_key");
        String teh = intent.getStringExtra("teh");
        String jeruk = intent.getStringExtra("jeruk");
        String pecel = intent.getStringExtra("pecel");
        String rawon = intent.getStringExtra("rawon");
        // display the string into textView

        nt.setText("Teh " + teh + " * 3000 = Rp. " + (Integer.parseInt(teh)*3000));
        nj.setText("Jeruk " + jeruk + " * 4000 = Rp. " + (Integer.parseInt(jeruk)*4000));
        np.setText("Pecel " + pecel + " * 10000 = Rp " + (Integer.parseInt(pecel)*10000));
        nr.setText("Rawon " + rawon + " * 12000 = Rp. " + (Integer.parseInt(rawon)*12000));

        receiver_msg.setText("Total Rp. " + str);
    }
}
package com.example.lat5formlogin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtUsername, txtPassword;
    Button btnSubmit;
    int percobaan = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtUsername =(EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

            btnSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    percobaan++;
                    if(!txtUsername.getText().toString().equalsIgnoreCase("admin")){
                        Toast.makeText(getApplicationContext(),"username salah",Toast.LENGTH_SHORT).show();
                        txtUsername.setText(null);
                        txtPassword.setText(null);
                    }else if(!txtPassword.getText().toString().equalsIgnoreCase("1234")){
                        Toast.makeText(getApplicationContext(),"password salah",Toast.LENGTH_SHORT).show();
                        txtPassword.setText(null);
                        txtUsername.setText(null);
                    }else{
                        Toast.makeText(getApplicationContext(),"Login Okee.",Toast.LENGTH_SHORT).show();
                    }

                    if(percobaan == 3){
                        btnSubmit.setEnabled(false);
                    }
                }
            });
    }
}
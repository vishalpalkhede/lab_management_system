package com.example.myapplication;


import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    EditText UsernameEt, PasswordEt;
    TextView newRegistration;

    Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        UsernameEt = (EditText)findViewById(R.id.etUserName);
        PasswordEt = (EditText)findViewById(R.id.etPassword);
        login = (Button)findViewById(R.id.btnLogin);

        final ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (networkInfo == null || !networkInfo.isConnected() || (networkInfo.getType() != ConnectivityManager.TYPE_WIFI&& networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)){
                    Toast toast = Toast.makeText(getApplicationContext(),"Connect to Network!!",Toast.LENGTH_LONG);
                    toast.show();
                }
                else {
                    String username = UsernameEt.getText().toString();
                    String password = PasswordEt.getText().toString();
                    String type = "login";
                    BackgroundWorker backgroundWorker = new BackgroundWorker(MainActivity.this);
                    backgroundWorker.execute(type, username, password);
                }

            }
        });

        newRegistration = findViewById(R.id.newReg);
         newRegistration.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Intent intent = new Intent(getApplicationContext(),RegistrationActivity.class);
                 startActivity(intent);

             }
         });


    }





    }


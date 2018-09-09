package com.example.tensoncai.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewByID(R.id.etName);
        Password = (EditText) findViewByID(R.id.etPassword);
        Info = (TextView) findViewById(R.id.tvinfo);
        Login = (EditText) findViewByID(R.id.LoginButton);




    }

    private void validate(String userName, String password)
    {
        if (userName == "Admin" && password == "1234")
        {
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        }
    }
}



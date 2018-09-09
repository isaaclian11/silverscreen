package com.example.tensoncai.firstapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.EditText;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity
{

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = (EditText) findViewByID(R.id.etName);
        Password = (EditText) findViewByID(R.id.etPassword);
        Info = (TextView) findViewById(R.id.tvinfo);
        Login = (Button) findViewByID(R.id.LoginButton);

        Info.setText("No of attempts remaining: 5");

        Login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                validate(Name.getText().toString(), Password.getText().toString());
            }
        }
        );



    }

    private void validate(String userName, String userPassword)
    {
        if ((userName == "Admin") && (userPassword == "1234"))
        {
            // intent is used to move from one activity to another activity
            Intent intent = new Intent(MainActivity.this, SecondActivity.class);
            startActivity(intent);
        }
        else
        {
            counter--;

            Info.setText("No of attempts remaining: " + String.valueOf(counter));

            if (counter == 0)
            {
                Login.setEnabled(false);
            }
        }
    }
}



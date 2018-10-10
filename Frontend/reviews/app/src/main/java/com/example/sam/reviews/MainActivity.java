package com.example.sam.reviews;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Request.Method;
import com.android.volley.VolleyLog;
import android.util.Log;
import com.example.sam.reviews.AppController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    Button writeRev, refRev;
    TextView title1, title2, title3, desc1, desc2, desc3, auth1, auth2, auth3;
    private String tag_string_req = "string_req";
    //private String url = "https://api.androidhive.info/volley/string_response.html";
    private TextView msgResponse;
    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        writeRev = (Button) findViewById(R.id.writeRev);
        refRev = (Button) findViewById(R.id.refRev);
        title1 = (TextView) findViewById(R.id.title1);
        title2 = (TextView) findViewById(R.id.title2);
        title3 = (TextView) findViewById(R.id.title3);
        desc1 = (TextView) findViewById(R.id.desc1);
        desc2 = (TextView) findViewById(R.id.desc2);
        desc3 = (TextView) findViewById(R.id.desc3);
        auth1 = (TextView) findViewById(R.id.auth1);
        auth2 = (TextView) findViewById(R.id.auth2);
        auth3 = (TextView) findViewById(R.id.auth3);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        refRev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                refRev();
            }
        });
        writeRev.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(getApplicationContext(), ReviewWrite.class));
            }
        });
    }

    private void refRev(){
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://api.androidhive.info/volley/string_response.html";
        //String url = "http://proj309-sb-07.misc.iastate.edu";

// Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        title1.setText(response.substring(0,20));
                        desc1.setText(response.substring(20,70));
                        auth1.setText(response.substring(70,85));
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //title1.setText("Error: " + error.toString());
                Toast.makeText(getApplicationContext(), "Error: " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

// Add the request to the RequestQueue.
        queue.add(stringRequest);
    }

    private void showProgressDialog(){
        if(!pDialog.isShowing())
            pDialog.show();
    }

    private void hideProgressDialog(){
        if (pDialog.isShowing())
            pDialog.hide();
    }
}

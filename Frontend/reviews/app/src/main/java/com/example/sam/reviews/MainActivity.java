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
    Button writeRev;
    private String tag_string_req = "string_req";
    private String url = "127.0.0.1:8888/login.php";
    private TextView msgResponse;
    private String TAG = MainActivity.class.getSimpleName();
    private ProgressDialog pDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        writeRev = (Button) findViewById(R.id.writeRev);
        pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.setCancelable(false);

        showProgressDialog();
        StringRequest strReq = new StringRequest(Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d(TAG, response.toString());
                msgResponse.setText(response.toString());
                hideProgressDialog();
            }
            }, new Response.ErrorListener(){
                @Override
                public void onErrorResponse(VolleyError error){
                    VolleyLog.d(TAG, "Error: " + error.getMessage());
                    hideProgressDialog();
                }
        });
        AppController.getmInstance().addToRequestQueue(strReq, tag_string_req);
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

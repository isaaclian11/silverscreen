package com.example.isaacsanga.login;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_6455;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;

public class Chat extends AppCompatActivity {

    Button  b2;
    EditText e1,e2;
    RecyclerView recyclerView;
    ArrayList<ChatModel> arrayList = new ArrayList<>();
    ChatAdapter chatAdapter;

    private WebSocketClient cc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        b2=(Button)findViewById(R.id.bt2);
        e2=(EditText)findViewById(R.id.et2);

        recyclerView = findViewById(R.id.tx1);

        chatAdapter = new ChatAdapter(getApplicationContext(), arrayList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(chatAdapter);

        recyclerView = findViewById(R.id.tx1);
        Draft[] drafts = {new Draft_6455()};
        String w = "http://10.26.50.117:8080/websocket/"+((CurrentUserInfo)getApplication()).getUsername();

        try {
            Log.d("Socket:", "Trying socket");
            cc = new WebSocketClient(new URI(w),(Draft) drafts[0]) {
            @Override
            public void onMessage(final String message) {

                final String msg = message;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        arrayList.add(new ChatModel(msg, false));
                        chatAdapter.notifyItemChanged(arrayList.size()-1);
                        chatAdapter.notifyItemRangeChanged(arrayList.size()-1, arrayList.size());
                        chatAdapter.notifyDataSetChanged();
                    }
                });

                }

                @Override
                public void onOpen(ServerHandshake handshake) {
                    Log.d("OPEN", "run() returned: " + "is connecting");
                }

                @Override
                public void onClose(int code, String reason, boolean remote) {
                    Log.d("CLOSE", "onClose() returned: " + reason);
                }

                @Override
                public void onError(Exception e)
                {
                    Log.d("Exception:", e.toString());
                }
            };
        }
        catch (URISyntaxException e) {
            Log.d("Exception:", e.getMessage().toString());
            e.printStackTrace();
        }
        cc.connect();




        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (e2.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "Empty message", Toast.LENGTH_LONG);
                } else {
                    try {
                        String message = e2.getText().toString();
                        e2.setText("");
                        cc.send( "@" + getIntent().getStringExtra("friendsUsername") + " " + message + "\n");
                    }
                    catch (Exception e)
                    {
                        Log.d("ExceptionSendMessage:", e.getMessage().toString());
                    }
                }
            }
        });
    }
}

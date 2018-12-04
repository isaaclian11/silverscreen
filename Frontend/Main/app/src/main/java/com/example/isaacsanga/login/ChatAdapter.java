package com.example.isaacsanga.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Layout;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
    Context mContext;
    ArrayList<ChatModel> arrayList;

    public ChatAdapter(Context mContext, ArrayList<ChatModel> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.chatrows, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {

        if(arrayList.get(i).isMyMessage){
            myViewHolder.chat.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            myViewHolder.chat.setText(arrayList.get(i).getMyMessage());
        }

        else{
            myViewHolder.chat.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            myViewHolder.chat.setText(arrayList.get(i).getMyMessage());
        }

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView chat;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            chat = itemView.findViewById(R.id.ChatText);
        }
    }
}

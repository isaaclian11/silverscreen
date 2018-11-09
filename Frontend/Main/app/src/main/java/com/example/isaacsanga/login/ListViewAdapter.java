package com.example.isaacsanga.login;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    Context mContext;
    List<Model> list;
    ArrayList<Model> arrayList;
    LayoutInflater inflater;

    public ListViewAdapter(Context mContext, List<Model> list) {
        this.mContext = mContext;
        this.list = list;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<Model>();
        arrayList.addAll(list);

    }

    private class ViewHolder{
        TextView name, desc, score;
        ImageView profile;
        Button reply;
    }



    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.row, null);

            holder.name = convertView.findViewById(R.id.Name);
            holder.desc = convertView.findViewById(R.id.getReply);
            holder.profile = convertView.findViewById(R.id.poster);
            holder.reply = convertView.findViewById(R.id.replyBtn);
            holder.score = convertView.findViewById(R.id.score);
            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(list.get(position).getName());
        holder.desc.setText(list.get(position).getDesc());
        holder.score.setText("Score: " + Integer.toString(list.get(position).getScore()));
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+list.get(position).getPoster()).into(holder.profile);
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, StatusReply.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("id", list.get(position).getId());
                intent.putExtra("user", list.get(position).getMe());
                mContext.startActivity(intent);
            }
        });


        return convertView;
    }
}

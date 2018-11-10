package com.example.isaacsanga.login;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ListViewAdapter extends RecyclerView.Adapter<ListViewAdapter.MyViewHolder>{
    private Context mContext;
    private List<Model> models;


    public ListViewAdapter(Context mContext, List<Model> models) {
        this.mContext = mContext;
        this.models = models;
    }

    @NonNull
    @Override
    public ListViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.updated_feed_row, viewGroup, false);

        return new ListViewAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+models.get(i).getPoster()).into(myViewHolder.moviePoster);
        myViewHolder.getReply.setText(models.get(i).getDesc());
        myViewHolder.Name.setText(models.get(i).getName());
        myViewHolder.score.setText("Score: " + Integer.toString(models.get(i).getScore()));
    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView getReply, Name, score;
        ImageView moviePoster;

        public MyViewHolder(View itemView){
            super(itemView);

            moviePoster = itemView.findViewById(R.id.feedPoster);
            getReply = itemView.findViewById(R.id.feedReview);
            Name = itemView.findViewById(R.id.feedName);
            score = itemView.findViewById(R.id.feedScore);
        }
    }






}
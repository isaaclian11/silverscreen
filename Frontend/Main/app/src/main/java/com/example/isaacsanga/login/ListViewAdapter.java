package com.example.isaacsanga.login;

import android.content.Context;
import android.content.Intent;
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
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.movieTitle.setText(models.get(i).getTitle());
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+models.get(i).getPoster()).into(myViewHolder.moviePoster);
        myViewHolder.getReply.setText(models.get(i).getDesc());
        myViewHolder.Name.setText(models.get(i).getName());
        myViewHolder.score.setText("Score: " + Integer.toString(models.get(i).getScore()));
        myViewHolder.reply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, StatusReply.class);
                intent.putExtra("id", Integer.toString(models.get(i).getId()));
                intent.putExtra("parentID", Integer.toString(models.get(i).parentID()));
                intent.putExtra("movie_title", models.get(i).getTitle());
                intent.putExtra("posterID", models.get(i).getPoster());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

    }


    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView movieTitle, getReply, Name, score;
        ImageView moviePoster, reply;

        public MyViewHolder(View itemView){
            super(itemView);
            movieTitle = itemView.findViewById(R.id.feedTitle);
            moviePoster = itemView.findViewById(R.id.feedPoster);
            getReply = itemView.findViewById(R.id.feedReview);
            Name = itemView.findViewById(R.id.feedName);
            score = itemView.findViewById(R.id.feedScore);
            reply = itemView.findViewById(R.id.replyButton);
        }
    }






}
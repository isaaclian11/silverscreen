package com.example.isaacsanga.login;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class LatestMovieListAdapter extends RecyclerView.Adapter<LatestMovieListAdapter.MyViewHolder>{
    private Context mContext;
    private List<MovieModel> models;


    public LatestMovieListAdapter(Context mContext, List<MovieModel> models) {
        this.mContext = mContext;
        this.models = models;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view;
        LayoutInflater layoutInflater = LayoutInflater.from(mContext);
        view = layoutInflater.inflate(R.layout.updatedmovierows, viewGroup, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.movieTitle.setText(models.get(i).getMovieTitle());
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+models.get(i).getMoviePoster()).into(myViewHolder.moviePoster);

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder{

        TextView movieTitle;
        ImageView moviePoster;

        public MyViewHolder(View itemView){
            super(itemView);

            movieTitle = itemView.findViewById(R.id.MovieTitle);
            moviePoster = itemView.findViewById(R.id.poster);


        }
    }
}

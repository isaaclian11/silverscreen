package com.example.isaacsanga.login;


import android.content.Context;
import android.content.Intent;
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
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, final int i) {
        myViewHolder.movieTitle.setText(models.get(i).getMovieTitle());
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+models.get(i).getMoviePoster()).into(myViewHolder.moviePoster);

        myViewHolder.moviePoster.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, CurrentMovie.class);
                intent.putExtra("movieID", Integer.toString(models.get(i).getMovieID()));
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

        TextView movieTitle;
        ImageView moviePoster;

        public MyViewHolder(View itemView){
            super(itemView);

            movieTitle = itemView.findViewById(R.id.MovieTitle);
            moviePoster = itemView.findViewById(R.id.poster);


        }
    }
}

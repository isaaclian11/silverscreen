package com.example.isaacsanga.login;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class LatestMovieListAdapter extends BaseAdapter {
    Context context;
    List<Model> model;
    LayoutInflater layoutInflater;

    public LatestMovieListAdapter(Context context, List<Model> list){
        this.context = context;
        layoutInflater = LayoutInflater.from(context);
        this.model = list;
    }
    @Override
    public int getCount() {
        return model.size();
    }

    @Override
    public Object getItem(int position) {
        return model.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    private class ViewHolder{
        TextView name, desc;
        ImageView profile;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView==null){
            convertView = layoutInflater.inflate(R.layout.movierows, null);
            viewHolder = new ViewHolder();
            viewHolder.desc = convertView.findViewById(R.id.movieSummary);
            viewHolder.name = convertView.findViewById(R.id.MovieTitle);
            viewHolder.profile = convertView.findViewById(R.id.poster);
            convertView.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        viewHolder.desc.setText(model.get(position).getDesc());
        viewHolder.name.setText(model.get(position).getName());
        new Connection(viewHolder, position).execute();
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return convertView;

    }
    public class Connection extends AsyncTask<Void, Void, Void>{

        int position;
        ViewHolder viewHolder;
        Bitmap bitmap;

        public Connection(ViewHolder viewHolder, int position){
            this.position = position;
            this.viewHolder = viewHolder;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                bitmap = BitmapFactory.decodeStream((InputStream)new URL("https://image.tmdb.org/t/p/w500"+model.get(position).poster).getContent());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            viewHolder.profile.setImageBitmap(bitmap);
        }

    }

}

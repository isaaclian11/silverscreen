package com.example.isaacsanga.login;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.zip.Inflater;

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

    public class ViewHolder{
        TextView name, desc;
        ImageView profile;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if(convertView==null){
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.row, null);

            holder.name = convertView.findViewById(R.id.Name);
            holder.desc = convertView.findViewById(R.id.getStatus);
            holder.profile = convertView.findViewById(R.id.picture);

            convertView.setTag(holder);
        }
        else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.name.setText(list.get(position).getName());
        holder.desc.setText(list.get(position).getDesc());

        holder.profile.setImageResource(list.get(position).getId());

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return convertView;
    }

    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        list.clear();
        if(charText.length()==0){
            list.addAll(arrayList);
        }
        else{
            for(Model model: arrayList){
                if(model.getName().toLowerCase(Locale.getDefault()).contains(charText)){
                    list.add(model);
                }
            }
        }
        notifyDataSetChanged();

    }
}

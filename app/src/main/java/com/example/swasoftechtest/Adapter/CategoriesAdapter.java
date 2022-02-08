package com.example.swasoftechtest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.swasoftechtest.Activity.AllCategories;
import com.example.swasoftechtest.Api.ApplicationConstant;
import com.example.swasoftechtest.R;
import com.example.swasoftechtest.response.AllInOnResponse;
import com.example.swasoftechtest.response.Datum;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {
    Context context;
    List<Datum> list;


    public CategoriesAdapter(Context context, List<Datum> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.cateories_model, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        if (position < 3) {

            holder.name.setText(list.get(position).getCateName().toString());

            Glide.with(context).load(ApplicationConstant.INSTANCE.baseUrl+"images/category/"+list.get(position).getImage()).into(holder.catImage);
        } else {
            holder.name.setText("All Categories");
            Glide.with(context).load("").into(holder.catImage);

        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (position == 3) {

                    Intent send = new Intent(context, AllCategories.class);
                    send.putExtra("name", "Categories");
                    context.startActivity(send);
                }
                else {
//                    if (list.get("po"))
//                    Intent send = new Intent(context, AllCategories.class);
//                    send.putExtra("name", "Categories");
//                    context.startActivity(send);
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return 4;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView catImage;
        TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            catImage = itemView.findViewById(R.id.catimge);
            name = itemView.findViewById(R.id.catext);
        }
    }
}

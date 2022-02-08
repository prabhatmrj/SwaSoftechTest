package com.example.swasoftechtest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.swasoftechtest.Activity.AllCategories;
import com.example.swasoftechtest.Activity.SubCategories;
import com.example.swasoftechtest.Api.ApplicationConstant;
import com.example.swasoftechtest.R;
import com.example.swasoftechtest.response.Datum;

import java.util.List;

public class AllCategoryAdapter extends RecyclerView.Adapter<AllCategoryAdapter.MyViewHolder> {


    Context context;
    List<Datum> list;


    public AllCategoryAdapter(Context context, List<Datum> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AllCategoryAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.all_cateories_model, parent, false);


        return new AllCategoryAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoryAdapter.MyViewHolder holder, int position) {


        holder.name.setText(list.get(position).getCateName().toString());

        Glide.with(context).load(ApplicationConstant.INSTANCE.baseUrl+"images/category/"+list.get(position).getImage()).into(holder.catImage);


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = list.get(position).getId().toString();
                String nameId=list.get(position).getCateName().toString();

                Intent intent = new Intent(context, SubCategories.class);
                intent.putExtra("idcat", id);
                intent.putExtra("idName", nameId);

                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView catImage;
        TextView name;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            catImage = itemView.findViewById(R.id.img);
            name = itemView.findViewById(R.id.nameView);
        }
    }
}



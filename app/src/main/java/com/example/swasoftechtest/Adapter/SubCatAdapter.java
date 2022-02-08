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
import com.example.swasoftechtest.Activity.ShopName;
import com.example.swasoftechtest.Activity.SubCategories;
import com.example.swasoftechtest.Api.ApplicationConstant;
import com.example.swasoftechtest.Home;
import com.example.swasoftechtest.MainActivity;
import com.example.swasoftechtest.R;
import com.example.swasoftechtest.response.Datum;

import java.util.ArrayList;

public class SubCatAdapter extends RecyclerView.Adapter<SubCatAdapter.MyViewHolder> {
    ArrayList<Datum> list;
    Context context;


    public SubCatAdapter(ArrayList<Datum> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sub_cateories_model, parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {


        holder.name.setText(list.get(position).getSubCategory().toString());

                Glide.with(context).load(ApplicationConstant.INSTANCE.baseUrl+"images/subcategory/"+list.get(position).getImage()).into(holder.catImage);
        Toast.makeText(context, ""+position, Toast.LENGTH_SHORT).show();

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String id = list.get(position).getId().toString();
//                String name = list.get(position).getId().toString();


                Intent intent = new Intent(context, ShopName.class);
//                intent.putExtra("id", id);
//                intent.putExtra("name", name);
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

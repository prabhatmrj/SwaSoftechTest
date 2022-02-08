package com.example.swasoftechtest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.ContentInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.swasoftechtest.Activity.AddVendorService;
import com.example.swasoftechtest.Api.ApplicationConstant;
import com.example.swasoftechtest.R;
import com.example.swasoftechtest.response.Datum;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.MyViewHolder> {

    Context context;
    List<Datum> list;

    public ShopAdapter(Context context, List<Datum> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.shop_item_model, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.shopeName.setText(list.get(position).getVendor_name().toString());
        holder.location.setText(list.get(position).getAddress().toString());
        holder.rate.setText(list.get(position).getPrice().toString());
      //  Glide.with(context).load(ApplicationConstant.INSTANCE.baseUrl+"images/subcategory/"+list.get(position).getImage()).into(holder.catImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String vendorId = list.get(position).getVendorId().toString();
                Intent intent = new Intent(context, AddVendorService.class);
                intent.putExtra("vendorId", vendorId);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView rate, location, shopeName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            shopeName = itemView.findViewById(R.id.shopeName);
            location = itemView.findViewById(R.id.location);
            rate = itemView.findViewById(R.id.rate);
        }
    }
}

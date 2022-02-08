package com.example.swasoftechtest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.swasoftechtest.Activity.AddVendorService;
import com.example.swasoftechtest.Activity.BookNowFragment;
import com.example.swasoftechtest.R;
import com.example.swasoftechtest.response.Datum;

import java.util.List;

public class ServiceAdapter extends RecyclerView.Adapter<ServiceAdapter.MyViewHolder> {
    Context context;
    List<Datum> list;


    public ServiceAdapter(Context context, List<Datum> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view= LayoutInflater.from(context).inflate(R.layout.service_model,parent, false);


        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.shopeName.setText(list.get(position).getVendor_name().toString());
       // holder.location.setText(list.get(position).getAddress().toString());
        holder.rate.setText(list.get(position).getPrice().toString());
        holder.book.setText("Book");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //BookNowFragment fragment=new BookNowFragment();
                Intent intent=new Intent(context,BookNowFragment.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class MyViewHolder  extends RecyclerView.ViewHolder{
        TextView shopeName,rate,book;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            shopeName=itemView.findViewById(R.id.nameView);
            rate=itemView.findViewById(R.id.rate);
            book=itemView.findViewById(R.id.book);



        }

    }
}

package com.example.swasoftechtest.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.example.swasoftechtest.R;
import com.example.swasoftechtest.response.AllInOnResponse;
import com.example.swasoftechtest.response.Datum;

import java.util.ArrayList;


public class ViewPagerAdapter extends PagerAdapter {

    ArrayList<Datum> list;

    Context mContext;
    LayoutInflater mLayoutInflater;
    int type;

    public ViewPagerAdapter(ArrayList<Datum> list, Context context) {

        this.list = list;
        this.mContext = context;

        mLayoutInflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public int getCount() {

        return list.size();
    }


    public boolean isViewFromObject(View view, Object object) {
        return view == ((LinearLayout) object);
    }


    public Object instantiateItem(ViewGroup container, int position) {
        View itemView = mLayoutInflater.inflate(R.layout.view_page_item, container, false);

        ImageView imageView = (ImageView) itemView.findViewById(R.id.imageView);

//        //Log.e("Image"," "+ApplicationConstant.INSTANCE.baseUrl+"/"+ImageList.get(position).getPath());
        //Glide.with(mContext).load("image/banner/" + list.get(position).getImage()).into(imageView);
        Glide.with(mContext).load("https://titoserviceprovider.in/images/banner/"+list.get(position).getImage().toString()).into(imageView);

        container.addView(itemView);
//ViewPager viewPager=(ViewPager) container;
//viewPager.addView(itemView);
        return itemView;
    }


    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((LinearLayout) object);
    }
}
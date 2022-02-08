package com.example.swasoftechtest;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.swasoftechtest.Adapter.CategoriesAdapter;
import com.example.swasoftechtest.Adapter.ViewPagerAdapter;
import com.example.swasoftechtest.Api.ApiClient;
import com.example.swasoftechtest.Api.EndPointInterFace;
import com.example.swasoftechtest.response.AllInOnResponse;
import com.example.swasoftechtest.response.Datum;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Home extends Fragment {


    public static TextView mDotsText[];
    List<AllInOnResponse> list;
    ViewPager mViewPager;
    LinearLayout dotsCount;
    Integer mDotsCount;
    ImageView imageView;
    Handler handler;
    RecyclerView recyclerView;
    CategoriesAdapter categoriesAdapter;
    Runnable runnable;
    ViewPagerAdapter pagerAdapterh;

    public Home() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_home, container, false);

        mViewPager = view.findViewById(R.id.pager);
        dotsCount = view.findViewById(R.id.image_count);
        recyclerView=view.findViewById(R.id.carecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),4));
        categoriesView();
        setViewPager();
        return view;

    }

    private void categoriesView() {
        EndPointInterFace git = ApiClient.getClient().create(EndPointInterFace.class);
        Call<List<AllInOnResponse>> call = git.getCategoies();
        call.enqueue(new Callback<List<AllInOnResponse>>() {
            @Override
            public void onResponse(Call<List<AllInOnResponse>> call, Response<List<AllInOnResponse>> response) {
                if (response.body() != null) {

                    ArrayList<Datum> value = new ArrayList<>();
                    for (AllInOnResponse data : response.body()) {
                        value = (ArrayList<Datum>) data.getData();
                    }
                    categoriesAdapter=new CategoriesAdapter(getContext(),value);
                    recyclerView.setAdapter(categoriesAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<AllInOnResponse>> call, Throwable t) {

            }
        });


    }

    private void setViewPager() {
        EndPointInterFace git = ApiClient.getClient().create(EndPointInterFace.class);
        Call<List<AllInOnResponse>> call = git.getBanner();
        call.enqueue(new Callback<List<AllInOnResponse>>() {
            @Override
            public void onResponse(Call<List<AllInOnResponse>> call, Response<List<AllInOnResponse>> response) {


                ArrayList<Datum> value = new ArrayList<>();
                if (response.body() != null) {
                    for (AllInOnResponse data : response.body()) {
                        value = (ArrayList<Datum>) data.getData();
                    }
//                    Bitmap bmp= BitmapFactory.decodeStream(getContext().getContentResolver()
//
//                            .openInputStream());


                    pagerAdapterh = new ViewPagerAdapter(value, getContext());

                    mViewPager.setAdapter(pagerAdapterh);
                    mViewPager.setOffscreenPageLimit(pagerAdapterh.getCount());

                    Integer mDotsCount;
                    postDelayedScrollNext();
                    mDotsCount = mViewPager.getAdapter().getCount();
                    mDotsText = new TextView[mDotsCount];

                    for (int i = 0; i < mDotsCount; i++) {
                        mDotsText[i] = new TextView(getContext());
                        mDotsText[i].setText("-");
                        mDotsText[i].setTextSize(50);
                        mDotsText[i].setTypeface(null, Typeface.BOLD);
                        mDotsText[i].setTextColor(getResources().getColor(R.color.black));
                        dotsCount.addView(mDotsText[i]);
                    }
                    //dots change color
                    mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                        @Override
                        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                            for (int i = 0; i < mDotsCount; i++) {
                                mDotsText[i].setTextColor(getResources().getColor(R.color.black));
                            }
                            mDotsText[position].setTextColor(getResources().getColor(R.color.second_color));
                        }

                        @Override
                        public void onPageSelected(int position) {

                        }

                        @Override
                        public void onPageScrollStateChanged(int state) {

                        }


                    });

                }

//                mViewPager.setOffscreenPageLimit(mViewPager.getCurrentItem());
            }

            @Override
            public void onFailure(Call<List<AllInOnResponse>> call, Throwable t) {
                Toast.makeText(getContext(), "faild", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void postDelayedScrollNext() {
        handler = new Handler();
        runnable = new Runnable() {
            public void run() {

                if (mViewPager.getAdapter() != null) {
                    if (mViewPager.getCurrentItem() == mViewPager.getAdapter().getCount() - 1) {
                        mViewPager.setCurrentItem(0);
                        postDelayedScrollNext();
                        return;
                    }
                    mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1);
                    // postDelayedScrollNext(position+1);
                    postDelayedScrollNext();
                }

                // onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
            }
        };
        handler.postDelayed(runnable, 2000);

    }

}
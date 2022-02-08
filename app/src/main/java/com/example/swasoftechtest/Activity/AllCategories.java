package com.example.swasoftechtest.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.swasoftechtest.Adapter.AllCategoryAdapter;
import com.example.swasoftechtest.Adapter.CategoriesAdapter;
import com.example.swasoftechtest.Api.ApiClient;
import com.example.swasoftechtest.Api.EndPointInterFace;
import com.example.swasoftechtest.R;
import com.example.swasoftechtest.response.AllInOnResponse;
import com.example.swasoftechtest.response.Datum;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllCategories extends AppCompatActivity {
    RecyclerView recyclerView;
    AllCategoryAdapter categoriesAdapter;
    TextView nameview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getSupportActionBar().hide();
        categoriesView();
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        nameview=findViewById(R.id.name);
        nameview.setText(name);
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
                    categoriesAdapter = new AllCategoryAdapter(AllCategories.this, value);
                    recyclerView.setAdapter(categoriesAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<AllInOnResponse>> call, Throwable t) {

            }
        });


    }

}
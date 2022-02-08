package com.example.swasoftechtest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.swasoftechtest.Activity.Profile;
import com.example.swasoftechtest.Adapter.ViewPagerAdapter;
import com.example.swasoftechtest.Api.ApiClient;
import com.example.swasoftechtest.Api.ApplicationConstant;
import com.example.swasoftechtest.Api.EndPointInterFace;
import com.example.swasoftechtest.response.AllInOnResponse;
import com.example.swasoftechtest.response.Datum;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    NavigationView nav;
    MainActivity activity;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;
    LinearLayout profile;
    String[] data;
    String img;
    TextView name, email;
    String nameSave, emailSave;
    public static TextView mDotsText[];
    List<AllInOnResponse> list;
    ViewPager mViewPager;
    LinearLayout dotsCount;
    Integer mDotsCount;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar);
        activity = this;

        getSupportActionBar().hide();
        nav = (NavigationView) findViewById(R.id.navbar);
        drawerLayout = findViewById(R.id.draw);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        fragmentChange(new Home());
        ArrayList<String> arrayList = new ArrayList<>();

        View header = nav.getHeaderView(0);
        profile = (LinearLayout) header.findViewById(R.id.profile);
        name = (TextView) header.findViewById(R.id.name);

        email = (TextView) header.findViewById(R.id.email);


        sharedPreferences = getSharedPreferences("LoginPref", MODE_PRIVATE);

        String type = sharedPreferences.getString("type", "");
        Toast.makeText(MainActivity.this, ""+type, Toast.LENGTH_SHORT).show();
        if (type.equalsIgnoreCase("vendor")){
            String id = sharedPreferences.getString("id", "");
            name.setText(sharedPreferences.getString("name", ""));

        }
        else {
            name.setText(sharedPreferences.getString("name", ""));
            email.setText(sharedPreferences.getString("email", ""));
            String id = sharedPreferences.getString("id", "");

        }
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Profile.class);
                startActivity(intent);
            }
        });
        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @SuppressLint("WrongConstant")
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.my_order:
                        fragmentChange(new MyOrder());
                        drawerLayout.closeDrawer(Gravity.START);
                        break;
                    case R.id.other_option:
                        fragmentChange(new OtherOption());
                        drawerLayout.closeDrawer(Gravity.START);
                        break;
                    case R.id.upgrade_option:
                        fragmentChange(new UpgradePackege());
                        drawerLayout.closeDrawer(Gravity.START);
                        break;
                    case R.id.about_us:
                        fragmentChange(new AboutUs());
                        drawerLayout.closeDrawer(Gravity.START);
                        break;
                    case R.id.refer_earn:
                        fragmentChange(new ReferEarn());
                        drawerLayout.closeDrawer(Gravity.START);
                        break;
                    case R.id.logout:

                        fragmentChange(new SignOut());

                        drawerLayout.closeDrawer(Gravity.START);
                        break;

                }

                return false;
            }
        });

    }


    private void fragmentChange(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame, fragment).setTransitionStyle(FragmentTransaction
                .TRANSIT_FRAGMENT_FADE).commit();
    }
}
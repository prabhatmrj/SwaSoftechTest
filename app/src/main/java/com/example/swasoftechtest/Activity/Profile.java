package com.example.swasoftechtest.Activity;

import static cn.pedant.SweetAlert.SweetAlertDialog.SUCCESS_TYPE;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.swasoftechtest.Adapter.ShopAdapter;
import com.example.swasoftechtest.Api.ApiClient;
import com.example.swasoftechtest.Api.EndPointInterFace;
import com.example.swasoftechtest.R;
import com.example.swasoftechtest.Request.RegisterRequest;
import com.example.swasoftechtest.Request.UpdateRequest;
import com.example.swasoftechtest.response.AllInOnResponse;
import com.example.swasoftechtest.response.Datum;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Profile extends AppCompatActivity {
    ImageView cam, imageView;
    int PICK_IMAGE_REQUEST = 1;
    Uri filePath;
    SweetAlertDialog alertDialog;
    SharedPreferences sharedPreferences;
    EditText name, email, address, mobile, dob;
    Button update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        cam = findViewById(R.id.profileImage);
        imageView = findViewById(R.id.imageView4);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        mobile = findViewById(R.id.mobile);
        dob = findViewById(R.id.dob);
        update=findViewById(R.id.update);


        sharedPreferences = getSharedPreferences("LoginPref", MODE_PRIVATE);
        name.setText(sharedPreferences.getString("name", ""));
        email.setText(sharedPreferences.getString("email", ""));
        String id = sharedPreferences.getString("id", "");
        String type = sharedPreferences.getString("type", "");
        Toast.makeText(Profile.this, ""+id, Toast.LENGTH_SHORT).show();
        cam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SelectImage();
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoriesView(id,type);
            }
        });

    }

    private void categoriesView(String id,String type) {
        if (type.equalsIgnoreCase("vendor")){
        EndPointInterFace git = ApiClient.getClient().create(EndPointInterFace.class);
        UpdateRequest request = new UpdateRequest(id,email.getText().toString(),name.getText().toString(),mobile.getText().toString()
        ,address.getText().toString(),dob.getText().toString(),"",String.valueOf(filePath));

        Call<List<AllInOnResponse>> call = git.getVendorUpdateProfile(id, request);

        call.enqueue(new Callback<List<AllInOnResponse>>() {
            @Override
            public void onResponse(Call<List<AllInOnResponse>> call, Response<List<AllInOnResponse>> response) {
                if (response.body() != null) {
                    alertDilog(response.body().get(0).getMessage().toString());

                }
                else {
                    alertDilog(response.body().get(0).getMessage().toString());

                }
            }

            @Override
            public void onFailure(Call<List<AllInOnResponse>> call, Throwable t) {

            }
        });
        }
        else {

            EndPointInterFace git = ApiClient.getClient().create(EndPointInterFace.class);
            UpdateRequest request = new UpdateRequest(id,email.getText().toString(), name.getText().toString(),
                    email.getText().toString(), mobile.getText().toString(), address.getText().toString()
                    , dob.getText().toString(), String.valueOf(filePath));

            Call<List<AllInOnResponse>> call = git.getUpdateProfile(id, request);

            call.enqueue(new Callback<List<AllInOnResponse>>() {
                @Override
                public void onResponse(Call<List<AllInOnResponse>> call, Response<List<AllInOnResponse>> response) {
                    if (response.body() != null) {
                        alertDilog(response.body().get(0).getMessage().toString());

                    }
                    else {
                        alertDilog(response.body().get(0).getMessage().toString());

                    }
                }

                @Override
                public void onFailure(Call<List<AllInOnResponse>> call, Throwable t) {

                }
            });
        }

    }

    private void alertDilog(String message) {

        try {
            alertDialog = new SweetAlertDialog(this);

            alertDialog.changeAlertType(SUCCESS_TYPE);
            alertDialog.setTitle(message);

            alertDialog.show();
        } catch (WindowManager.BadTokenException bte) {

        }

    }

    private void SelectImage() {

        // Defining Implicit Intent to mobile gallery
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(
                Intent.createChooser(
                        intent,
                        "Select Image from here..."),
                PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE_REQUEST
                && resultCode == RESULT_OK
                && data != null
                && data.getData() != null) {

            // Get the Uri of data
            filePath = data.getData();
            try {

                // Setting image on image view using Bitmap
                Bitmap bitmap = MediaStore
                        .Images
                        .Media
                        .getBitmap(
                                getContentResolver(),
                                filePath);
                imageView.setImageBitmap(bitmap);
            } catch (IOException e) {
                // Log the exception
                e.printStackTrace();
            }
        }
    }


}
package com.example.swasoftechtest.Activity;

import static cn.pedant.SweetAlert.SweetAlertDialog.SUCCESS_TYPE;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.swasoftechtest.Adapter.ServiceAdapter;
import com.example.swasoftechtest.Api.ApiClient;
import com.example.swasoftechtest.Api.EndPointInterFace;
import com.example.swasoftechtest.R;
import com.example.swasoftechtest.Request.RegisterRequest;
import com.example.swasoftechtest.response.AllInOnResponse;
import com.example.swasoftechtest.response.Datum;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class BookNowFragment extends AppCompatActivity {
RecyclerView recyclerView;
//BookAdapter bookAdapter;
SharedPreferences sharedPreferences;
    SweetAlertDialog alertDialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_book_now);



        sharedPreferences = getSharedPreferences("LoginPref", MODE_PRIVATE);
//        name.setText(sharedPreferences.getString("name", ""));
//        email.setText(sharedPreferences.getString("email", ""));
        String id = sharedPreferences.getString("id", "");
        String type = sharedPreferences.getString("type", "");
        bookNow(id);
    }

    private void bookNow(String id) {
        EndPointInterFace git = ApiClient.getClient().create(EndPointInterFace.class);
        RegisterRequest request=new RegisterRequest("1");

        Call<List<AllInOnResponse>> call = git.bookNow(id,"3");
        call.enqueue(new Callback<List<AllInOnResponse>>() {
            @Override
            public void onResponse(Call<List<AllInOnResponse>> call, Response<List<AllInOnResponse>> response) {
                if (response.body() != null) {
                    String message = "null";
                    ArrayList<Datum> value = new ArrayList<>();
                    for (AllInOnResponse data : response.body()) {
                        value = (ArrayList<Datum>) data.getData();
                    message =data.getMessage().toString();
                   alertDilog(message);
                    }
                    Toast.makeText(BookNowFragment.this, ""+message, Toast.LENGTH_SHORT).show();

//                    categoriesAdapter = new ServiceAdapter(getContext(), value);
//                    recyclerView.setAdapter(categoriesAdapter);

                }
            }

            @Override
            public void onFailure(Call<List<AllInOnResponse>> call, Throwable t) {

            }
        });


    }

    private void alertDilog(String message) {

        try {
            alertDialog= new SweetAlertDialog(this);

            alertDialog.changeAlertType(SUCCESS_TYPE);
            alertDialog.setTitle(message);

            alertDialog.show();
        } catch (WindowManager.BadTokenException bte) {

        }

    }

}
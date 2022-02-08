package com.example.swasoftechtest.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.swasoftechtest.Api.ApiClient;
import com.example.swasoftechtest.Api.EndPointInterFace;
import com.example.swasoftechtest.MainActivity;
import com.example.swasoftechtest.R;
import com.example.swasoftechtest.Request.RegisterRequest;
import com.example.swasoftechtest.Request.UserRequest;
import com.example.swasoftechtest.UtilMethod;
import com.example.swasoftechtest.response.AllInOnResponse;
import com.example.swasoftechtest.response.Datum;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    EditText mobile, password;
    Spinner spinner;
    SharedPreferences sharedPreferences;
    Button submit, allHaveAnAccount;
    SharedPreferences getSharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);
        spinner = findViewById(R.id.type);
        submit = findViewById(R.id.submitBtn);
        allHaveAnAccount = findViewById(R.id.allReadyBtn);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.array_list,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String data = parent.getItemAtPosition(position).toString();
                registerNow(data);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        allHaveAnAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

    }

    private void registerNow(String data) {

        if (data.equalsIgnoreCase("Vendor")) {
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EndPointInterFace git = ApiClient.getClient().create(EndPointInterFace.class);
                    RegisterRequest request = new RegisterRequest(mobile.getText().toString(),
                            password.getText().toString(), data);
                    Call<List<AllInOnResponse>> call = git.getLogin(request);
                    call.enqueue(new Callback<List<AllInOnResponse>>() {
                        @Override
                        public void onResponse(Call<List<AllInOnResponse>> call, Response<List<AllInOnResponse>> response) {
                            if (response != null) {
                                List<AllInOnResponse> value = new ArrayList<>();
                                for (AllInOnResponse data : response.body()) {
                                    value.add(data);

                                }
                                if(value.get(0).getMessage().toString().equalsIgnoreCase("you are successfully login")){
                                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                    Toast.makeText(LoginActivity.this, ""+value.get(0).getMessage().toString(), Toast.LENGTH_SHORT).show();
                                    String name=value.get(0).getData().get(0).getUsername().toString();
                                    String email="value.get(0).getData().get(0).getEmail().toString()";
                                    String id=value.get(0).getData().get(0).getId().toString();
                                    String type=value.get(0).getData().get(0).getType().toString();
                                    saveLogin(name,email,id,type);
                                }
                                else {
                                    Toast.makeText(LoginActivity.this, ""+value.get(0).getMessage().toString(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<AllInOnResponse>> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "okk" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            });

        } else {
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EndPointInterFace git = ApiClient.getClient().create(EndPointInterFace.class);
                    UserRequest request = new UserRequest(data, mobile.getText().toString(),
                            password.getText().toString());
                    Call<List<AllInOnResponse>> call = git.getLoginUser(request);
                    call.enqueue(new Callback<List<AllInOnResponse>>() {
                        @Override
                        public void onResponse(Call<List<AllInOnResponse>> call, Response<List<AllInOnResponse>> response) {
                            if (response != null) {
                                List<AllInOnResponse> value = new ArrayList<>();
                                for (AllInOnResponse data : response.body()) {
                                    value.add(data);

                                }
                                if(value.get(0).getMessage().toString().equalsIgnoreCase("you are successfully login")){
                                startActivity(new Intent(LoginActivity.this,MainActivity.class));
                                    Toast.makeText(LoginActivity.this, ""+value.get(0).getMessage().toString(), Toast.LENGTH_SHORT).show();
                                    String name=value.get(0).getData().get(0).getUsername().toString();
                                    String email=value.get(0).getData().get(0).getEmail().toString();
                                    String id=value.get(0).getData().get(0).getId().toString();
                                    String type=value.get(0).getData().get(0).getType().toString();
                                    saveLogin(name,email,id,type);
                                }
                                else {
                                    Toast.makeText(LoginActivity.this, ""+value.get(0).getMessage().toString(), Toast.LENGTH_SHORT).show();

                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<List<AllInOnResponse>> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, "okk" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            });

        }

    }

    private void saveLogin(String name,String email,String id,String type) {

        sharedPreferences = getSharedPreferences("LoginPref", MODE_PRIVATE);
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        myEdit.putString("mobile", mobile.getText().toString());
        myEdit.putString("pass", password.getText().toString());
        myEdit.putString("name",name);
        myEdit.putString("email",email);
        myEdit.putString("id",id);
        myEdit.putString("type",type);
        myEdit.commit();
    }

}
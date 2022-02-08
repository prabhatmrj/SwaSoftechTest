package com.example.swasoftechtest.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.example.swasoftechtest.R;
import com.example.swasoftechtest.Request.RegisterRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    EditText name, email, mobile, password, state, country, city;
    Spinner spinner;
    Button submit, allReadyAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        mobile = findViewById(R.id.mobile);
        password = findViewById(R.id.password);
        state = findViewById(R.id.state);
        country = findViewById(R.id.countary);
        spinner = findViewById(R.id.type);
        submit = findViewById(R.id.submitBtn);
        allReadyAccount = findViewById(R.id.allReadyBtn);
        city = findViewById(R.id.city);
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

        allReadyAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
            }
        });
    }

    private void registerNow(String data) {
        if (data.equalsIgnoreCase("Vendor")) {
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EndPointInterFace git = ApiClient.getClient().create(EndPointInterFace.class);
                    RegisterRequest request = new RegisterRequest(name.getText().toString(),
                            email.getText().toString(), mobile.getText().toString(),
                            password.getText().toString(), city.getText().toString()
                            , state.getText().toString(), country.getText().toString(), data);
                    Call<String> call = git.getVendorRegister(request);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response != null) {
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));

                                Toast.makeText(RegisterActivity.this, "okk", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "okk" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            });

        } else {
            submit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    EndPointInterFace git = ApiClient.getClient().create(EndPointInterFace.class);
                    RegisterRequest request = new RegisterRequest(name.getText().toString(),
                            email.getText().toString(), mobile.getText().toString(),
                            password.getText().toString(), city.getText().toString()
                            , state.getText().toString(), country.getText().toString(), data);
                    Call<String> call = git.getUserRegister(request);
                    call.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            if (response != null) {
                                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                Toast.makeText(RegisterActivity.this, "okk", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable t) {
                            Toast.makeText(RegisterActivity.this, "okk" + t.getMessage().toString(), Toast.LENGTH_SHORT).show();

                        }
                    });

                }
            });

        }

    }
}
package com.example.swasoftechtest.Api;

import com.example.swasoftechtest.Request.LoginRequest;
import com.example.swasoftechtest.Request.RegisterRequest;
import com.example.swasoftechtest.Request.UpdateRequest;
import com.example.swasoftechtest.Request.UserRequest;
import com.example.swasoftechtest.response.AllInOnResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PartMap;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface EndPointInterFace {


    @Headers("Content-Type: application/json")
    @POST("api/vendor-register")
    Call<String> getVendorRegister(@Body RegisterRequest request);


    @Headers("Content-Type: application/json")
    @POST("api/user-register")
    Call<String> getUserRegister(@Body RegisterRequest request);

    @Headers("Content-Type: application/json")
    @POST("api/add-book-now/{id}/{vendor_id}")
    Call<List<AllInOnResponse>> bookNow(
            @Path("id") String id,
            @Path("vendor_id") String vendor_id

    );

    @Headers("Content-Type: application/json")
    @POST("api/add-sub-category")
    Call<String> subCategories(@Body RegisterRequest request);

    @Headers("Content-Type: application/json")
    @POST("api/fetch-category-sb")
    Call<List<AllInOnResponse>> fatchCatSub(@Body RegisterRequest request);

    @Headers("Content-Type: application/json")
    @POST("api/login")
    Call<List<AllInOnResponse>> getLogin(@Body RegisterRequest request);

    @Headers("Content-Type: application/json")
    @POST("api/login")
    Call<List<AllInOnResponse>> getLoginUser(@Body UserRequest request);

    @Headers("Content-Type: application/json")
    @GET("api/fetch-banner")
    Call<List<AllInOnResponse>> getBanner();


    @Headers("Content-Type: application/json")
    @GET("api/fetch-category")
    Call<List<AllInOnResponse>> getCategoies();

    @Headers("Content-Type: application/json")
    @POST("api/fetch-vendor-service2/{id}")
    Call<List<AllInOnResponse>> getVendorService2(
            @Path("id") String id,
            @Body RegisterRequest request
    );

    @Headers("Content-Type: application/json")
    @POST("api/update-user-profile/{id}")
    Call<List<AllInOnResponse>> getUpdateProfile(
            @Path("id") String id,
            @Body UpdateRequest request
    );
    @Headers("Content-Type: application/json")
    @POST("api/update-vendor-profile/{id}")
    Call<List<AllInOnResponse>> getVendorUpdateProfile(
            @Path("id") String id,
            @Body UpdateRequest request
    );

    @GET("api/fetch-vendor-service/{id}")
    Call<List<AllInOnResponse>> getVendorService(
            @Path("id") String id

    );
}

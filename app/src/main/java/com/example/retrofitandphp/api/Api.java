package com.example.retrofitandphp.api;

import androidx.annotation.Nullable;

import com.example.retrofitandphp.models.DefaultResponse;
import com.example.retrofitandphp.models.LoginResponse;
import com.example.retrofitandphp.models.UserResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface Api {

    @FormUrlEncoded
    @POST("createuser")
    Call<DefaultResponse> createUser(
           @Nullable @Field("name")String name,
           @Nullable @Field("password")String password,
           @Nullable @Field("email")String email
    );


    @FormUrlEncoded
    @POST("userLogin")
    Call<LoginResponse> userLogin(
            @Nullable @Field("password")String password,
            @Nullable @Field("email")String email
    );

    @GET("getAll")
    Call<UserResponse> getUsers();

    @FormUrlEncoded
    @POST("addAddress")
    Call<DefaultResponse> addAddressFromMap(
            @Nullable @Field("fullAddress")String fullAddress,
            @Nullable @Field("country")String country,
            @Nullable @Field("state")String state,
            @Nullable @Field("city")String city,
            @Nullable @Field("lng") String lng,
            @Nullable @Field("lat") String lat
    );
    @FormUrlEncoded
    @POST("addImage")
    Call<DefaultResponse> addImage(
        @Nullable @Field("image1") String imageOne,
        @Nullable @Field("image2") String imageTow,
        @Nullable @Field("user_id") String userId

    );
}

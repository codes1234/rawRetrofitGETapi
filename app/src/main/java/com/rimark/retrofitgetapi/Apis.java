package com.rimark.retrofitgetapi;

import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apis {
    //get_response.aspx
    @GET("get_response.aspx")
    Call<JsonObject> login(@Query(value = "mt_key", encoded = true) String mt_key,
                           @Query(value = "loginId", encoded = true) String loginId,
                           @Query(value = "passwrd", encoded = true) String passwrd,
                           @Query(value = "app_ver", encoded = true) String app_ver
    );
}

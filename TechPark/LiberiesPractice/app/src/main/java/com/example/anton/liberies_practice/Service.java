package com.example.anton.liberies_practice;

import retrofit2.Call;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Service {
    @GET("community/{number}")
   Call<ResponseBody> getUser(@Path("number") String number);
}

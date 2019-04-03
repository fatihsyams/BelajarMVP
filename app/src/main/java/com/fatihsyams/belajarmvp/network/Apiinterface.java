package com.fatihsyams.belajarmvp.network;

import com.fatihsyams.belajarmvp.response.ResponseBerita;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Apiinterface {
    @GET("/v2/everything?q=bitcoin&from=2019-02-23&sortBy=publishedAt&apiKey=eaa27a5fa59a44e0b7b718e1e6ab57dd")
    Call<ResponseBerita> getBerita(
    );

    @GET("everything")
    Call<ResponseBerita> getSearch(
            @Query("q") String keyword,
            @Query("language") String language,
            @Query("sortBy") String sortBy,
            @Query("apiKey") String apiKey
    );

}

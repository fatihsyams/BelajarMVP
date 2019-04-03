package com.fatihsyams.belajarmvp.network;

import com.fatihsyams.belajarmvp.response.LoginBody;
import com.fatihsyams.belajarmvp.response.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiInterfaceLogin {
    @POST("/api/login")
    Call<LoginResponse> postLogin(
            @Body LoginBody loginBody
            );
}

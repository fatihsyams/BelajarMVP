package com.fatihsyams.belajarmvp.login;

import com.fatihsyams.belajarmvp.network.ApiClientLogin;
import com.fatihsyams.belajarmvp.network.ApiInterfaceLogin;
import com.fatihsyams.belajarmvp.response.LoginBody;
import com.fatihsyams.belajarmvp.response.LoginResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPrensenter implements LoginContract.Presenter {
    private  final LoginContract.View view;
    private ApiInterfaceLogin apiInterfaceLogin = ApiClientLogin.getClient().create(ApiInterfaceLogin.class);

    public LoginPrensenter(LoginContract.View view) {
        this.view = view;
    }

    @Override
    public void doLogin(String email, String password) {
        if(email == null || email.isEmpty()) {
            view.loginFailure("Email Is Empty");
            return;
        }
        if(password == null || password.isEmpty()){
            view.loginFailure("Password is Empty");
            return;
        }
        view.showProgress();
        LoginBody loginBody = new LoginBody();
        loginBody.setPassword(password);
        loginBody.setEmail(email);

        Call<LoginResponse> call = apiInterfaceLogin.postLogin(loginBody);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                view.hideProgress();

                if(response.body() != null) {
                    LoginResponse loginResponse = response.body();
                    if(loginResponse.getToken() != null) {
                        view.loginSucces(loginResponse.getToken());
                    }
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                view.hideProgress();
                view.loginFailure("msg");
            }
        });
    }
}

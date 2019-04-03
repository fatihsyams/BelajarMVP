package com.fatihsyams.belajarmvp.login;

public interface LoginContract {
    interface  View {
        void showProgress();
        void hideProgress();
        void loginFailure(String msg);
        void loginSucces (String token);
    }
    interface Presenter{
        void doLogin (String email, String password);
    }

}

package com.fatihsyams.belajarmvp.main;

import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;

import com.fatihsyams.belajarmvp.R;
import com.fatihsyams.belajarmvp.network.ApiClient;
import com.fatihsyams.belajarmvp.network.Apiinterface;
import com.fatihsyams.belajarmvp.response.ResponseBerita;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter implements MainContract.Presenter {
    private final MainContract.View view;
    private Apiinterface apiInterface = ApiClient.getClient().create(Apiinterface.class);
    MainPresenter(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataListBerita(final String keyword) {
        view.ShowProgress();
        Call<ResponseBerita> call;
        call = apiInterface.getBerita();

        call.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                view.HideProgress();
                if(response.body() != null){
                    view.ShowDataList(response.body().getArticles());
                }
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                view.HideProgress();
                view.ShowFailureMassage(t.getMessage());
            }
        });
    }

    @Override
    public void searchTeams(String search) {

    }

}
















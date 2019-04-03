package com.fatihsyams.belajarmvp.detail;

import android.os.Bundle;

import com.fatihsyams.belajarmvp.network.ApiClient;
import com.fatihsyams.belajarmvp.network.Apiinterface;
import com.fatihsyams.belajarmvp.response.ArticlesItem;
import com.fatihsyams.belajarmvp.response.ResponseBerita;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailPresenter implements DetailContract.Presenter {

    private final DetailContract.View view;
    private Apiinterface apiinterface = ApiClient.getClient().create(Apiinterface.class);
    private String title;

    public DetailPresenter(DetailContract.View view) {
        this.view = view;
    }

    @Override
    public void getDataSingle(Bundle bundle) {
        if (bundle != null) {
            title = bundle.getString("id");
        }
        view.showProgress();
        Call<ResponseBerita> call = apiinterface.getBerita();
        call.enqueue(new Callback<ResponseBerita>() {
            @Override
            public void onResponse(Call<ResponseBerita> call, Response<ResponseBerita> response) {
                view.hideProgress();
                if(response.body() != null){
                    List<ArticlesItem>articlesItemList = response.body().getArticles();
                    ArticlesItem articlesItem = articlesItemList.get(0);
                    view.ShowDataSingle(articlesItem);
                    }
            }

            @Override
            public void onFailure(Call<ResponseBerita> call, Throwable t) {
                view.hideProgress();
                view.showFailureMessage(t.getMessage());
            }
        });
    }
}

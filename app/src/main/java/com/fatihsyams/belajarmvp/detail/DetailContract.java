package com.fatihsyams.belajarmvp.detail;

import android.os.Bundle;

import com.fatihsyams.belajarmvp.response.ArticlesItem;

public interface DetailContract {
    interface View {
        void showProgress();
        void hideProgress();
        void ShowDataSingle(ArticlesItem articlesItem);
        void showFailureMessage (String msg);
    }
    interface Presenter{
        void getDataSingle(Bundle bundle);
    }
}

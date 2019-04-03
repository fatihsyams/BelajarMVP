package com.fatihsyams.belajarmvp.main;

import android.view.Menu;
import android.widget.SearchView;

import com.fatihsyams.belajarmvp.response.ArticlesItem;

import java.util.List;

public interface MainContract {
    interface View{
        void ShowProgress();
        void HideProgress();
        void ShowDataList(List<ArticlesItem> articlesItems);

        void ShowFailureMassage(String msg);
        void SearchDataList(Menu menu);
        void ShowSearch(Menu menu);
        void ShowMenu(Menu menu);
    }
    interface Presenter {
        void getDataListBerita(final String keyword);
        void searchTeams(String search);


    }
}

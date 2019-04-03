package com.fatihsyams.belajarmvp.main;

import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fatihsyams.belajarmvp.R;
import com.fatihsyams.belajarmvp.adapter.MainAdapter;
import com.fatihsyams.belajarmvp.network.Apiinterface;
import com.fatihsyams.belajarmvp.response.ArticlesItem;
import com.fatihsyams.belajarmvp.response.ResponseBerita;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements MainContract.View {

    @BindView(R.id.rv_berita)
    RecyclerView rvBerita;
    @BindView(R.id.edtSearchMovie)
    EditText edtSearchMovie;
    @BindView(R.id.btnSearchMovie)
    Button btnSearchMovie;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout refreshLayout;

    private ProgressDialog progressDialog;
    private MenuInflater inflater;
    private SearchManager manager;
    private SearchView searchView;
    private MenuItem menuItem;
    private final MainPresenter presenter = new MainPresenter(this);
    private MainContract.View view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        presenter.getDataListBerita("");
        searchTeams();

    }

    private void searchTeams() {
        btnSearchMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchText = edtSearchMovie.getText().toString().toLowerCase();
                presenter.searchTeams(searchText);
            }
        });
    }

    @Override
    public void ShowProgress() {
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading");
        progressDialog.setCancelable(false);
        progressDialog.show();
    }

    @Override
    public void HideProgress() {
        progressDialog.dismiss();
    }


    @Override
    public void ShowDataList(List<ArticlesItem> articlesItems) {
        rvBerita.setLayoutManager(new LinearLayoutManager(this));
        rvBerita.setAdapter(new MainAdapter(this, articlesItems));
    }


    @Override
    public void ShowSearch(Menu menu) {
        menuItem = menu.findItem(R.id.action_search);
        menuItem.getIcon().setVisible(false, false);
    }

    @Override
    public void ShowMenu(Menu menu) {
        manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        searchView.setQueryHint("Search Latest News.......");
    }

    @Override
    public void ShowFailureMassage(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void SearchDataList(Menu menu) {
        if (!search.isEmpty()) {
            view.ShowProgress();
            Call<ResponseBerita> call = Apiinterface.(Constant.S, Constant.C);
            call.enqueue(new Callback<ResponseTeams>() {
                @Override
                public void onResponse(Call<ResponseTeams> call, Response<ResponseTeams> response) {
                    if (response.body() != null) {
                        List<TeamsItem> teamsItemList = response.body().getTeams();
                        List<TeamsItem> mTeamsList = new ArrayList<>();

                        for (TeamsItem data : teamsItemList) {
                            String namaStd = data.getStrTeam().toLowerCase();
                            if (namaStd.contains(search)) {
                                mTeamsList.add(data);
                            }
                        }
                        view.showFailureMassage("Tidak Ada Teams");
                    }
                }

            }

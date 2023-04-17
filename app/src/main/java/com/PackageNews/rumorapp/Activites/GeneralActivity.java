package com.PackageNews.rumorapp.Activites;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;

import com.PackageNews.rumorapp.Adapters.NewsAdapter;
import com.PackageNews.rumorapp.Models.Article;
import com.PackageNews.rumorapp.Models.News;
import com.PackageNews.rumorapp.R;
import com.PackageNews.rumorapp.Utils.ApiClient;
import com.PackageNews.rumorapp.Utils.ApiInterface;
import com.PackageNews.rumorapp.Utils.Util;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GeneralActivity extends AppCompatActivity {

    public final String API_KEY = "1ab97031f1b04eb7bb33eb3abf32748e";

    private List<Article> articles = new ArrayList<>();
    private NewsAdapter adapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general);

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerone);
        layoutManager = new LinearLayoutManager(getApplicationContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        LoadJson();

    }

    public void LoadJson(){



//        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

//        String country = Util.getCountry();
//
//        Call<News> call;
//
//        call = apiInterface.getNews("in", API_KEY);
//        call.enqueue(new Callback<News>() {
//            @Override
//            public void onResponse(Call<News> call, Response<News> response) {
//                if (response.isSuccessful() && response.body().getArticle() != null){
//
//                    if (!articles.isEmpty()){
//                        articles.clear();
//                    }
//
//                    articles = response.body().getArticle();
//                    adapter = new NewsAdapter(getApplicationContext(),articles);
//                    mRecyclerView.setAdapter(adapter);
//                    adapter.notifyDataSetChanged();
//
//
//                }
//            }
//
//            @Override
//            public void onFailure(Call<News> call, Throwable t) {
//            }
//        });

    }
}
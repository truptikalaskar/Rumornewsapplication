package com.PackageNews.rumorapp.Fragments;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;

import androidx.core.app.ActivityOptionsCompat;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.PackageNews.rumorapp.Adapters.NewsAdapter;
import com.PackageNews.rumorapp.Models.Article;
import com.PackageNews.rumorapp.Models.News;
import com.PackageNews.rumorapp.R;
import com.PackageNews.rumorapp.Utils.ApiClient;
import com.PackageNews.rumorapp.Utils.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BusinessNewsFragment extends Fragment {

    public final String API_KEY = "1ab97031f1b04eb7bb33eb3abf32748e";

    private List<Article> articles = new ArrayList<>();
    private NewsAdapter adapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;

    public BusinessNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_news, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        LoadJson();

        return view;
    }

    public void LoadJson(){


        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        Call<News> call;

        call = apiInterface.getHeadlines("business","in", API_KEY);
        call.enqueue(new Callback<News>() {
            @Override
            public void onResponse(Call<News> call, Response<News> response) {
                if (response.isSuccessful() && response.body().getArticle() != null){

                    if (!articles.isEmpty()){
                        articles.clear();
                    }

                    articles = response.body().getArticle();
                    adapter = new NewsAdapter(getContext(),articles);
                    mRecyclerView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                }
            }

            @Override
            public void onFailure(Call<News> call, Throwable t) {
            }
        });

    }

}
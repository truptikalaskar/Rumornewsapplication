package com.PackageNews.rumorapp.Fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

public class TechNewsFragment extends Fragment {

    public final String API_KEY = "1ab97031f1b04eb7bb33eb3abf32748e";

    private List<Article> articles = new ArrayList<>();
    private NewsAdapter adapter;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager layoutManager;

    public TechNewsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_news, container, false);

        mRecyclerView = view.findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        LoadJson();

        return view;
    }

    public void LoadJson(){

        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        String category = Util.getCategory();

        Call<News> call;

        call = apiInterface.getHeadlines("technology","in", API_KEY);
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

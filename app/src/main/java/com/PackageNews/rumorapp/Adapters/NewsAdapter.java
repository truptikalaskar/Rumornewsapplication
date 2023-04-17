package com.PackageNews.rumorapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.PackageNews.rumorapp.Activites.NewsDetailActivity;
import com.PackageNews.rumorapp.Models.Article;
import com.PackageNews.rumorapp.R;
import com.PackageNews.rumorapp.Utils.Util;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder>{

    private List<Article> articles;
    private Context mContext;

    public NewsAdapter(Context context,List<Article> articles) {
        this.mContext = context;
        this.articles = articles;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.news_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holders, int position) {
        final MyViewHolder holder = holders;
        Article model = articles.get(position);

        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(Util.getRandomDrawbleColor());
        requestOptions.error(Util.getRandomDrawbleColor());
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        requestOptions.centerCrop();

        Glide.with(mContext)
                .load(model.getUrlToImage())
                .apply(requestOptions)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        holder.progressBar.setVisibility(View.GONE);
                        return false;
                    }
                })
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(holder.imageView);

        holder.title.setText(model.getTitle());
        holder.desc.setText(model.getDescription());
        holder.source.setText(model.getSource().getName());
        holder.time.setText(" \u2022 " + Util.DateToTimeFormat(model.getPublishedAt()));
        holder.published_ad.setText(Util.DateFormat(model.getPublishedAt()));
        holder.author.setText(model.getAuthor());

    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, desc, author, published_ad, source, time;
        ImageView imageView;
        ProgressBar progressBar;

        public MyViewHolder(View itemView) {

            super(itemView);

            title = itemView.findViewById(R.id.title);
            desc = itemView.findViewById(R.id.desc);
            author = itemView.findViewById(R.id.author);
            published_ad = itemView.findViewById(R.id.publishedAt);
            source = itemView.findViewById(R.id.source);
            time = itemView.findViewById(R.id.time);
            imageView = itemView.findViewById(R.id.img_news);
            progressBar = itemView.findViewById(R.id.prograss_load_photo);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(mContext, NewsDetailActivity.class);
                    intent.putExtra("url", articles.get(getAdapterPosition()).getUrl());
                    intent.putExtra("title",articles.get(getAdapterPosition()).getTitle() );
                    intent.putExtra("img",  articles.get(getAdapterPosition()).getUrlToImage());
                    intent.putExtra("date",  articles.get(getAdapterPosition()).getPublishedAt());
                    intent.putExtra("source",  articles.get(getAdapterPosition()).getSource().getName());
                    intent.putExtra("author",  articles.get(getAdapterPosition()).getAuthor());

                    mContext.startActivity(intent);
                }
            });

        }
    }
}
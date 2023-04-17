package com.PackageNews.rumorapp.Activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.PackageNews.rumorapp.R;
import com.google.android.material.tabs.TabLayout;
import com.PackageNews.rumorapp.Adapters.NewsPagerAdapter;

public class MainActivity extends AppCompatActivity{

    private TabLayout mSmartTabLayout;
    private ViewPager mViewPager;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureToolbar();

        mSmartTabLayout = (TabLayout) findViewById(R.id.all_news_tabs);
        mViewPager = (ViewPager) findViewById(R.id.news_viewpager);
        mViewPager.setAdapter(new NewsPagerAdapter(getSupportFragmentManager(), this));
        mSmartTabLayout.setupWithViewPager(mViewPager);

    }

    private void configureToolbar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbarRumor);
        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id){
            case R.id.item_profile:
                startActivity(new Intent(MainActivity.this,UserAccountActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
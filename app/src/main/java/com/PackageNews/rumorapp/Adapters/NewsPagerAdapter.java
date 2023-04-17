package com.PackageNews.rumorapp.Adapters;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.DynamicDrawableSpan;
import android.text.style.ImageSpan;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.PackageNews.rumorapp.Fragments.BusinessNewsFragment;
import com.PackageNews.rumorapp.Fragments.EntertainmentNewsFragment;
import com.PackageNews.rumorapp.Fragments.GeneralNewsFragment;
import com.PackageNews.rumorapp.Fragments.ScienceNewsFragment;
import com.PackageNews.rumorapp.Fragments.SportsNewsFragment;
import com.PackageNews.rumorapp.Fragments.TechNewsFragment;
import com.PackageNews.rumorapp.R;


public class NewsPagerAdapter extends FragmentStatePagerAdapter {

    private Context mContext;
    Drawable myDrawable;
    String title;


    public NewsPagerAdapter(@NonNull FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }


    @NonNull
    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment =  new GeneralNewsFragment();
                break;
            case 1:
                fragment =  new TechNewsFragment();
                break;
            case 2:
                fragment =  new BusinessNewsFragment();
                break;
            case 3:
                fragment =  new ScienceNewsFragment();
                break;
            case 4:
                fragment =  new SportsNewsFragment();
                break;
            case 5:
                fragment =  new EntertainmentNewsFragment();
                break;


        }

        return fragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                myDrawable = mContext.getResources().getDrawable(R.drawable.ic_general);
                title = mContext.getResources().getString(R.string.general);
                break;
            case 1:
                myDrawable = mContext.getResources().getDrawable(R.drawable.ic_tech);
                title = mContext.getResources().getString(R.string.tech);
                break;
            case 2:
                myDrawable = mContext.getResources().getDrawable(R.drawable.ic_business);
                title = mContext.getResources().getString(R.string.business);
                break;
            case 3:
                myDrawable = mContext.getResources().getDrawable(R.drawable.science_out);
                title = mContext.getResources().getString(R.string.science);
                break;
            case 4:
                myDrawable = mContext.getResources().getDrawable(R.drawable.ic_sports);
                title = mContext.getResources().getString(R.string.sports);
                break;
            case 5:
                myDrawable = mContext.getResources().getDrawable(R.drawable.ic_tv);
                title = mContext.getResources().getString(R.string.entertainment);
                break;
            default:
                //TODO: handle default selection
                break;
        }

        SpannableStringBuilder sb = new SpannableStringBuilder("   " + title); // space added before text for convenience
        try {
            myDrawable.setBounds(0, 0, myDrawable.getIntrinsicWidth(), myDrawable.getIntrinsicHeight());
            ImageSpan span = new ImageSpan(myDrawable, DynamicDrawableSpan.ALIGN_CENTER);
            sb.setSpan(span, 0, 1, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            return sb;
        } catch (Exception e) {
            // TODO: handle exception
        }
        return null;
    }

    @Override
    public int getCount() {
        return 6;
    }
}

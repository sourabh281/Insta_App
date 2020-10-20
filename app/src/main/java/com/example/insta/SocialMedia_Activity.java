package com.example.insta;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import static androidx.fragment.app.FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT;

public class SocialMedia_Activity extends AppCompatActivity {

    private ViewPager viewPager;
    private TabLayout tabLayout;
    private Tab_Adapter tab_adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_social_media_);
        viewPager = findViewById(R.id.view_pager);

        tab_adapter = new Tab_Adapter(getSupportFragmentManager());
        viewPager.setAdapter(tab_adapter);

        tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(viewPager , false);



    }
}
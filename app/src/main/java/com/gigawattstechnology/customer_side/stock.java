package com.gigawattstechnology.customer_side;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class stock extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem availablestock,unavailablestock;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stock);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        tabLayout=findViewById(R.id.tablayout);
        viewPager=findViewById(R.id.vpager);
        availablestock=findViewById(R.id.availablestock);
        unavailablestock=findViewById(R.id.unavailablestock);
        tabLayout.setupWithViewPager(viewPager);
        PageAdapter pageAdapter=new PageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT);
        pageAdapter.addFragment(new availablestocktab(),"Available Stock");
        pageAdapter.addFragment(new Profile(),"Profile");
        pageAdapter.addFragment(new unavailablestocktab(),"Unavailable Stock");
        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);
        final Handler handler=new Handler();
        for(int i=0;i<3;i++){
            int finalI = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    viewPager.setCurrentItem(finalI);
                }
            },250);
        }
        for(int i=2;i>=0;i--){
            int finalI = i;
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    viewPager.setCurrentItem(finalI);
                }
            },250);
        }
    }
}
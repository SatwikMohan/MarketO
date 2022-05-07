package com.gigawattstechnology.marketo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class workspace extends AppCompatActivity {
    TabLayout tabLayout;
    TabItem general,profile,receipt;
    ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workspace);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();
        tabLayout=findViewById(R.id.tablayout);
        general=findViewById(R.id.general);
        profile=findViewById(R.id.profile);
        receipt=findViewById(R.id.receipt);
        viewPager=findViewById(R.id.vpager);
        tabLayout.setupWithViewPager(viewPager);
        PageAdapter pageAdapter=new PageAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_SET_USER_VISIBLE_HINT);
        pageAdapter.addFragment(new generaltab(),"General");
        pageAdapter.addFragment(new logouttab(),"Profile");
        pageAdapter.addFragment(new receipttab(),"Receipt");
        viewPager.setAdapter(pageAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }
}
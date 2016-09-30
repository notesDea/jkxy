package com.jkxy.baiduwaimai;


import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.jkxy.baiduwaimai.fragment.HomeFragment;
import com.jkxy.baiduwaimai.fragment.MineFragment;
import com.jkxy.baiduwaimai.fragment.OrderFormFragment;

public class MainActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private TabLayout tabLayout;
    private TabLayout.Tab homeTab, orderFormTab, mineTab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initListener();




    }

    private void initView() {
        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.vp_main_container);
        tabLayout = (TabLayout) findViewById(R.id.tablayout_main_category);
        mViewPager.setAdapter(new SectionsPagerAdapter(getSupportFragmentManager()));
        tabLayout.setupWithViewPager(mViewPager);

        //初始化 Tab
        homeTab = tabLayout.getTabAt(0);
        orderFormTab = tabLayout.getTabAt(1);
        mineTab = tabLayout.getTabAt(2);
        homeTab.setIcon(R.drawable.tab_icon_home_selected);
        orderFormTab.setIcon(R.drawable.tab_icon_dingdan);
        mineTab.setIcon(R.drawable.tab_icon_me);

    }

    private void initListener() {
        //当Tab被选中时
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if (tab == homeTab) {
                    homeTab.setIcon(R.drawable.tab_icon_home_selected);
                    mViewPager.setCurrentItem(0);
                } else if (tab == orderFormTab) {
                    orderFormTab.setIcon(R.drawable.tab_icon_dingdan_selected);
                    mViewPager.setCurrentItem(1);
                } else if (tab == mineTab) {
                    mineTab.setIcon(R.drawable.tab_icon_me_selected);
                    mViewPager.setCurrentItem(2);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab == homeTab) {
                    homeTab.setIcon(R.drawable.tab_icon_home);
                } else if (tab == orderFormTab) {
                    orderFormTab.setIcon(R.drawable.tab_icon_dingdan);
                } else if (tab == mineTab) {
                    mineTab.setIcon(R.drawable.tab_icon_me);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        private String[] mTitle = {"首页", "订单", "我的"};
        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return new HomeFragment();
                case 1:
                    return new OrderFormFragment();
                case 2:
                    return new MineFragment();
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return mTitle.length;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitle[position];
        }
    }


}

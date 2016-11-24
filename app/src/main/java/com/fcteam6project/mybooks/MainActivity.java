package com.fcteam6project.mybooks;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.fcteam6project.mybooks.mybooks.MyBookFragment;
import com.fcteam6project.mybooks.mybooks.TodoFragment;

public class MainActivity extends AppCompatActivity {

    HomeFragment fr_home;
    MyBookFragment fr_book;
    TodoFragment fr_todo;
    ViewPager pager;


    static final int FRAGMENT_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fr_home = new HomeFragment();
        fr_book = new MyBookFragment();
        fr_todo = new TodoFragment();

        TabLayout tablayout = (TabLayout) findViewById(R.id.tab);
        tablayout.setTabGravity(TabLayout.GRAVITY_CENTER);
        tablayout.addTab(tablayout.newTab().setText("Home"));
        tablayout.addTab(tablayout.newTab().setText("My Books"));
        tablayout.addTab(tablayout.newTab().setText("To Do"));

        pager = (ViewPager) findViewById(R.id.viewPager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(adapter);
        pager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        tablayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(pager) );

    }

    class PagerAdapter extends FragmentStatePagerAdapter {
        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return FRAGMENT_COUNT;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = null;
            switch(position){
                case 0: fragment = fr_home; break;
                case 1: fragment = fr_book; break;
                case 2: fragment = fr_todo; break;
            }
            return fragment;
        }
    }

}

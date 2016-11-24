package com.fcteam6project.mybooks;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.fcteam6project.mybooks.mybooks.MyBookFragment;
import com.fcteam6project.mybooks.mybooks.TodoFragment;

public class MainActivity extends AppCompatActivity {

    HomeFragment fr_home;
    MyBookFragment fr_book;
    TodoFragment fr_todo;
    ViewPager pager;

    private final static int REQUEST_CODE = 100;
    static final int FRAGMENT_COUNT = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 권한 세팅
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M) {
            fr_home = new HomeFragment();
            fr_book = new MyBookFragment();
            fr_todo = new TodoFragment();
        }else{
            checkPermissions();
        }

/*        TextView signIn = (TextView)findViewById(R.id.tv_main_signin);
        signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });*/

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

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermissions() {
        if(checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED ){
            String permissionArray[] = { Manifest.permission.CAMERA};
            requestPermissions( permissionArray , REQUEST_CODE );
        }else{
            fr_home = new HomeFragment();
            fr_book = new MyBookFragment();
            fr_todo = new TodoFragment();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case REQUEST_CODE:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    fr_home = new HomeFragment();
                    fr_book = new MyBookFragment();
                    fr_todo = new TodoFragment();
                }
                break;
        }
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

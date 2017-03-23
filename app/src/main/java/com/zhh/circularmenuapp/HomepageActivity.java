package com.zhh.circularmenuapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;


/**
 * Created by zhh on 2017/03/19.
 * 个人
 * csdn：http://blog.csdn.net/zhh_csdn_ard
 * devstore：http://www.devstore.cn/user_home/zhanghai_ardapp.html
 * github:https://github.com/seastoneard/
 */
public class HomepageActivity extends AppCompatActivity {



   private  HomePageMenuLayout homePageMenuLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homepage_layout);
        initLayout();
    }


    private void initLayout() {
        homePageMenuLayout= (HomePageMenuLayout) findViewById(R.id.homepage_layout);
        homePageMenuLayout.setMenuItemIconsAndTexts(Constants.MENUALL);
        homePageMenuLayout.setOnMenuItemClickListener(new HomePageMenuLayout.OnMenuItemClickListener() {
            @Override
            public void itemClick(View view, int pos) {
                Toast.makeText(HomepageActivity.this, Constants.MENUALL[pos], Toast.LENGTH_SHORT).show();
            }
        });
    }

}
